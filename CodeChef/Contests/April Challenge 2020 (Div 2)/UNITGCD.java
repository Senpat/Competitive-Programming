//make sure to make new file!
import java.io.*;
import java.util.*;
//doesnt work
public class UNITGCD{
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      //sieve, from g4g
      int N = 1000002;
      boolean prime[] = new boolean[N+1]; 
      for(int i=0;i<N;i++) 
         prime[i] = true; 
          
      for(int p = 2; p*p <=N; p++) 
      { 
            // If prime[p] is not changed, then it is a prime 
         if(prime[p]) 
         { 
                // Update all multiples of p 
            for(int i = p*p; i <= N; i += p) 
               prime[i] = false; 
         } 
      } 
      
      ArrayList<Integer> primes = new ArrayList<Integer>();
      for(int i = 2; i <= N; i++) 
      { 
         if(prime[i]) 
            primes.add(i);
      }
      
      
      
      
      
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
         ArrayList<ArrayList<Integer>> answer = new ArrayList<ArrayList<Integer>>(n/2);
         
         for(int k = 0; k < n/2; k++) answer.add(new ArrayList<Integer>());
         
         answer.get(0).add(1);
         for(int k = 0; k < primes.size(); k++){
            if(primes.get(k) <= n) answer.get(0).add(primes.get(k));
         }
         
         for(int k = 4; k <= n; k+=2){
            answer.get(k/2-1).add(k);
         }
         
         for(int p = 1; p < primes.size() && primes.get(p) <= n; p++){
            int i = 1;
            for(int k = primes.get(p); primes.get(p)*k <= n; k+=2){
               out.println(primes.get(p) + " " + k);
               while(!check(answer.get(i),primes.get(p)*k)){
                  i++;
               }
               answer.get(i).add(primes.get(p)*k);
            }
         }
         
         out.println(answer.size());
         for(ArrayList<Integer> alist : answer){
            out.print(alist.size());
            for(int i : alist){
               out.print(" " + i);
            }
            out.println();
         }
         
      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(ArrayList<Integer> alist, int i){
      for(int k = 0; k < alist.size(); k++){
         if(gcd(alist.get(k),i) != 1) 
            return false;
      }
      return true;
   }
   
   public static int gcd(int a, int b) 
   { 
     
      while(b > 0){
      
         if(b > a){
            int temp = a;
            a = b;
            b = temp;
         }
      
      
         if (b == 0) 
            return a; 
            
            
            
         int temp = a;
         a = b;
         b = temp%b;
      }
      return a;
      
      
   } 
   
}