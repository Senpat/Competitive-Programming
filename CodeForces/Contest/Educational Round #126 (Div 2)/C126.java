//make sure to make new file!
import java.io.*;
import java.util.*;

public class C126{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         
         long max = 0L;
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            max = Math.max(array[k],max);
         }
         
         long l = 0;
         long r = 300000000000000L;
         long ans = -1;
         
         while(l <= r){
            long mid = l + (r-l)/2L;
            
            //check
            long num2 = mid/2;
            long num1 = mid-num2;
            
            boolean val = check(num1,num2,max,array) || check(num1,num2,max+1,array);
            
            if(!val){
               l = mid+1;
            } else {
               ans = mid;
               r = mid-1;
            }
         }
         
         out.println(ans);
            
            
      
      
      
      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(long num1, long num2, long max, long[] array){
      for(int k = 0; k < array.length; k++){
         long dif = max-array[k];
         if(num2 >= dif/2){
            num2 -= dif/2;
            dif -= (dif/2)*2;
         } else {
            dif -= num2*2;
            num2 = 0;
         }
               
               //fill rest with 0s
         if(num1 >= dif){
            num1 -= dif;
            dif = 0;
         } else {
            return false;
            
         }
      }
   
      return true;
            
   }
   
   
}