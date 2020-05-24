//make sure to make new file!
import java.io.*;
import java.util.*;

public class D1G7{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         String s = f.readLine();
         char[] array = s.toCharArray();
         int n = s.length();
         
         if(n==1){
            out.println(s);
            continue;
         }
         
         int l = 0;
         int r = n-1;
         while(r > l && array[l] == array[r]){
            l++;
            r--;
         }
         /*
         if(r <= l+1 && n != 2){
            out.println(s);
            continue;
         }
         */
         char[] s2 = s.substring(l,r+1).toCharArray();
         char[] s2r = reverse(s2);
         int pre = prefixpalin(s2);
         int suf = prefixpalin(s2r);
         
         if(pre > suf){
            StringJoiner sj = new StringJoiner("");
            sj.add(s.substring(0,l));
            for(int k = 0; k < pre; k++){
               sj.add("" + s2[k]);
            }
            sj.add(s.substring(r+1,n));
            out.println(sj.toString());
         } else {
            StringJoiner sj = new StringJoiner("");
            sj.add(s.substring(0,l));
            for(int k = 0; k < suf; k++){
               sj.add("" + s2r[k]);
            }
            sj.add(s.substring(r+1,n));
            out.println(sj.toString());
         }
         
      
      }
      
      
      
      
      out.close();
   }
   
   public static int prefixpalin(char[] s2){
      if(s2.length == 1) return 1;
      
      for(int k = s2.length-1; k >= 0; k--){
         if(checkpalin(s2,k)) return k;
      }
      
      return -1;
   }
   
   public static boolean checkpalin(char[] s2, int n){
      int l = 0;
      int r = n-1;
      
      while(r > l){
         if(s2[l] != s2[r]) return false;
         r--;
         l++;
      }
      
      return true;
   }
   
   public static char[] reverse(char[] array){
      int n = array.length;
      char[] ret = new char[n];
      for(int k = 0; k < n; k++){
         ret[k] = array[n-k-1];
      }
      return ret;
   }
      
      
      
      
   
   
      
}