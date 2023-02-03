//make sure to make new file!
import java.io.*;
import java.util.*;

public class D845{
   
   public static long MOD = 1000000007L;
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] depths;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      int N = 200005;
      long[] pow2 = new long[N];
      pow2[0] = 1L;
      for(int k = 1; k < N; k++){
         pow2[k] = (2L * pow2[k-1] + MOD)%MOD;
      }
      
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
         
         depths = new int[n+1];
         dfs(1,-1);
         
         long sumdepths = 0L;
         for(int k = 1; k <= n; k++){
            sumdepths = (sumdepths + depths[k] + MOD)%MOD;
         }
         
         long answer = (sumdepths * pow2[n-1] + MOD)%MOD;
         out.println(answer);
      
      }
      
      
      
      
      out.close();
   }
   
   
   public static void dfs(int v, int p){
      int max = 0;
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfs(nei,v);
         max = Math.max(max,depths[nei]);
      }
      
      depths[v] = max+1;
   
   }
      
}