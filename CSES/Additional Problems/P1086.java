//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1086{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      
      long l = 1L;
      long r = 1000000000000000000L;
      long ans = -1;
      while(l <= r){
         long mid = l+(r-l)/2;
         
         if(check(n,mid)){
            l = mid+1;
            ans = mid;
         } else {
            r = mid-1;
         }
      }
      
      out.println(ans);
      
      
      out.close();
      
   }
   
   public static boolean check(long n, long x){
      String s = "" + x;
      int sn = s.length();
      long[] array = new long[sn];
      for(int k = 0; k < sn; k++){
         array[k] = (long)Character.getNumericValue(s.charAt(k));
      }
      long[] pow10 = new long[sn];
      pow10[sn-1] = 1L;
      for(int k = sn-2; k >= 0; k--){
         pow10[k] = pow10[k+1] * 10L;
      }
      
      for(int c = 0; c <= 9; c++){
         //System.out.println("C: " + c);
         long num = 0L;
         for(int k = 0; k < sn; k++){
            
            long freq;
            long after = pow10[k];
            
            if(c <= array[k]){
               freq = x / pow10[k] / 10L+1;
            } else {
               freq = x / pow10[k] / 10L;
            }
            
            if(c == 0){
               freq--;
            }
            
            //System.out.println(freq + " " + after);
            if(c == array[k] && k < sn-1){
               num += (freq-1) * after;
               num += x % pow10[k] + 1;
            } else {
               num += freq*after;
            }
            if(num > n || num < 0) return false;
         }
         
         if(num > n || num < 0) return false;
      }
      
      return true;
   }
   
   
   public static void check2(long x){
      String s = "" + x;
      int sn = s.length();
      long[] array = new long[sn];
      for(int k = 0; k < sn; k++){
         array[k] = (long)Character.getNumericValue(s.charAt(k));
      }
      long[] pow10 = new long[sn];
      pow10[sn-1] = 1L;
      for(int k = sn-2; k >= 0; k--){
         pow10[k] = pow10[k+1] * 10L;
      }
      
      for(int c = 0; c <= 9; c++){
         //System.out.println("C: " + c);
         long num = 0L;
         for(int k = 0; k < sn; k++){
            
            long freq;
            long after = pow10[k];
            
            if(c <= array[k]){
               freq = x / pow10[k] / 10L+1;
            } else {
               freq = x / pow10[k] / 10L;
            }
            
            if(c == 0){
               freq--;
            }
            
            //System.out.println(freq + " " + after);
            if(c == array[k] && k < sn-1){
               num += (freq-1) * after;
               num += x % pow10[k] + 1;
            } else {
               num += freq*after;
            }
         }
         
         System.out.println(num);
      }
      
      int check = 0;
      for(long k = 1; k <= x; k++){
         if(k >= 1000) check++;
         if(k >= 100) check++;
         if(k >= 10) check++;
         check++;
      }
      System.out.println("Check: " + check);
      
   }     
}