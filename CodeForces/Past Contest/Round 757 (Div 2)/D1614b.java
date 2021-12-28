//Divan and Kostomuksha
import java.io.*;
import java.util.*;
//solves D2 (fail)
//semi-t
public class D1614b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 20000005;
      long Nl = 20000005L;
      
      boolean[] isprime = new boolean[N];
      Arrays.fill(isprime,true);
      for(int k = 2; k < N; k++){
         if(!isprime[k]) 
            continue;
         for(int j = k*2; j < N; j+=k){
            isprime[j] = false;
         }
      }
      
      ArrayList<Long> primes = new ArrayList<Long>();
      for(int k = 2; k < N; k++) 
         if(isprime[k]) primes.add((long)k);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      Long[] array = new Long[n];
      
      HashMap<Long,Long> count = new HashMap<Long,Long>();
      
      ArrayList<Long> unis = new ArrayList<Long>();                  //uniques
      
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         if(count.containsKey(array[k])) count.put(array[k],count.get(array[k])+1L);
         else {
            unis.add(array[k]);
            count.put(array[k],1L);
         }
      }
      
      Collections.sort(unis);
      
      for(int k = unis.size()-1; k >= 0; k--){
         for(int j = 0; j < primes.size() && unis.get(k)*primes.get(j) < Nl; j++){
            count.put(unis.get(k),count.get(unis.get(k))+count.getOrDefault(unis.get(k)*primes.get(j),0L));
         }
      }
      
      HashMap<Long,Long> dp = new HashMap<Long,Long>();
      
      for(int k = 0; k < unis.size(); k++){
         dp.put(unis.get(k),count.get(unis.get(k))*unis.get(k));
      }
      
      for(int k = unis.size()-1; k >= 0; k--){
         for(int j = 0; j < primes.size() && unis.get(k)*primes.get(j) < Nl; j++){
            long i = unis.get(k)*primes.get(j);
            if(!dp.containsKey(i)) 
               continue;
            dp.put(unis.get(k),Math.max(dp.get(unis.get(k)),dp.get(i)+unis.get(k)*(count.get(unis.get(k))-count.get(i))));
         }
      }
      
      long answer = 0L;
      for(long i : dp.keySet()){
         answer = Math.max(answer,dp.get(i));
      }
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}