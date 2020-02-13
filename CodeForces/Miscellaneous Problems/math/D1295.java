//Same GCD's
import java.io.*;
import java.util.*;

public class D1295{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long a = Long.parseLong(st.nextToken());
         long m = Long.parseLong(st.nextToken());
         
         long gcd = gcd(a,m);
         //out.println(gcd);
         
         //find totient of m/gcd
         long tot = totient(m/gcd);
         out.println(tot);
         
      }
      
      
      
      
      
      
      
      out.close();
   }
   //from cp-algorithms
   public static long totient(long x){
      long result = x;
      for(long i = 2; i*i<=x; i++){
         if(x%i == 0){
            while(x%i == 0){
               x/=i;
            }
            result -= result/i;
         }
      }
      
      if(x > 1){
         result -= result/x;
      }
      return result;
   }
   
   //gcd of two numbers, a and b
   public static long gcd(long a, long b){
      
      if(b > a){
         long temp = a;
         a = b;
         b = temp;
      }
      
      
      
      while(a != 0){
         b = b%a;
         
         long temp = a;
         a = b;
         b = temp;
         
      }
      
      return b;
   }
         
   
   
   
}