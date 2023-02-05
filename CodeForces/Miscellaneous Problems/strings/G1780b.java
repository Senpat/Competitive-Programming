//Delivius Dessert
import java.io.*;
import java.util.*;
//same as G1780.java but with fast suffix array implementation
//actually not faster
public class G1780b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      String ss = f.readLine();
      int[] s = new int[n+1];
      for(int k = 0; k < n; k++) s[k] = ctoi(ss.charAt(k));
      s[n] = 0;
      
      int[] suffixarray = make_suffix_array_fast(s);
      
      int[] lcp = make_lcp(s,suffixarray);
      
      ArrayList<ArrayList<Integer>> alist = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) alist.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n; k++){
         alist.get(lcp[k]).add(k);
      }
      
      //ranges
      int[] endofstart = new int[n];
      int[] startofend = new int[n];
      Arrays.fill(endofstart,-1);
      Arrays.fill(startofend,-1);
      long[] sizefreqs = new long[n+1];
      
      long answer = (long)n;
      
      for(int k = n; k >= 2; k--){
         for(int i : alist.get(k)){
            //add i to range
            //can merge left and right
            if(i > 0 && startofend[i-1] != -1 && i < n-1 && endofstart[i+1] != -1){
               sizefreqs[i-1-startofend[i-1]+1]--;
               sizefreqs[endofstart[i+1]-(i+1)+1]--;
               
               endofstart[startofend[i-1]] = endofstart[i+1];
               startofend[endofstart[i+1]] = startofend[i-1];
               sizefreqs[endofstart[i+1]-startofend[i-1]+1]++;
               
               startofend[i-1] = -1;
               endofstart[i+1] = -1;
            } else if(i > 0 && startofend[i-1] != -1){
               //can merge left
               sizefreqs[i-1-startofend[i-1]+1]--;
               
               endofstart[startofend[i-1]] = i;
               startofend[i] = startofend[i-1];
               sizefreqs[i-startofend[i-1]+1]++;
               
               startofend[i-1] = -1;
            } else if(i < n-1 && endofstart[i+1] != -1){
               //can merge right
               sizefreqs[endofstart[i+1]-(i+1)+1]--;
               
               startofend[endofstart[i+1]] = i;
               endofstart[i] = endofstart[i+1];
               sizefreqs[endofstart[i+1]-i+1]++;
               
               endofstart[i+1] = -1;
            } else {
               //make range by itself
               startofend[i] = i;
               endofstart[i] = i;
               sizefreqs[1]++;
            }
            
         }
         
         for(int j = k-1; j <= n; j += k){
            answer += sizefreqs[j]*(j+1);
         }
      }
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   public static int[] make_lcp(int[] s, int[] suffixarray){
      int n = s.length;
      
      int[] pos = new int[n];
      for(int k = 0; k < n; k++){
         pos[suffixarray[k]] = k;
      }
      
      int[] lcp = new int[n-1];
      int x = 0;
      for(int k = 0; k < n; k++){
         if(pos[k] == n-1){
            x = 0;
            continue;
         }
         
         int j = suffixarray[pos[k]+1];
         while(k+x < n && j+x < n && s[k+x] == s[j+x]){
            x++;
         }
         lcp[pos[k]] = x;
         if(x > 0){
            x--;
         }
      }
     
      return lcp;
   }
   
   
   //https://cp-algorithms.com/string/suffix-array.html#on-log-n-approach
   //with improvements by danny https://codeforces.com/contest/1780/submission/190686802
   //faster on large N
   public static int[] make_suffix_array_fast(int[] s){
      int n = s.length;
      int alphabet = 1 << 20;
      int[] p = new int[n];
      int[] c = new int[2*n];
      int[] cnt = new int[alphabet];
      
      int[] initial = new int[n];
      if(n < 5) initial = s;
      else{
         initial[0] = s[3] + (32 * (s[2] + (32 * (s[1] + (32 * s[0])))));
         for(int k = 1; k < n-3; k++){
            initial[k] = s[k+3] + (initial[k-1] << 5) - (s[k-1] << 20);
         }
         for(int k = n-3; k < n; k++){
            initial[k] = (initial[k-1] << 5) - (s[k-1] << 20);
         }
      }
      
      for(int k = 0; k < n; k++){
         cnt[initial[k]]++;
      }
      for(int k = 1; k < alphabet; k++){
         cnt[k] += cnt[k-1];
      }
      for(int k = 0; k < n; k++){
         p[--cnt[initial[k]]] = k;
      }
      c[p[0]] = 0;
      int classes = 1;
      for(int k = 1; k < n; k++){
         if(initial[p[k]] != initial[p[k-1]]){
            classes++;
         }
         c[p[k]] = classes-1;
      }
      
      int[] pn = new int[n];
      int[] cn = new int[2*n];
      
      for(int h = 2; (1 << h) < n; h++){
         for(int k = 0; k < n; k++){
            pn[k] = p[k] - (1 << h);
            if(pn[k] < 0){
               pn[k] += n;
            }
         }
         
         Arrays.fill(cnt,0);
         
         for(int k = 0; k < n; k++){
            cnt[c[pn[k]]]++;
         }
         for(int k = 1; k < classes; k++){
            cnt[k] += cnt[k-1];
         }
         for(int k = n-1; k >= 0; k--){
            p[--cnt[c[pn[k]]]] = pn[k];
         }
         cn[p[0]] = 0;
         classes = 1;
         for(int k = 1; k < n; k++){
            int cur1 = c[p[k]];
            int cur2 = c[p[k] + (1 << h)];
            int prev1 = c[p[k-1]];
            int prev2 = c[p[k-1] + (1 << h)];
            
            if(cur1 != prev1 || cur2 != prev2){
               classes++;
            }
            cn[p[k]] = classes-1;
         }
         
         //swap c and cn
         int[] temp = c;
         c = cn;
         cn = temp;
      }
      
      return p;
   }         
              
   //may need to edit (as well as alphabet variable)
   public static int ctoi(char ch){
      return ch-'a'+1;
   }
 
   
      
}