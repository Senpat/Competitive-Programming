//make sure to make new file!
import java.io.*;
import java.util.*;
//danny's sol
public class K{

   public static int n;
   public static long mod;
   public static long[] pow10;
   public static long[] pow10i;
   public static long[] pow10_25;

   public static ArrayList<ArrayList<Integer>> adj;
   public static ArrayList<ArrayList<Integer>> children;

   public static long[] values;
   
   public static int t;
   public static int[] tin;
   public static int[] tout;
   public static int[] parents;
   
   public static int fwn;              //fenwick tree n
   public static long[][] bits;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      mod = Long.parseLong(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      //split mod into mod25 and mod, mod25 is the part of mod that is pow of 2 and 5
      long mod25 = 1L;
      while(mod % 2L == 0L){
         mod /= 2L;
         mod25 *= 2L;
      }
      
      while(mod % 5L == 0L){
         mod /= 5L;
         mod25 *= 5L;
      }
      
      int N = 200005;
      pow10 = new long[N];
      pow10i = new long[N];
      pow10[0] = 1L;
      pow10i[0] = 1L;
      for(int k = 1; k < N; k++){
         pow10[k] = (10L * pow10[k-1] + mod)%mod;
         pow10i[k] = modInverse(pow10[k],mod);
      }
      
      int N25 = 30;
      pow10_25 = new long[N25];
      pow10_25[0] = 1L;
      for(int k = 1; k < N25; k++){
         pow10_25[k] = (10L * pow10_25[k-1] + mod25)%mod25;
      }
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      children = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Integer>());
         children.add(new ArrayList<Integer>());
      }
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      values = new long[n+1];
      for(int k = 1; k <= n; k++){
         values[k] = Long.parseLong(f.readLine());
      }
      
      //euler tour precomp
      t = 1;
      tin = new int[n+1];
      tout = new int[n+1];
      parents = new int[n+1];
      
      dfs(1,-1);
      
      //LCA PRECOMP 
      lca = new int[n+1][MAXD];
      depth = new int[n+1];
      depth[1] = 0;
      initdfs(1,-1);
      initLCA();
      
      //fenwick tree precomp
      fwn = 2*t;
      bits = new long[fwn+1][2];                   //0 is on the way up (decreasing pow of 10), 1 is on the way down (increasing pow of 10) 
      
      for(int k = 1; k <= n; k++){
         //up
         update(tin[k],(values[k]*pow10i[depth[k]] + mod)%mod, 0);
         update(tout[k],((-1*values[k]*pow10i[depth[k]])%mod + mod)%mod, 0);
         
         //down
         update(tin[k],(values[k]*pow10[depth[k]] + mod)%mod, 1);
         update(tout[k],((-1*values[k]*pow10[depth[k]])%mod + mod)%mod, 1);
      }
      
      
      for(int qq = 0; qq < q; qq++){
         st = new StringTokenizer(f.readLine());
      
         int b = Integer.parseInt(st.nextToken());
         int a = Integer.parseInt(st.nextToken());
         
         int lca = lca(a,b);
         
         //calculate coprime from 10 part
         //up part is a to lca
         long uppart = ((psum(tin[a],0) - psum(tin[lca]-1,0))%mod + mod)%mod;
         //adjust
         uppart = (uppart * pow10[depth[a]] + mod)%mod;
         
         //down part is lca to b
         long downpart = ((psum(tin[b],1) - psum(tin[lca]-1,1))%mod + mod)%mod;
         //adjust
         int adjust = -1*(depth[lca] - (depth[a]-depth[lca]));
         //downpart = (downpart * pow10i[depth[a]-depth[lca]] + mod)%mod;
         //downpart = (downpart * pow10i[depth[a]] + mod)%mod;
         
         if(adjust > 0){
            downpart = (downpart * pow10[adjust] + mod)%mod;
         }
         if(adjust < 0){
            downpart = (downpart * pow10i[adjust*-1] + mod)%mod;
         }
         
         
         //value at lca (because it is overcountted)
         long lcapart = values[lca]*pow10[depth[a]-depth[lca]];
         
         //answer for coprime part
         long cpanswer = ((uppart+downpart-lcapart)%mod + mod)%mod;
         
         //path of only last 30 numbers
         ArrayList<Integer> path = getlast(a, b, lca);
         
         long ncpanswer = 0L;
         for(int k = 0; k < path.size(); k++){
            long prod = (values[path.get(k)]*pow10_25[k] + mod25)%mod25;
            ncpanswer = (ncpanswer + prod + mod25)%mod25;
         }
         
         long answer = crt(cpanswer,mod,ncpanswer,mod25);
         out.println(answer);
         
      }
         
      
      
      
      
      
      
      
      out.close();
   }
   
   //get last 30 elements in the path from a to b with lca lca
   public static ArrayList<Integer> getlast(int a, int b, int lca){
      int N = 30;
      
      ArrayList<Integer> path = new ArrayList<Integer>();
      
      //go from a to lca
      int i = a;
      while(N > 0 && i != lca){
         path.add(i);
         i = parents[i];
         N--;
      }
      
      if(N > 0){
         path.add(lca);
         N--;
      }
      
      if(N == 0) return path;
      
      //go from lca to b
      i = lca;
      while(N > 0 && i != b){
         //find child
         int l = 0;
         int r = adj.get(i).size()-1;
         int ans = -1;
         while(l <= r){
            int mid = l + (r-l)/2;
            int cur = children.get(i).get(mid);
            if(tin[cur] <= tin[b] && tout[cur] >= tout[b]){
               ans = cur;
               break;
            }
            
            if(tout[cur] < tin[b]){
               l = mid+1;
            } else {
               r = mid-1;
            }
         }
         
         path.add(ans);
         i = ans;
         N--;
      }
      
      return path;
   }
   
   
   //euler tour
   public static void dfs(int v, int p){
      parents[v] = p;
   
      tin[v] = t;
      t++;
      
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         children.get(v).add(nei);
         dfs(nei,v);
      }
      
      tout[v] = t;
      t++;
   
   }
   
   
   //fenwick tree
   public static void update(int i, long x, int id){
      for(; i <= fwn; i += i&-i){
         //System.out.println(i);
         bits[i][id] = (bits[i][id] + x + mod)%mod;
      }
   
   }
   
   public static long psum(int i, int id){
      long sum = 0L;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum = (sum + bits[i][id] + mod)%mod;
      }
      return sum;
   
   }
   
   //lca and dist stuff
   public static int MAXD = 18;
   public static int[][] lca;
   public static int[] depth;
   
   public static void initLCA() {
      for(int d = 1; d < MAXD; d++) {
         for(int i = 1; i < n+1; i++) {
            lca[i][d] = lca[lca[i][d-1]][d-1];
         }
      }
   }
   
   public static int lca(int a, int b){
      
      if(depth[a] < depth[b]){
         //swap a and b
         int temp = a;
         a = b;
         b = temp;
      }
      
      for(int i = MAXD-1; i >= 0; i--){
         if (((depth[a]-depth[b]) & (1<<i)) != 0){
         //if(depth[a] - (1<<i) > depth[b]){
            a = lca[a][i];
         }
      }
      if(a==b) 
         return a;
      
      for(int i = MAXD-1; i >= 0; i--){
         if(lca[a][i] != lca[b][i]){
            a = lca[a][i];
            b = lca[b][i];
         }
      }
      return lca[a][0];
   }
            
   
   
   public static int dist(int a, int b){
      //System.out.println("lca: " + lca(a,b));
      return depth[a] + depth[b] - 2*depth[lca(a,b)];
   }
   
   public static void initdfs(int v, int p){
      
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         depth[nei] = depth[v]+1;
         lca[nei][0] = v;
         initdfs(nei,v);
      }
   }
   
           //from geeksforgeeks
   public static long modInverse(long a, long m) 
   { 
       long m0 = m; 
       long y = 0;
       long x = 1; 
     
       if (m == 1) 
         return 0; 
     
       while (a > 1) 
       { 
           // q is quotient 
           long q = a / m; 
           long tt = m; 
     
           // m is remainder now, process same as 
           // Euclid's algo 
           m = a % m;
           a = tt; 
           tt = y; 
     
           // Update y and x 
           y = x - q * y; 
           x = tt; 
       } 
     
       // Make x positive 
       if (x < 0) 
          x += m0; 
       return x; 
   }  
      
   /*Chinese Remainder Theorem - solves:
   x = a1 (mod n1)
   x = a2 (mod n2)
   */
   public static long crt(long a1, long n1, long a2, long n2){
      
      long d = gcd(n1,n2);
      long lcm = n1*n2/d;
      
      //x = a1 + n1*k1
      
      //calculate x' using Extended Euclidean Algorithm
      long xprime = exgcd(n1,n2);
      
   
      long prod1 = (xprime * (a2-a1)/d + (n2/d)) % (n2/d);
      long prod2 = (prod1 * n1 + lcm)%lcm;
      long answer = (prod2 + a1 + lcm)%lcm;
      
      return answer;
   }
   
   //https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm
   //ONLY RETURNS X'
   public static long exgcd(long a, long b){
      long s = 0;
      long r = b;
      long old_s = 1;
      long old_r = a;
      
      long temp;
      while(r != 0){
         long quotient = old_r / r;
         temp = r;
         r = old_r - quotient*temp;
         old_r = temp;
         
         temp = s;
         s = old_s - quotient*temp;
         old_s = temp;
      }
      
      return old_s;
   }
   
   public static long gcd(long a, long b){
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