//Paint the Array
import java.io.*;
import java.util.*;

public class C1618{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long[] array = new long[n];
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         long gcdodd = array[1];
         long gcdeven = array[0];
         for(int k = 2; k < n; k++){
            if(k%2 == 0){
               gcdeven = gcd(gcdeven,array[k]);
            } else {
               gcdodd = gcd(gcdodd,array[k]);
            }
         }
         
         //try odd
         boolean fail = false;
         for(int k = 0; k < n; k+=2){
            if(array[k]%gcdodd == 0){
               fail = true;
               break;
            }
         }
         
         if(!fail){
            out.println(gcdodd);
            continue;
         }
         
         //try even
         fail = false;
         for(int k = 1; k < n; k+=2){
            if(array[k]%gcdeven == 0){
               fail = true;
               break;
            }
         }
         
         if(!fail){
            out.println(gcdeven);
            continue;
         }
         
         out.println(0);
         
      

      }
      
      
      
      
      out.close();
   }
   
   public static long gcd(long a, long b){
      if(a < b) return a == 0 ? b : gcd(b%a,a);
      if(a > b) return b == 0 ? a : gcd(a%b,b);
      return a;
   }
   
      
}