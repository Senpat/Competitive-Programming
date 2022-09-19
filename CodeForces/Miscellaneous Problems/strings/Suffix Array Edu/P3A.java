//make sure to make new file!
import java.io.*;
import java.util.*;

public class P3A{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      int n = s.length();
      int[] suffixarray = make_suffix_array(s);
      
      
      int t = Integer.parseInt(f.readLine());
      for(int q = 0; q < t; q++){
         String qs = f.readLine();
         int qn = qs.length();
         /*
         if(qn > n){
            out.println("No");
            continue;
         } else if(qn == n){
            if(qs.equals(s)){
               out.println("Yes");
            } else {
               out.println("No");
            }
            continue;
         }*/
      
         int l = 1;
         int r = n;
         int ans = -1;
         
         while(l <= r){
            int mid = l + (r-l)/2;
            
            if(compare(s,suffixarray[mid],qs) >= 0){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         if(ans == -1 || compare(s,suffixarray[ans],qs) != 0){
            out.println("No");
         } else {
            out.println("Yes");
         }
         
      
      }
      
      
      
      
      
      out.close();
   }
   /*
   public static boolean checkequal(String s, int x, String qs){
      int n = s.length();
      int qn = qs.length();
      
      if(x + qn > n) return false;
      
      for(int k = 0; k < qn; k++){
         if(s.charAt(k+x) != qs.charAt(k)) return false;
      }
      
      return true;
   }*/
   
   //0 if qs is prefix of s substring at s, -1 if s substring at x is lexicographically less than qs, 1 if qs is greater
   public static int compare(String s, int x, String qs){
      int n = s.length();
      int qn = qs.length();
      
      int i = 0;
      while(i+x < n && i < qn && s.charAt(i+x) == qs.charAt(i)) i++;
      
      if(i >= qn){
         return 0;
      } else if(i+x >= n){
         return -1;
      }
      
      return (int)s.charAt(i+x) - (int)qs.charAt(i);
   }
   
   //https://cp-algorithms.com/string/suffix-array.html#on-log-n-approach
   public static int[] sort_cyclic_shifts(String s){
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
   
   //ELEMENT AT 0 IS ALWAYS N
   public static int[] make_suffix_array(String s){
      return sort_cyclic_shifts(s + "$");
   }
   
      
}