//make sure to make new file!
import java.io.*;
import java.util.*;

public class F632{
   
   public static HashSet<Integer> primes;   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      primes = new HashSet<Integer>();
      int numprimes = sieve(n);
      ArrayList<Integer> sortedprimes = new ArrayList<Integer>();
      for(int i : primes){
         sortedprimes.add(i);
      }
      Collections.sort(sortedprimes);
      //out.println(numprimes);
      ArrayList<Integer> answer = new ArrayList<Integer>();
      for(int k = 0; k < numprimes; k++){
         answer.add(1);
      }
      
      HashMap<Integer,Integer> hmap = new HashMap<Integer,Integer>();
      for(int k = 2; k*2 <= n; k++){
         if(primes.contains(k)){
            for(int j = 0; sortedprimes.get(j) <= k; j++){
               if(k*sortedprimes.get(j) <= n){
                  answer.add(k);
               } else {
                  break;
               }
            }
            hmap.put(k*k,k);
         } else if(hmap.containsKey(k)){
            for(int j = 0; sortedprimes.get(j) <= hmap.get(k); j++){
               if(k*sortedprimes.get(j) <= n){
                  answer.add(k);
               }
            }
            hmap.put(k*hmap.get(k),hmap.get(k));
         }
         else {
            if(k*2 <= n){
               answer.add(k);
            }
         }
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int i : answer){
         sj.add("" + i);
      }
      
      out.println(sj.toString());
      
      
      
   
      
      
      
      
      
      out.close();
   }
   
   public static int sieve(int n) 
   { 
      int N = 500005;
      boolean prime[] = new boolean[N+1]; 
      for(int i=0;i<N;i++) 
         prime[i] = true; 
          
      for(int p = 2; p*p <=N; p++) 
      { 
         if(prime[p] == true) 
         { 
            for(int i = p*p; i <= N; i += p) 
               prime[i] = false; 
         } 
      } 
        
      int ret = 0;
      for(int i = 2; i <= n; i++) 
      { 
         if(prime[i] == true){
            ret++;
         }
      } 
        
      for(int i = 2; i <= N; i++){
         if(prime[i] == true){
            primes.add(i);
         }
      }
        
      return ret;
   } 
   
      
}