//make sure to make new file!
import java.io.*;
import java.util.*;

import java.math.*;

public class F340{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long x = Long.parseLong(st.nextToken());
      long y = Long.parseLong(st.nextToken());
      
      boolean fail = false;
      long a = 0L;
      long b = 0L;
      if(x == 0L){
         if(2L % y == 0L){
            b = 0L;
            a = 2L/y;
         } else {
            fail = true;
         }
      } else if(y == 0L){
         if(2L % x == 0L){
            a = 0L;
            b = 2L/x;
         } else fail = true;
      } else if(x == 1L){
         a = 0L;
         b = 2L;
      } else if(x == 2L){
         a = 2L;
         b = y+1L;
      } else {
         //try y*A = 2 mod X and y*A = X-2 mod X
         fail = true;
         long A = get(x,y,2L);
         
         if(A != Long.MAX_VALUE){
            a = A;
            BigInteger Ab = new BigInteger("" + A);
            BigInteger Yb = new BigInteger("" + y);
            BigInteger Xb = new BigInteger("" + x);
            BigInteger b2 = new BigInteger("2");
            
            b = Ab.multiply(Yb).subtract(b2).divide(Xb).longValue();            //A < x, guaranteed to fit
            fail = false;
         }
         
         
         if(fail){
            long c = Math.abs(x)-2L;
            A = get(x,y,c);
            
            if(A != Long.MAX_VALUE){
               a = A;
               BigInteger Ab = new BigInteger("" + A);
               BigInteger Yb = new BigInteger("" + y);
               BigInteger Xb = new BigInteger("" + x);
               BigInteger b2 = new BigInteger("2");
               
               b = Ab.multiply(Yb).add(b2).divide(Xb).longValue();            //A < x, guaranteed to fit
               fail = false;
            }
         }
      }
      
      
      
      if(fail){
         out.println("-1");
      } else {
         out.println(a + " " + b);
      }
      
      
      
      out.close();
   }
   
   //finds A that solves y*A = a mod x
   public static long get(long x, long y, long a){
      long xa = Math.abs(x);
      long ya = Math.abs(y);
      long g = gcd(xa,ya);
      
      if(a % g != 0L) return Long.MAX_VALUE;
      a /= g;
      xa /= g;
      y /= g;
      
      if(y < 0) y = ((y%xa) + xa)%xa;
      
      long A = (a * modInverse(y,xa) + xa)%xa;
      
      return A;
   }
   
   
   public static long gcd(long x, long y){
      if(x > y){
         if(y == 0L) return x;
         return gcd(x%y,y);
      } else if(x < y){
         if(x == 0L) return y;
         return gcd(y%x,x);
      }
      return x;
   }
      
      
        //from geeksforgeeks
   public static long modInverse(long a, long m) 
   { 
       long m0 = m; 
       long y = 0;
       long x = 1; 
     
       if (m == 1) 
         return 0; 
     
       while (a > 1) 
       { 
           // q is quotient 
           long q = a / m; 
           long t = m; 
     
           // m is remainder now, process same as 
           // Euclid's algo 
           m = a % m;
           a = t; 
           t = y; 
     
           // Update y and x 
           y = x - q * y; 
           x = t; 
       } 
     
       // Make x positive 
       if (x < 0) 
          x += m0; 
       return x; 
   }  


      
}