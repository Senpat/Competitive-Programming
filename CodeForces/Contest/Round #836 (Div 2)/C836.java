//make sure to make new file!
import java.io.*;
import java.util.*;

public class C836{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 200005;
      boolean[] isprime = new boolean[N];
      Arrays.fill(isprime,true);
      for(int k = 2; k < N; k++){
         if(!isprime[k]) continue;
         for(int j = k*2; j < N; j+=k){
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
         int x = Integer.parseInt(st.nextToken());
         
         if(n%x != 0){
            out.println("-1");
            continue;
         }
         
         int[] p = new int[n+1];
         for(int k = 1; k <= n; k++) p[k] = k;
         
         p[n] = 1;
         p[1] = x;
         
         int pi = 0;
         
         if(x != n){
            while(x*primes.get(pi) < n){
               if(x*primes.get(pi) < (long)n && n % (x*primes.get(pi)) == 0){
                  p[x] = x*primes.get(pi);
                  x *= primes.get(pi);
               } else {
                  pi++;
               }
            }
            p[x] = n;
         }
         
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 1; k <= n; k++){
            sj.add("" + p[k]);
         }
         
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}