//Timofey and a flat tree
import java.io.*;
import java.util.*;
//tree hashing practice
public class D763{
   
   public static long MOD = 1000000007L;
   public static long[] bases;
   
   public static int[] depth;
   public static int[] depth2;
   public static long[] hashes;
   public static long[] hashes2;    //hashes with 2nd smallest depth
   public static HashMap<Long,Integer> hmap;
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static int vertex;
   public static long answer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 100005;
      bases = new long[N];
      for(int k = 0; k < N; k++){
         bases[k] = (long)(Math.random() * (MOD-1)) + 1;
      }
      
      int n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      hmap = new HashMap<Long,Integer>();
      hashes = new long[n+1];
      hashes2 = new long[n+1];
      depth = new int[n+1];
      depth2 = new int[n+1];
      init(1,-1);
      
      remove(hashes[1]);
      answer = 0L;
      vertex = -1;
      dfs(1,-1);
      
      out.println(vertex);
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      
      long initialhash = hashes[v];
      long initialhash2 = hashes2[v];
      int initialdepth = depth[v];
      int initialdepth2 = depth2[v];
      //recalculate hash where v is root
      int mind1 = Integer.MAX_VALUE;
      int mind2 = Integer.MAX_VALUE;
      for(int nei : adj.get(v)){
         if(depth[nei]+1 < mind1){
            mind2 = mind1;
            mind1 = depth[nei]+1;
         } else if(depth[nei] < mind2){
            mind2 = depth[nei]+1;
         }
      }
      
      if(mind1 == Integer.MAX_VALUE) mind1 = 1;
      if(mind2 == Integer.MAX_VALUE) mind2 = 1;
      
      depth[v] = mind1;
      depth2[v] = mind2;
      
      hashes[v] = 1L;
      hashes2[v] = 1L;
      for(int nei : adj.get(v)){
         long sum = (bases[depth[v]] + hashes[nei] + MOD)%MOD;
         hashes[v] = (hashes[v] * sum + MOD)%MOD;
         long sum2 = (bases[depth2[v]] + hashes[nei] + MOD)%MOD;
         hashes2[v] = (hashes2[v] * sum2 + MOD)%MOD;
      }
      
      add(hashes[v]);
      
      //System.out.println(v + ": " + hmap.size());
      if(answer < hmap.size()){
         vertex = v;
         answer = hmap.size();
      }
      
      long prevhash = hashes[v];
      int prevdepth = depth[v];
      
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         
         //update v values (nei values will be updated after dfs call)
         remove(hashes[v]);
         
         if(depth[nei] != depth[v]-1){
            long neihashterm = (bases[depth[v]] + hashes[nei] + MOD)%MOD;
            hashes[v] = (hashes[v] * modInverse(neihashterm,MOD) + MOD)%MOD;
         } else {
            long neihashterm = (bases[depth2[v]] + hashes[nei] + MOD)%MOD;
            hashes[v] = (hashes2[v] * modInverse(neihashterm,MOD) + MOD)%MOD;
            depth[v] = depth2[v];
         }
         
         add(hashes[v]);
         remove(hashes[nei]);
         
         dfs(nei,v);
         
         //undo
         add(hashes[nei]);
         remove(hashes[v]);
         hashes[v] = prevhash;
         depth[v] = prevdepth;
         add(hashes[v]);
      }
      
      remove(hashes[v]);
      hashes[v] = initialhash;
      hashes2[v] = initialhash2;
      depth[v] = initialdepth;
      depth2[v] = initialdepth2;
   }
   
   
   public static void init(int v, int p){
      
      ArrayList<Long> childhashes = new ArrayList<Long>();
      int mind1 = Integer.MAX_VALUE;
      int mind2 = Integer.MAX_VALUE;
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         init(nei,v);
         childhashes.add(hashes[nei]);
         if(depth[nei]+1 < mind1){
            mind2 = mind1;
            mind1 = depth[nei]+1;
         } else if(depth[nei] < mind2){
            mind2 = depth[nei]+1;
         }
      }
      
      if(mind1 == Integer.MAX_VALUE) mind1 = 1;
      if(mind2 == Integer.MAX_VALUE) mind2 = 1;
      
      depth[v] = mind1;
      depth2[v] = mind2;
      
      hashes[v] = 1L;
      hashes2[v] = 1L;
      for(long hash : childhashes){
         long sum = (bases[mind1] + hash + MOD)%MOD;
         hashes[v] = (hashes[v] * sum + MOD)%MOD;
         long sum2 = (bases[mind2] + hash + MOD)%MOD;
         hashes2[v] = (hashes2[v] * sum2 + MOD)%MOD;
      }
      
      add(hashes[v]);
   }
   
   public static void add(long x){
      if(hmap.containsKey(x)) hmap.put(x,hmap.get(x)+1);
      else hmap.put(x,1);
   }
   
   public static void remove(long x){
      if(hmap.get(x) == 1) hmap.remove(x);
      else hmap.put(x,hmap.get(x)-1);
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