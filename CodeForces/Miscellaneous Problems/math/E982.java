//Billiard
import java.io.*;
import java.util.*;

public class E982{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      long x = Long.parseLong(st.nextToken());
      long y = Long.parseLong(st.nextToken());
      long vx = Long.parseLong(st.nextToken());
      long vy = Long.parseLong(st.nextToken());
      
      if(vx == 0){
         if(x == 0 || x == n){
            if(vy == 1){
               out.println(x + " " + m);
            } else {
               out.println(x + " " + 0);
            }
         } else {
            out.println(-1);
         }
      } else if(vy == 0){
         if(y == 0 || y == m){
            if(vx == 1){
               out.println(n + " " + y);
            } else {
               out.println(0 + " " + y);
            }
         } else {
            out.println(-1);
         }
      } else {
      
         long xmod = 0;
         long ymod = 0;
         
         long fwx = 0;                 //first wall x
         long fwy = 0;                 //first wall y
                  
         if(x == 0 || x == n){
            xmod = 0;
            fwx = x;
         } else if(vx == 1){
            xmod = n-x;
            fwx = n;
         } else {
            xmod = x;
            fwx = 0;
         }
         
         if(y == 0 || y == m){
            ymod = 0;
            fwy = y;
         } else if(vy == 1){
            ymod = m-y;
            fwy = m;
         } else {
            ymod = y;
            fwy = 0;
         }
         
         
         
         long answer = crt(xmod,n,ymod,m);
         //out.println(answer);
         if(answer % m != ymod){
            out.println(-1);
         } else {
            long xans = 0;
            long yans = 0;
            
            if((answer/n)%2 == 0){                 //same as first wall hit
               xans = fwx;
            } else {
               xans = n-fwx;
            }
            
            if((answer/m)%2 == 0){                 //same as first wall hit
               yans = fwy;
            } else {
               yans = m-fwy;
            }
            
            out.println(xans + " " + yans);
            
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