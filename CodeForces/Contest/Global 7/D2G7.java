//make sure to make new file!
import java.io.*;
import java.util.*;

public class D2G7{
   
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
      
      char[] kmp = new char[s2.length*2+1];
      for(int k = 0; k < s2.length; k++){
         kmp[k] = s2[k];
      }
      kmp[s2.length]='?';
      for(int k = 0; k < s2.length; k++){
         kmp[s2.length*2-k] = s2[k];
      }
      
      int n = s2.length*2+1;
      
      int[] lps = new int[n];       // lps[i] = longest suffix length for substring kmp[0..i] where the suffix == prefix
      
      for(int k = 1; k < n; k++){
         int prev = lps[k-1];
         while(prev > 0 && kmp[k] != kmp[prev]){
            prev = lps[prev-1];
         }
         lps[k] = prev+(kmp[k]==kmp[prev]?1:0);
      }
      
      return lps[n-1];
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