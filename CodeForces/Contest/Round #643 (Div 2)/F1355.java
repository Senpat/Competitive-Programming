//make sure to make new file!
import java.io.*;
import java.util.*;
//in contest attempt, fail
public class F1355{

   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      long e9 = 1000000000L;
      long e18 = 1000000000000000000L;
      //get first 44 primes
      int NUMP = 196;
      
      
      
      //sieve is from geeks for geeks
      boolean prime[] = new boolean[NUMP+1]; 
      for(int i=0;i<NUMP;i++) 
         prime[i] = true; 
          
      for(int p = 2; p*p <=NUMP; p++) 
      { 
            // If prime[p] is not changed, then it is a prime 
         if(prime[p] == true) 
         { 
                // Update all multiples of p 
            for(int i = p*p; i <= NUMP; i += p) 
               prime[i] = false; 
         } 
      } 
          
      ArrayList<Long> primes = new ArrayList<Long>();
        // Print all prime numbers 
      for(int i = 2; i <= NUMP; i++) 
      { 
         if(prime[i] == true) primes.add((long)i);
      } 
      
      int[] divfreq = new int[NUMP];
      
      for(int q = 1; q <= t; q++){
      
         for(int k = 0; k < 22; k++){
            long a = primes.get(k*2);
            long b = primes.get(k*2+1);
            
            long i = 1L;
            
            while(i*a < e9){
               i *= a;
            }
            while(i*b < e18 && i*b > 0){
               i *= b;
            }
            
            out.println("? " + i);
            out.flush();
            
            int r = Integer.parseInt(f.readLine());
            
            //update divfreq
            while(r%a == 0){
               divfreq[k*2]++;
               r/=a;
            }
            while(r%b == 0){
               divfreq[k*2+1]++;
               r/=b;
            }
         }
         
         long answer = 2L;
         
         for(int k = 0; k < 44; k++){
            answer *= (divfreq[k]+1);
         }
         
         out.println("! " + answer);
         out.flush();
      
      }
      
      
      
      
      out.close();
   }
   
 
      
}