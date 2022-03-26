//make sure to make new file!
import java.io.*;
import java.util.*;
//standard crt
public class crt{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
        
         long a1 = Long.parseLong(st.nextToken());
         long n1 = Long.parseLong(st.nextToken());
         long a2 = Long.parseLong(st.nextToken());
         long n2 = Long.parseLong(st.nextToken());
         
         long answer = crt(a1,n1,a2,n2);
         if(answer % n2 != a2){
            out.println("no solution");
         } else {
            out.println(answer + " " + (n1*n2/gcd(n1,n2)));
         }

      }
      
      
      
      
      out.close();
   }
   
   
      /*Chinese Remainder Theorem - solves:
   x = a1 (mod n1)
   x = a2 (mod n2)
   */
   public static long crt(long a1, long n1, long a2, long n2){
      
      long d = gcd(n1,n2);
      long lcm = n1*n2/d;
      
      //x = a1 + n1*k1
      
      //calculate x' using Extended Euclidean Algorithm
      long xprime = exgcd(n1,n2);
      
   
      long prod1 = (xprime * (a2-a1)/d + (n2/d)) % (n2/d);
      long prod2 = (prod1 * n1 + lcm)%lcm;
      long answer = (prod2 + a1 + lcm)%lcm;
      
      return answer;
   }
   
   //https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm
   //ONLY RETURNS X'
   public static long exgcd(long a, long b){
      long s = 0;
      long r = b;
      long old_s = 1;
      long old_r = a;
      
      long temp;
      while(r != 0){
         long quotient = old_r / r;
         temp = r;
         r = old_r - quotient*temp;
         old_r = temp;
         
         temp = s;
         s = old_s - quotient*temp;
         old_s = temp;
      }
      
      return old_s;
   }
   
   public static long gcd(long a, long b){
      if(a > b){
         if(b == 0) return a;
         return gcd(a%b,b);
      } else if(a < b){
         if(a == 0) return b;
         return gcd(b%a,a);
      }
      return a;
   }
   
      
}