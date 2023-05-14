//make sure to make new file!
import java.io.*;
import java.util.*;
//both hashes got hacked
public class G855{
   
   public static long MOD = 1000000007L;
   public static long base = 200003L;
   public static long base2 = 200009L;
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static long[] subsize;
   public static long[] subhash;
   public static boolean[] issym;
   
   public static long[] powb2;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 200005;
      powb2 = new long[N];
      powb2[0] = 1L;
      for(int k = 1; k < N; k++){
         powb2[k] = (base2 * powb2[k-1] + MOD)%MOD;
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
         
         subsize = new long[n+1];
         subhash = new long[n+1];
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
   
   /*
   // old hash
   public static void gethash(int v, int p){
      ArrayList<Long> childhashes = new ArrayList<Long>();
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         gethash(nei,v);
         childhashes.add((base*subhash[nei] + MOD)%MOD);
      }
      Collections.sort(childhashes);
      
      subhash[v] = 1L;
      for(int k = 0; k < childhashes.size(); k++){
         subhash[v] = (subhash[v] + childhashes.get(k) * powb2[k+1] + MOD)%MOD;
      }
   }
   */
   
   //hash from comments
   public static void gethash(int v, int p){
      ArrayList<Long> childhashes = new ArrayList<Long>();
      subsize[v] = 1L;
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         gethash(nei,v);
         childhashes.add(subhash[nei]);
         subsize[v] += subsize[nei];
      }
      Collections.sort(childhashes);
      
      subhash[v] = subsize[v];
      for(int k = 0; k < childhashes.size(); k++){
         subhash[v] = (subhash[v] + childhashes.get(k) * powb2[k+1] + MOD)%MOD;
      }
   }
   
}