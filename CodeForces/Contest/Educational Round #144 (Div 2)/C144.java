//make sure to make new file!
import java.io.*;
import java.util.*;

public class C144{

   public static long MOD = 998244353L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      //primes
      int N = 1000005;
      boolean[] isprime = new boolean[N];
      Arrays.fill(isprime,true);
      isprime[2] = true;
      for(int k = 2; k < N; k++){
         if(!isprime[k]) 
            continue;
         for(int j = 2*k; j < N; j += k){
            isprime[j] = false;
         }
      }
      
      ArrayList<Integer> primes = new ArrayList<Integer>();
      for(int k = 2; k < N; k++){
         if(isprime[k]) primes.add(k);
      }
      
      int q = Integer.parseInt(f.readLine());
      
      Query[] queries = new Query[q];
      int[] a1 = new int[q];
      for(int k = 0; k < q; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         
         queries[k] = new Query(k,l,r);
         a1[k] = queries[k].a1;
      }
      
      Arrays.sort(queries);
      
      long[] precomp = new long[N];
      ArrayList<Integer> curnums = new ArrayList<Integer>();
      for(int p : primes){
         precomp[p] = 1;
         curnums.add(p);
      }
      int done = 2;
      
      
      
      long[] a2 = new long[q];
      for(int k = 0; k < q; k++){
         int l = queries[k].l;
         int r = queries[k].r;
         
         if(queries[k].a1 == 1){
            a2[queries[k].i] = r-l+1;
            continue;
         }
         
         if(done < queries[k].a1){        //will enter at most log(N) times
            while(done < queries[k].a1){        //will loop at most log(N) times
               long[] next = new long[N];
               for(int j = 2; j < N; j++){
                  if(precomp[j] == 0L) 
                     continue;
                  for(int p : primes){
                     if(j*p >= N) 
                        break;
                     next[j*p] = next[j*p] + precomp[j];
                     if(next[j*p] >= MOD) next[j*p] -= MOD;
                  }
               }
            
               precomp = next;
                  
               done++;
            }
            
            curnums = new ArrayList<Integer>();
            
            for(int j = 2; j < N; j++){
               if(precomp[j] != 0){
                  curnums.add(j);
               }
            }
         }
         
         long answer2 = 0L;
         
         for(int i : curnums){
            int numstart = r/i - l + 1;
            if(numstart <= 0) break;
            answer2 = (answer2 + numstart * precomp[i] + MOD)%MOD;
         }
         
         a2[queries[k].i] = answer2;
         
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < q; k++){
         sj.add("" + a1[k] + " " + a2[k]);
      }
      out.println(sj.toString());
      
      
      out.close();
   }
   
   public static class Query implements Comparable<Query>{
      int i;
      int l;
      int r;
      int a1;
      public Query(int a, int b, int c){
         i = a;
         l = b;
         r = c;
         
         a1 = 1;
         int i = l;
         while(i*2 <= r){
            i *= 2;
            a1++;
         }
      }
      
      public int compareTo(Query qu){
         return a1-qu.a1;
      }
   }
   
      
}