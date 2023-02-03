//make sure to make new file!
import java.io.*;
import java.util.*;

public class BTDB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      int N = 100000;
      boolean[] isprime = new boolean[N];
      Arrays.fill(isprime,true);
      for(int k = 2; k < N; k++){
         if(isprime[k]){
            for(int j = k*2; j < N; j += k){
               isprime[j] = false;
            }
         }
      }
      
      ArrayList<Long> primes = new ArrayList<Long>();
      for(int k = 2; k < N; k++){
         if(isprime[k]){
            primes.add((long)k);
         }
      }
      
      int M = 40;
      
      for(int q = 1; q <= t; q++){

         long n = Long.parseLong(f.readLine());
      
         ArrayList<ArrayList<Long>> freqs = new ArrayList<ArrayList<Long>>(M);
         for(int k = 0; k < M; k++) freqs.add(new ArrayList<Long>());
         
         int allprimes = 1;
         long i = n;
         int p = 0;
         while(p < primes.size() && primes.get(p)*primes.get(p) <= i){
            int count = 0;
            while(i % primes.get(p) == 0){
               count++;
               i /= primes.get(p);
            }
            
            if(count > 0){
               freqs.get(count).add(primes.get(p));
               allprimes *= primes.get(p);
            }
            
            p++;
         }
         
         if(i > 1){
            freqs.get(1).add(i);
            allprimes *= i;
         }
         
         long answer = 0L;
         
         int last = 0;
         for(int k = 1; k < M; k++){
            if(freqs.get(k).size() > 0){
               answer += allprimes*(long)(k-last);
               for(long prime : freqs.get(k)){
                  allprimes /= prime;
               }
               last = k;
            }
         }
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}