//Delivius Dessert
import java.io.*;
import java.util.*;

public class G1780{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String s = f.readLine();
      s += '$';
      
      int[] suffixarray = make_suffix_array(s);
      
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
   
   public static int[] make_lcp(String s, int[] suffixarray){
      int n = s.length();
      
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
         while(k+x < n && j+x < n && s.charAt(k+x) == s.charAt(j+x)){
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
   public static int[] make_suffix_array(String s){
      int n = s.length();
      int alphabet = 256;
      int[] p = new int[n];
      int[] c = new int[2*n];
      int[] cnt = new int[Math.max(alphabet,n)];
      
      for(int k = 0; k < n; k++){
         cnt[ctoi(s.charAt(k))]++;
      }
      for(int k = 1; k < alphabet; k++){
         cnt[k] += cnt[k-1];
      }
      for(int k = 0; k < n; k++){
         p[--cnt[ctoi(s.charAt(k))]] = k;
      }
      c[p[0]] = 0;
      int classes = 1;
      for(int k = 1; k < n; k++){
         if(s.charAt(p[k]) != s.charAt(p[k-1])){
            classes++;
         }
         c[p[k]] = classes-1;
      }
      
      int[] pn = new int[n];
      int[] cn = new int[2*n];
      
      for(int h = 0; (1 << h) < n; h++){
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
      return (int)ch;
   }
 
   
      
}