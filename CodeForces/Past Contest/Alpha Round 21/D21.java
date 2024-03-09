//make sure to make new file!
import java.io.*;
import java.util.*;

public class D21{

   public static int n;
   public static int[][] adj;
   public static boolean[] seen;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      //stores min dist
      adj = new int[n][n];
      int[][] dist = new int[n][n];         //for floyd warshall
      for(int k = 0; k < n; k++){
         Arrays.fill(adj[k],Integer.MAX_VALUE);
         Arrays.fill(dist[k],Integer.MAX_VALUE);
      }
      
      int[] degree = new int[n];
      
      int wsum = 0;
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken())-1;
         int b = Integer.parseInt(st.nextToken())-1;
         int w = Integer.parseInt(st.nextToken());
         
         adj[a][b] = Math.min(adj[a][b],w);
         adj[b][a] = Math.min(adj[b][a],w);
         dist[a][b] = adj[a][b];
         dist[b][a] = adj[b][a];
         
         degree[a]++;
         degree[b]++;
         
         wsum += w;
      }
      
      //check that all edges can be reached
      seen = new boolean[n];
      dfs(0);
      
      boolean fail = false;
      for(int k = 0; k < n; k++){
         if(!seen[k] && degree[k] > 0){
            fail = true;
            break;
         }
      }
      
      if(fail){
         out.println("-1");
         out.close();
         return;
      }
      
      //floyd warshall
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            for(int h = 0; h < n; h++){
               if(k == j || k == h || h == j) continue;
               if(dist[j][k] == Integer.MAX_VALUE || dist[k][h] == Integer.MAX_VALUE) continue;
               
               dist[j][h] = Math.min(dist[j][h],dist[j][k] + dist[k][h]);
            }
         }
      }
      
      int pn = (1 << n);
      int[] dp = new int[pn];
      Arrays.fill(dp,Integer.MAX_VALUE);
      
      int startmask = 0;
      for(int k = 0; k < n; k++){
         if(degree[k] % 2 == 0){
            startmask += (1 << k);
         }
      }
      
      dp[startmask] = 0;
      
      for(int mask = 0; mask < pn; mask++){
         if(dp[mask] == Integer.MAX_VALUE) continue;
         for(int i1 = 0; i1 < n; i1++){
            for(int i2 = i1+1; i2 < n; i2++){
               if((mask & (1 << i1)) != 0 || (mask & (1 << i2)) != 0) continue;
               
               int newmask = mask | (1 << i1) | (1 << i2);
               dp[newmask] = Math.min(dp[newmask],dp[mask] + dist[i1][i2]);
            }
         }
      }
      
      int answer = wsum + dp[pn-1];
      out.println(answer); 
      
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      seen[v] = true;
      
      for(int nei = 0; nei < n; nei++){
         if(adj[v][nei] != Integer.MAX_VALUE && !seen[nei]){
            dfs(nei);
         }
      }
   }
   
      
}