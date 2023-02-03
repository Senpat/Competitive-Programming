//make sure to make new file!
import java.io.*;
import java.util.*;
//hint (cnblogs)
public class P5Fb{
   
   public static int K = 18;
   public static int[][] spt;
   
   public static int[] log;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      int n = s.length();
      
      s += "$";
      
      int[] suffixarray = make_suffix_array(s);
      
      int[] lcp = make_lcp(s,suffixarray);
      
      int[] indexof = new int[n+1];
      for(int k = 1; k <= n; k++){
         indexof[suffixarray[k]] = k;
      }
      
      spt = new int[n][K+1];
      
      for(int k = 0; k < n; k++){
         spt[k][0] = lcp[k];
      }
      
      for(int j = 1; j <= K; j++){
         for(int k = 1; k + (1<<j) < n; k++){
            spt[k][j] = Math.min(spt[k][j-1],spt[k + (1 << (j-1))][j-1]);
         }
      }
      
      log = new int[n+1];
      log[1] = 0;
      for(int k = 2; k <= n; k++){
         log[k] = log[k/2]+1;
      }
      
      int answer = 1;
      for(int gap = 1; gap <= n; gap++){
         for(int k = 0; k+gap <= n; k += gap){
            int a = Math.min(indexof[k],indexof[k+gap]);
            int b = Math.max(indexof[k],indexof[k+gap]);
            
            int curlcp = getmin(a,b-1);
            
            answer = Math.max(answer,curlcp/gap+1);
            
            int extra = gap - curlcp % gap;
            if(extra != 0 && curlcp != 0 && k-extra >= 0){
               a = Math.min(indexof[k-extra],indexof[k-extra+gap]);
               b = Math.max(indexof[k-extra],indexof[k-extra+gap]);
               
               curlcp = getmin(a,b-1);
               
               answer = Math.max(answer,curlcp/gap+1);
            }
            
         }
      }
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static int getmin(int l, int r){
      int lg = log[r-l+1];
      return Math.min(spt[l][lg],spt[r-(1 << lg)+1][lg]);
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