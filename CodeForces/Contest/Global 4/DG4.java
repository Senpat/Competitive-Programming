//make sure to make new file!
import java.io.*;
import java.util.*;

public class DG4{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 5000;
      //sieve, from g4g
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
        
      TreeSet<Integer> primes = new TreeSet<Integer>();
      for(int k = 0; k < N; k++){
         if(prime[k]){
            primes.add(k);
         }
      }

      
      int n = Integer.parseInt(f.readLine());
      int add = 0;
      if(!primes.contains(n)){
         add = primes.higher(n)-n;
      }
      
      out.println(n+add);
      for(int k = 1; k < n; k++){
         out.println(k + " " + (k+1));
      }
      out.println(n + " 1");
      
      for(int k = 2; k < 2+add; k++){
         out.println(k + " " + (n-k+2));
      }
   
      
      
      
      
      out.close();
   }
   
      
}