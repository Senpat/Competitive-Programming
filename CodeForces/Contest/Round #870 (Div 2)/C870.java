//make sure to make new file!
import java.io.*;
import java.util.*;

public class C870{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 10000;
      boolean[] isprime = new boolean[N];
      Arrays.fill(isprime,true);
      
      for(int k = 2; k < N; k++){
         if(!isprime[k]) continue;
         for(int j = k*2; j < N; j += k){
            isprime[j] = false;
         }
      }
      
      ArrayList<Integer> primes = new ArrayList<Integer>();
      for(int k = 2; k < N; k++){
         if(isprime[k]) primes.add(k);
      }
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         if(n == 1){
            out.println("YES");
            continue;
         }
         
         if(n <= m){
            out.println("NO");
            continue;
         }
         
         //get smallest divisor of n
         int small = n;
         for(int p : primes){
            if(n % p == 0){
               small = p;
               break;
            }
         }
         
         if(small <= m){
            out.println("NO");
         } else {
            out.println("YES");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}