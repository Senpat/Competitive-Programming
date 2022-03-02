//make sure to make new file!
import java.io.*;
import java.util.*;

public class B736{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      //sieve up to 10^7
      int P = 10000000;
      boolean[] prime = new boolean[P];
      for(int k = 2; k < P; k++){
         if(prime[k]) continue;
         for(int j = k*2; j < P; j+=k){
            prime[j] = true;
         }
      }
      
      ArrayList<Long> primes = new ArrayList<Long>();
      for(int k = 2; k < P; k++){
         if(!prime[k]) primes.add((long)k);
      }
      
      int REP = 20;
      
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         int odd = 0;
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            if(array[k]%2 == 1) odd++;
         }
         
         int answer = Math.max(odd,n-odd);
         
         HashSet<Long> tested = new HashSet<Long>();
         for(int r = 0; r < REP; r++){
            int x1 = (int)(Math.random()*n);
            int x2 = (int)(Math.random()*n);
            
            long dif = Math.abs(array[x1]-array[x2]);
            
            if(dif < 2) continue;
            
            //prime fac
            ArrayList<Long> curprimes = new ArrayList<Long>();
            long i = dif;
            for(long p : primes){
               if(p*p > i) break;
               if(i % p == 0){
                  curprimes.add(p);
                  while(i % p == 0){
                     i /= p;
                  }
               }
            }
            
            if(i > 1){
               curprimes.add(i);
            }
            
            //(at most 20)
            for(long cp : curprimes){
               //test cp
               if(tested.contains(cp)) continue;
               tested.add(cp);
               out.println(cp);
               HashMap<Long,Integer> freqs = new HashMap<Long,Integer>();
               for(int k = 0; k < n; k++){
                  long x = array[k]%cp;
                  if(!freqs.containsKey(x)) freqs.put(x,1);
                  else freqs.put(x,freqs.get(x)+1);
               }
               
               for(long x : freqs.keySet()){
                  answer = Math.max(answer,freqs.get(x));
               }
               out.println("new answer: " + answer);
            }
         }
         
         out.println(answer);
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}