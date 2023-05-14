//Labeling the Tree with Distances
import java.io.*;
import java.util.*;

public class E1794{

   public static int B = 3;
   public static long[] MOD = {1000000007L,1000000009L,998244353L};
   public static long[] base = {200003L,200009L,200017L};
   
   public static ArrayList<HashSet<Long>> targethashes;
   
   public static long[][] pow;
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static long[][] subhash;          //subhash[x] = hash of subtree of where x is root
   public static long[] backhash;           //away from where you are going toward (depth +1)
   public static ArrayList<Integer> answer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 200005;
      pow = new long[N][B];
      for(int k = 0; k < B; k++) pow[0][k] = 1L;
      for(int k = 1; k < N; k++){
         for(int b = 0; b < B; b++){
            pow[k][b] = (pow[k-1][b] * base[b] + MOD[b])%MOD[b];
         }
      }
      
      int n = Integer.parseInt(f.readLine());
      
      int[] array = new int[n-1];
      long[] initialhash = new long[B];
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n-1; k++){
         array[k] = Integer.parseInt(st.nextToken());
         for(int b = 0; b < B; b++){
            initialhash[b] = (initialhash[b] + pow[array[k]][b] + MOD[b])%MOD[b];
         }
      }
      
      targethashes = new ArrayList<HashSet<Long>>();
      
      for(int b = 0; b < B; b++){
         HashSet<Long> curhset = new HashSet<Long>();
         for(int k = 0; k < N; k++){
            curhset.add((initialhash[b] + pow[k][b] + MOD[b])%MOD[b]);
         }
         targethashes.add(curhset);
      }
      
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      subhash = new long[n+1][B];
      
      gethash(1,-1);
      
      backhash = new long[B];
      answer = new ArrayList<Integer>();
      dfs(1,-1,subhash[1]);
      
      Collections.sort(answer);
      out.println(answer.size());
      StringJoiner sj = new StringJoiner(" ");
      for(int i : answer){
         sj.add("" + i);
      }
      out.println(sj.toString());
      
      
      out.close();
   }
   
   public static void dfs(int v, int p, long[] hash){
      boolean fail = false;
      long[] prevbackhash = new long[B];
      for(int b = 0; b < B; b++){
         prevbackhash[b] = backhash[b];
         if(!targethashes.get(b).contains(hash[b])) fail = true;
      }
      
      if(!fail) answer.add(v);
      
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         
         long[] curhash = new long[B];
         for(int b = 0; b < B; b++){
            long neihashcontrib = (subhash[nei][b]*base[b] + MOD[b])%MOD[b];               //part of hashes[v] that nei contributes
            backhash[b] = ((backhash[b] + subhash[v][b] - neihashcontrib)%MOD[b] + MOD[b])%MOD[b];
            backhash[b] = (backhash[b] * base[b] + MOD[b])%MOD[b];
            curhash[b] = (backhash[b] + subhash[nei][b] + MOD[b])%MOD[b];
         }
         
         dfs(nei,v,curhash);
         
         for(int b = 0; b < B; b++){
            backhash[b] = prevbackhash[b];
         }
      } 
         
   }
   
   public static void gethash(int v, int p){
      
      Arrays.fill(subhash[v],1L);
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         gethash(nei,v);
         for(int b = 0; b < B; b++){
            subhash[v][b] = (subhash[v][b] + base[b] * subhash[nei][b] + MOD[b])%MOD[b];
         }
      }
   }
   
      
}

