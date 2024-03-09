//make sure to make new file!
import java.io.*;
import java.util.*;
//no cycles
//and n <= 11
public class P1{
   
   public static long MOD = 1000000007L;
   
   public static ArrayList<ArrayList<Edge>> adj;
   public static long[] p;
   
   public static long[] answer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         f.readLine();
         
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         p = new long[n+1];
         st = new StringTokenizer(f.readLine());
         for(int k = 1; k <= n; k++){
            p[k] = Long.parseLong(st.nextToken());
         }
         
         adj = new ArrayList<ArrayList<Edge>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(new Edge(b,k));
            adj.get(b).add(new Edge(a,k));
         }
         
         answer = new long[n+1];
         
         if(n <= 11){
            int pm = (1 << m);
            long[][] dp = new long[n+1][pm];
            boolean[][] seen = new boolean[n+1][pm];
            dp[1][0] = 1L;
            seen[1][0] = true;
            
            Queue<State> qu = new LinkedList<State>();
            qu.add(new State(1,0));
            
            while(!qu.isEmpty()){
               State s = qu.poll();
               
               long numchildren = 0L;
               for(Edge e : adj.get(s.v)){
                  if(((s.mask >> e.i)&1) == 0) numchildren++;
               }
               
               long pin = dp[s.v][s.mask];
               if(numchildren == 0L){
                  answer[s.v] = (answer[s.v] + pin + MOD)%MOD;
                  continue;
               }
               
               long denom = modInverse(numchildren,MOD);
               long pout = (pin * inv(p[s.v]) + MOD)%MOD;
               pout = (pout * denom + MOD)%MOD;
               
               for(Edge e : adj.get(s.v)){
                  if(((s.mask >> e.i)&1) == 1) continue;
                  int newmask = s.mask ^ (1 << e.i);
                  
                  dp[e.to][newmask] = (dp[e.to][newmask] + pout + MOD)%MOD;
                  if(!seen[e.to][newmask]){
                     qu.add(new State(e.to,newmask));
                     seen[e.to][newmask] = true;
                  }
               }
            }
            
         } else {  
            dfs(1,-1,1L);
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 1; k <= n; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
         
      }
      
      
      
      
      out.close();
   }
   
   
   public static void dfs(int v, int par, long pin){
      answer[v] = (pin * p[v] + MOD)%MOD;
      
      long numchildren = 0L;
      for(Edge e : adj.get(v)){
         if(e.to == par) continue;
         numchildren++;
      }
      
      if(numchildren == 0L) answer[v] = pin;
      
      long denom = modInverse(numchildren,MOD);
      long pout = (pin * inv(p[v]) + MOD)%MOD;
      pout = (pout * denom + MOD)%MOD;
      for(Edge e : adj.get(v)){
         if(e.to == par) continue;
         dfs(e.to,v,pout);
      }
   }
   
   
   public static long inv(long x){
      long ret = MOD+1-x;
      if(ret >= MOD) ret -= MOD;
      return ret;
   }
   
   
   public static class Edge{
      int to;
      int i;
      public Edge(int a, int b){
         to = a;
         i = b;
      }
   }
   
   
   public static class State{
      int v;
      int mask;
      public State(int a, int b){
         v = a;
         mask = b;
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
           long t = m; 
     
           // m is remainder now, process same as 
           // Euclid's algo 
           m = a % m;
           a = t; 
           t = y; 
     
           // Update y and x 
           y = x - q * y; 
           x = t; 
       } 
     
       // Make x positive 
       if (x < 0) 
          x += m0; 
       return x; 
   }  

   
     
      
}