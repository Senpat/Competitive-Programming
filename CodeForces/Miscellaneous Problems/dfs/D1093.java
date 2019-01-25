//Beautiful Graph
//semi-t
import java.io.*;
import java.util.*;

public class D1093{

   public static boolean[] seen;
   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] nums,colors;
   public static boolean notbi;
   public static long MOD = 998244353;
   public static int n,m;
   public static long[] pow2;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int qq = Integer.parseInt(f.readLine());
      
      pow2 = new long[300001];
      pow2[0] = 1;
      for(int k = 1; k < pow2.length; k++){
         pow2[k] = (pow2[k-1] << 1) % MOD;
      }
      
      for(int q = 0; q < qq; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());
      
         adj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(b);
            adj.get(b).add(a);
         }
         
         seen = new boolean[n+1];
         colors = new int[n+1];
         Arrays.fill(colors,-1);
         out.println(solve());
      
      }
      
      
      out.close();
   }
   
   public static long solve(){
      long answer = 1L;
      notbi = false;
      
      for(int k = 1; k <= n; k++){
         if(!seen[k]){
            nums = new int[2];                               //check for bipartite
            dfs(k,0);
            if(notbi) return 0;
            answer  = (answer * (((pow2[nums[0]] + pow2[nums[1]])%MOD)) + MOD) % MOD;
         }
      }
      
      return (answer+MOD)%MOD;
   }
   
   public static void dfs(int v,int c){
      if(notbi) return;
      seen[v] = true;
      nums[c]++;
      colors[v] = c;
      
      for(int nei : adj.get(v)){
         if(seen[nei] && colors[nei] == c){
            notbi = true;
            return;
         } else if(!seen[nei]){
            dfs(nei,1-c);
         }
      }
   }
      
      
      
      
   
}