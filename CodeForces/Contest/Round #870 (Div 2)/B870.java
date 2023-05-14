//make sure to make new file!
import java.io.*;
import java.util.*;

public class B870{
   
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
         
         long gcd = 0;
         for(int k = 0; k < n-k-1; k++){
            long diff = Math.abs(array[n-k-1] - array[k]);
            if(gcd == 0) gcd = diff;
            else {
               gcd = gcd(gcd,diff);
            }
         }
         
         out.println(gcd);

      }
      
      
      
      
      out.close();
   }
   
   public static long gcd(long a, long b){
      if(a < b){
         if(a == 0) return b;
         return gcd(b%a,a);
      }
      if(b < a){
         if(b == 0) return a;
         return gcd(a%b,b);
      }
      return a;
   }
   
      
}