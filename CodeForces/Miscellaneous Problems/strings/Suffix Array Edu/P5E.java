//make sure to make new file!
import java.io.*;
import java.util.*;

public class P5E{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      char[] charray = new char[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         charray[k] = (char)(array[k]+65);
      }
      
      String s = new String(charray);
      s += '$';
      
      int[] suffixarray = make_suffix_array(s);
      
      int[] lcp = make_lcp(s,suffixarray);
      
      ArrayList<ArrayList<Integer>> alist = new ArrayList<ArrayList<Integer>>();
      for(int k = 0; k < n; k++){
         alist.add(new ArrayList<Integer>());
      }
      
      for(int k = 1; k < n; k++){
         alist.get(lcp[k]).add(k);
      }
      
      int[] startofend = new int[n];
      int[] endofstart = new int[n];
      Arrays.fill(startofend,-1);
      Arrays.fill(endofstart,-1);
      
      long max = (long)n;
      int alen = n;
      int aind = 0;
      
      for(int k = n-1; k >= 1; k--){
         for(int i : alist.get(k)){
            
            int left;
            int right;
            if(i != 1 && startofend[i-1] != -1 && i != n-1 && endofstart[i+1] != -1){
               left = startofend[i-1];
               right = endofstart[i+1];
            } else if(i != 1 && startofend[i-1] != -1){
               left = startofend[i-1];
               right = i;
            } else if(i != n-1 && endofstart[i+1] != -1){
               left = i;
               right = endofstart[i+1];
            } else {
               left = i;
               right = i;
            }
         
            startofend[right] = left;
            endofstart[left] = right;
            
            int len = right-left+1+1;
            long cur = (long)len * (long)k;
            
            if(max < cur){
               max = cur;
               alen = k;
               aind = suffixarray[i];
            }
         }
      }
      
      out.println(max);
      out.println(alen);
      for(int k = 0; k < alen; k++){
         out.print(array[aind+k] + " ");
      }
      
      
      
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
      int[] c = new int[n];
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
      int[] cn = new int[n];
      
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
            int cur2 = c[(p[k] + (1 << h)) % n];
            int prev1 = c[p[k-1]];
            int prev2 = c[(p[k-1] + (1 << h)) % n];
            
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