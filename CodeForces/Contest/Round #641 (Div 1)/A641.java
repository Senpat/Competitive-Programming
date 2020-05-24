//make sure to make new file!
import java.io.*;
import java.util.*;

public class A641{

   public static ArrayList<Long> primes;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      primes = new ArrayList<Long>();
      sieve(200005);
      
      HashMap<Long,PriorityQueue<Integer>> hmap = new HashMap<Long,PriorityQueue<Integer>>();
      
      for(int k = 0; k < n; k++){
         long i = array[k];
         for(long p : primes){
            if(p*p > i) 
               break;
            int cur = 0;
            while(i%p == 0){
               cur++;
               i/=p;
            }
            
            if(hmap.containsKey(p)){
               hmap.get(p).add(cur);
            } else {
               PriorityQueue<Integer> temp = new PriorityQueue<Integer>();
               temp.add(cur);
               hmap.put(p,temp);
            }
         }
         
         if(i > 1){
            if(hmap.containsKey(i)){
               hmap.get(i).add(1);
            } else {
               PriorityQueue<Integer> temp = new PriorityQueue<Integer>();
               temp.add(1);
               hmap.put(i,temp);
            }
         }
      }
      
      long answer = 1L;
      
      for(long i : hmap.keySet()){
         if(hmap.get(i).size() == n-1){
            int t = hmap.get(i).poll();
            for(int k = 0; k < t; k++){
               answer *= i;
            }
         } else if(hmap.get(i).size() == n){
            hmap.get(i).poll();
            int t = hmap.get(i).poll();
            for(int k = 0; k < t; k++){
               answer *= i;
            }
         }
      }
      
      
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
   public static void sieve(int n) 
   { 
      boolean prime[] = new boolean[n+1]; 
      for(int i=0;i<n;i++) 
         prime[i] = true; 
          
      for(int p = 2; p*p <=n; p++) 
      { 
         if(prime[p] == true) 
         { 
                // Update all multiples of p 
            for(int i = p*p; i <= n; i += p) 
               prime[i] = false; 
         } 
      } 
          
        // Print all prime numbers 
      for(int i = 2; i <= n; i++) 
      { 
         if(prime[i] == true) primes.add((long)i);
      } 
   } 
   
      
}