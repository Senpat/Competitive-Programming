//make sure to make new file!
import java.io.*;
import java.util.*;
//in contest attempt tle
//upsolve, comments
public class E142{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long n = Long.parseLong(st.nextToken());
         long m1 = Long.parseLong(st.nextToken());
         long m2 = Long.parseLong(st.nextToken());
         
         HashMap<Long,Integer> divs = new HashMap<Long,Integer>();
         //prime fac m1 * m2
         
         ArrayList<Long> primes = new ArrayList<Long>();
         long i1 = m1;
         long i2 = m2;
         for(long k = 2L; k*k <= i1 || k*k <= i2; k++){
            int v = 0;
            while(i1 > 1 && i1%k == 0L){
               v++;
               i1/=k;
            }
            while(i2 > 1 && i2%k == 0L){
               v++;
               i2/=k;
            }
            
            if(v > 0){
               divs.put(k,v);
               primes.add(k);
            }
            if(k!=2)k++;
         } 
         
         if(i1 > 1 && i1==i2){
            divs.put(i1,2);
            primes.add(i1);
         } else {
            if(i1 > 1){
               divs.put(i1,1);
               primes.add(i1);
            }
            if(i2 > 1){
               divs.put(i2,1);
               primes.add(i2);
            }
         }
         
         Collections.sort(primes);
         
         int p = primes.size();
         
         long cur = 1L;
         int[] count = new int[p];
         
         long[] pows = new long[p];
         for(int k = 0; k < p; k++){
            long curpow = 1L;
            for(int j = 0; j < divs.get(primes.get(k)); j++){
               curpow *= primes.get(k);
            }
            pows[k] = curpow;
         }
         
         int non0 = 0;
         long answer = 0;
         
         ArrayList<Long> alldivs = new ArrayList<Long>();
         while(true){
            alldivs.add(cur);
            
            //increment
            int i = 0;
            while(i < p && count[i] == divs.get(primes.get(i))){
               cur /= pows[i];
               count[i] = 0;
               i++;
            }
            if(i >= p){
               break;
            } else {
               count[i]++;
               cur *= primes.get(i);
            }
         }
         
         //dp
         Collections.sort(alldivs);
         HashMap<Long,Long> dp = new HashMap<Long,Long>();
         
         for(long d : alldivs){
            if(d > n*n){
               dp.put(d,0L);
            } else if(d <= n){
               dp.put(d,d);
            } else {
               long max = 0;
               for(long prime : primes){
                  long prev = d/prime;
                  if(d%prime == 0L && dp.containsKey(prev) && dp.get(prev) != 0){
                     if(d > n*dp.get(prev)) continue;
                     max = Math.max(max,dp.get(prev));
                  }
               }
               dp.put(d,max);
            }
         }
         
         int answer1 = 0;
         long answer2 = 0L;
         
         for(long d : alldivs){
            if(dp.get(d) != 0){
               answer1++;
               answer2 ^= d/dp.get(d);
            }
         }
         
         out.println(answer1 + " " + answer2);
      }
      
      
      
      
      out.close();
   }
   
      
}