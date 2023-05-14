//make sure to make new file!
import java.io.*;
import java.util.*;

public class C860{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         long[] a = new long[n];
         long[] b = new long[n];
         
         for(int k = 0; k < n; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
         
            a[k] = Long.parseLong(st.nextToken());
            b[k] = Long.parseLong(st.nextToken());
      
         }
         
         int answer = 1;
         long curgcd = a[0]*b[0];
         long blcm= b[0];
         for(int k = 1; k < n; k++){
            curgcd = gcd(curgcd,a[k]*b[k]);
            blcm = blcm * b[k] / gcd(blcm,b[k]);
            if(curgcd % blcm != 0){
               answer++;
               curgcd = a[k]*b[k];
               blcm = b[k];
            }
         }
         
         out.println(answer);
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