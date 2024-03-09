//make sure to make new file!
import java.io.*;
import java.util.*;
//bug: count > 1 instead of count > 0 in prime fac
public class D1493{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 200005;
      boolean[] isprime = new boolean[N];
      Arrays.fill(isprime,true);
      for(int k = 2; k < N; k++){
         if(isprime[k]){
            for(int j = 2*k; j < N; j += k){
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
      int P = primes.size();
      
      HashMap<Long,Integer> pindexof = new HashMap<Long,Integer>();
      for(int k = 0; k < P; k++){
         pindexof.put(primes.get(k),k);
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      ArrayList<HashMap<Long,Integer>> pfacs = new ArrayList<HashMap<Long,Integer>>(n);
      for(int k = 0; k < n; k++) pfacs.add(new HashMap<Long,Integer>());
      
      ArrayList<PriorityQueue<Prime>> pqs = new ArrayList<PriorityQueue<Prime>>(P);
      for(int k = 0; k < P; k++) pqs.add(new PriorityQueue<Prime>());
      
      long[] array = new long[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         
         long i = array[k];
         for(int p = 0; p < P; p++){
            if(primes.get(p)*primes.get(p) > i) 
               break;
            int count = 0;
            while(i % primes.get(p) == 0){
               i /= primes.get(p);
               count++;
            }
            
            if(count > 0){
               add(pfacs.get(k),primes.get(p),count);
               pqs.get(p).add(new Prime(k,count));
            }
         }
         
         if(i > 1L){
            add(pfacs.get(k),i,1);
            pqs.get(pindexof.get(i)).add(new Prime(k,1));
         }
      }
      
      long answer = 1L;
      
      //previous min of prime that has already been included in the answer
      int[] curmin = new int[P];
      for(int k = 0; k < P; k++){
         if(pqs.get(k).size() == n){
            for(int j = 0; j < pqs.get(k).peek().freq; j++){
               //don't need to mod here
               answer *= primes.get(k);      
            }
            curmin[k] = pqs.get(k).peek().freq;
         }
      }
      
      for(int qq = 0; qq < q; qq++){
         st = new StringTokenizer(f.readLine());
         
         int index = Integer.parseInt(st.nextToken())-1;
         long x = Long.parseLong(st.nextToken());
         
         long i = x;
         
         ArrayList<Pair> changes = new ArrayList<Pair>();
         
         for(int p = 0; p < P; p++){
            if(primes.get(p)*primes.get(p) > i) 
               break;
            
            int count = 0;
            while(i % primes.get(p) == 0){
               i /= primes.get(p);
               count++;
            }
            
            if(count > 0){
               changes.add(new Pair(p,count));
            }
         }
         
         if(i > 1){
            changes.add(new Pair(pindexof.get(i),1));
         }
         
         for(Pair pair : changes){
            int p = pair.p;
            int count = pair.count;
            if(!pfacs.get(index).containsKey(primes.get(p))){
               pqs.get(p).add(new Prime(index,count));
            }
            add(pfacs.get(index),primes.get(p),count);
               
            //update answer
            if(pqs.get(p).size() == n){
               while(pfacs.get(pqs.get(p).peek().i).get(primes.get(p)) != pqs.get(p).peek().freq){
                  Prime prime = pqs.get(p).poll();
                  pqs.get(p).add(new Prime(prime.i,pfacs.get(prime.i).get(primes.get(p))));
               }
                  
               //won't multiply more than log times per query
               for(int k = curmin[p]; k < pqs.get(p).peek().freq; k++){
                  answer = (answer * primes.get(p) + MOD)%MOD;
               }
               curmin[p] = pqs.get(p).peek().freq;
            }
         }
         
         out.println(answer);
      }
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void add(HashMap<Long,Integer> hmap, long key, int x){
      if(!hmap.containsKey(key)) hmap.put(key,x);
      else hmap.put(key,hmap.get(key)+x);
   }
   
   public static class Prime implements Comparable<Prime>{
      int i;
      int freq;
      
      public Prime(int a, int b){
         i = a;
         freq = b;
      }
      
      //sort by freq
      public int compareTo(Prime p){
         return freq-p.freq;
      }
   }
   
   public static class Pair{
      int p;
      int count;
      public Pair(int a, int b){
         p = a;
         count = b;
      }
   }
      
}