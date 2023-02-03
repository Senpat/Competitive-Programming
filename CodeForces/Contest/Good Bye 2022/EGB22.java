//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class EGB22{

   public static long MOD = 998244353L;
   public static long i2 = 499122177L;
   
   public static boolean[] butterfly;
   public static ArrayList<ArrayList<Edge>> adj;
   public static long[] prob;       //probability that there is a butterfly
   
   public static int[] parent;
   public static long[] subbf;       //butterflies in subtree
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      long ml = (long)m;
      
      st = new StringTokenizer(f.readLine());
      butterfly = new boolean[n+1];
      prob = new long[n+1];
      for(int k = 0; k < m; k++){
         int i = Integer.parseInt(st.nextToken());
         butterfly[i] = true;
         prob[i] = 1;
      }
      
      adj = new ArrayList<ArrayList<Edge>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      Edge[] edges = new Edge[n-1];
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         edges[k] = new Edge(a,b,k);
         adj.get(a).add(new Edge(b,k));
         adj.get(b).add(new Edge(a,k));
      }
      
      parent = new int[n+1];
      subbf = new long[n+1];
      
      initdfs(1,-1);
      
      long answer = 0L;
      long atob,pnomove,nomoveval,pmove,moveval,prod;
      for(int k = 0; k < n-1; k++){
         int a = edges[k].from;
         int b = edges[k].to;
         
         //make b the parent
         if(parent[b] == a){
            int temp = a;
            a = b;
            b = temp;
         } 
         
         long ayes = prob[a];
         long ano = ((MOD+1-prob[a])%MOD + MOD)%MOD;
         long byes = prob[b];
         long bno = ((MOD+1-prob[b])%MOD + MOD)%MOD;
         
         //direction is a -> b
         
         //no butterflies move
         pnomove = (ano + ayes*byes + MOD)%MOD;
         //answer if no butterflies move
         nomoveval = (subbf[a] * (ml-subbf[a]) + MOD)%MOD;
         
         prod = (i2 * pnomove + MOD)%MOD;
         prod = (prod * nomoveval + MOD)%MOD;
         answer = (answer + prod + MOD)%MOD;
         
         //a butterfly moves (if less than 2, then val is trivially 0)
         if(subbf[a] >= 2){
            pmove = (ayes*bno + MOD)%MOD;
            moveval = ((subbf[a]-1) * (ml-subbf[a]+1) + MOD)%MOD;
            
            prod = (i2 * pmove + MOD)%MOD;
            prod = (prod * moveval + MOD)%MOD;
            answer = (answer + prod + MOD)%MOD;
         }
         
         //direction is b -> a
         pnomove = (bno + byes * ayes + MOD)%MOD;
         nomoveval = ((ml-subbf[a]) * subbf[a] + MOD)%MOD;
         
         prod = (i2 * pnomove + MOD)%MOD;
         prod = (prod * nomoveval + MOD)%MOD;
         answer = (answer + prod + MOD)%MOD;
         
         if(ml-subbf[a] >= 2){
            pmove = (byes * ano + MOD)%MOD;
            moveval = ((ml-subbf[a]-1) * (subbf[a] + 1) + MOD)%MOD;
            
            prod = (i2 * pmove + MOD)%MOD;
            prod = (prod * moveval + MOD)%MOD;
            answer = (answer + prod + MOD)%MOD;  
         }
         
         //a -> b
         //a = i2 * (ayes * byes), b = i2 * (byes + ayes * bno)
         long proda1 = (ayes * byes + MOD)%MOD; 
         proda1 = (proda1 * i2 + MOD)%MOD;
         long prodb1 = (byes + ayes * bno + MOD)%MOD;
         prodb1 = (prodb1 * i2 + MOD)%MOD;
         
         //b -> a
         //a = (ayes + ano * byes), b = (ayes * byes)
         long proda2 = (ayes + ano * byes + MOD)%MOD;
         proda2 = (proda2 * i2 + MOD)%MOD;
         long prodb2 = (ayes * byes + MOD)%MOD;
         prodb2 = (prodb2 * i2 + MOD)%MOD;
         
         prob[a] = (proda1 + proda2 + MOD)%MOD;
         prob[b] = (prodb1 + prodb2 + MOD)%MOD;
         
      }
      
      long denom = (ml * (ml-1) + MOD)%MOD;
      denom = (denom * i2 + MOD)%MOD;
      answer = (answer * modInverse(denom,MOD) + MOD)%MOD;
      
      
      
      out.println(answer);
      
      
      out.close();
   }
   
   public static void initdfs(int v, int p){
      parent[v] = p;
      
      for(Edge e : adj.get(v)){
         if(e.to == p) continue;
         initdfs(e.to,v);
         subbf[v] += subbf[e.to];
      }
      
      if(butterfly[v]) subbf[v]++;
   }
   
   public static class Edge{
      int from;
      int to;
      int i;
      public Edge(int b, int c){
         to = b;
         i = c;
      }
      public Edge(int a, int b, int c){
         from = a;
         to = b;
         i = c;
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