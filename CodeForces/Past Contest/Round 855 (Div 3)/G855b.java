//make sure to make new file!
import java.io.*;
import java.util.*;
//based on G855.java but with better hashing
//http://rng-58.blogspot.com/2017/02/hashing-and-probability-of-collision.html
public class G855b{
   
   public static long MOD = 1000000007L;
   public static long[] bases;            //different base for each depth
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static int[] depth;             //distance from leaf
   public static long[] subhash;
   public static boolean[] issym;
   
   public static long[] powb2;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 200005;
      bases = new long [N];
      for(int k = 0; k < N; k++){
         bases[k] = (long)(Math.random() * (MOD-1)) + 1;          //random number in range [1,MOD-1]
      }
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

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
         
         if(n <= 3){
            out.println("YES");
            continue;
         }
         
         subhash = new long[n+1];
         depth = new int[n+1];
         gethash(1,-1);
         
         issym = new boolean[n+1];
         symdfs(1,-1);
         
         if(issym[1]){
            out.println("YES");
         } else {
            out.println("NO");
         }

      }
      
      
      
      
      out.close();
   }
   
   public static void symdfs(int v, int p){
      
      HashSet<Long> symhashes = new HashSet<Long>();
      HashMap<Long,Integer> hashes = new HashMap<Long,Integer>();
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         symdfs(nei,v);
         
         long hash = subhash[nei];
         if(issym[nei]) symhashes.add(hash);
         if(hashes.containsKey(hash)){
            hashes.put(hash,hashes.get(hash)+1);
         } else {
            hashes.put(hash,1);
         }
      }
      
      long oddhash = -1;
      for(long hash : hashes.keySet()){
         if(hashes.get(hash)%2 == 1){
            if(oddhash == -1) oddhash = hash;
            else {
               //two odd hashes, fail
               issym[v] = false;
               return;
            }
         }
      }
      
      issym[v] = (oddhash == -1 || (oddhash != -1 && symhashes.contains(oddhash)));
      
   }
   
   public static void gethash(int v, int p){
      ArrayList<Long> childhashes = new ArrayList<Long>();
      depth[v] = Integer.MAX_VALUE;
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         gethash(nei,v);
         depth[v] = Math.min(depth[v],depth[nei]+1);
         childhashes.add(subhash[nei]);
      }
      if(depth[v] == Integer.MAX_VALUE) depth[v] = 1;
      
      subhash[v] = 1L;
      for(int k = 0; k < childhashes.size(); k++){
         long sum = (bases[depth[v]] + childhashes.get(k));
         subhash[v] = (subhash[v] * sum + MOD)%MOD;
      }
   }
   
}