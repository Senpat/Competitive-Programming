//make sure to make new file!
import java.io.*;
import java.util.*;
//based on B687, uses prime factorization trick
public class B687b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      //sieve
      int N = 1000005;
      boolean[] isprime = new boolean[N];
      int[] sp = new int[N];
      Arrays.fill(isprime,true);
      for(int k = 2; k < N; k++){
         if(!isprime[k]) continue;
         sp[k] = k;
         for(int p = 2*k; p < N; p += k){
            if(isprime[p]){
               sp[p] = k;
            }
            isprime[p] = false;
         }
      }
      
      ArrayList<Integer> primes = new ArrayList<Integer>();
      //HashMap<Integer,Integer> maxfreq = new HashMap<Integer,Integer>();
      int[] maxfreq = new int[N];
      
      for(int k = 2; k < N; k++){
         if(isprime[k]){
            primes.add(k);
            //maxfreq.put(k,0);
         }
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      
      HashSet<Integer> hset = new HashSet<Integer>();
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         
         int prevprime = -1;
         int prevcount = 0;
         
         int i = array[k];
         
         while(i > 1){
            
            int curprime = sp[i];
            
            if(prevprime != curprime){
               if(prevprime != -1){
                  maxfreq[prevprime] = Math.max(maxfreq[prevprime],prevcount);
               }
               prevprime = curprime;
               prevcount = 1;
            } else {
               prevcount++;
            }
            
            i /= curprime;
         }
         
         if(prevprime != -1) maxfreq[prevprime] = Math.max(maxfreq[prevprime],prevcount);
      }
      
      int i = m;
      boolean fail = false;
      for(int p : primes){
         if(p*p > i) break;
         int count = 0;
         while(i%p == 0){
            count++;
            i/=p;
         }
         if(maxfreq[p] < count){
            fail = true;
         }
      }
      
      if(i > 1){
         if(maxfreq[i] < 1){
            fail = true;
         }
      }
      
      if(fail){
         out.println("No");
      } else {
         out.println("Yes");
      }
            
            
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static int gcd(int a, int b){
      if(a > b){
         if(b == 0) return a;
         return gcd(a%b,b);
      } else if(a < b){
         if(a == 0) return b;
         return gcd(b%a,a);
      }
      return a;
   }
      
}