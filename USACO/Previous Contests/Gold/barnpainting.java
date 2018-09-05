/*
TASK: barnpainting
LANG: JAVA
*/
//ucf
import java.io.*;
import java.util.*;

class barnpainting{

   public static long MOD = 1000000007L;
   
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("barnpainting.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barnpainting.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      Arrays.fill(array,-1);
      
      ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n);
      for(int k = 0; k < n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken())-1;
         int b = Integer.parseInt(st.nextToken())-1;
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         array[Integer.parseInt(st.nextToken())-1] = Integer.parseInt(st.nextToken());
      }
      
      for(int k = 0; k < n; k++){
         for(int nei : adj.get(k)){
            if(array[k]==array[nei] && array[k] != -1){
               System.out.println(0L);
               out.println(0);
               out.close();
               System.exit(0);
            }
         }
      }
      
      long[][] dp = new long[n][4];
      
      //bfs
      
      int[] border = new int[n];
      boolean[] seen = new boolean[n];
      Queue<Integer> q = new LinkedList<Integer>();
      int[] parent = new int[n];
      int index = 0;
      Arrays.fill(parent,-1);
      
      q.add(0);
      seen[0] = true;
      while(!q.isEmpty()){
         int cur = q.poll();
         border[index++] = cur;
         
         for(int nei : adj.get(cur)){
            if(!seen[nei]){
               q.add(nei);
               parent[nei] = cur;
               seen[nei] = true;
            }
         }
      }
      
      //dp
      for(int k = n-1; k >= 0; k--){
         int v = border[k];
         
         for(int j = 1; j <= 3; j++){
            if(array[v]!=-1 && array[v] != j) continue;
            
            long ret = 1L;
            
            for(Integer nei : adj.get(v)){
               if(nei!=parent[v]){
                  ret=(ret*(dp[nei][0]-dp[nei][j]+MOD))%MOD;
               }
            }
            
            dp[v][j] = ret;
         }
         
         dp[v][0] = (dp[v][1] + dp[v][2] + dp[v][3])%MOD;
      }
      
      System.out.println(dp[0][0]);
      out.println(dp[0][0]);
      
      out.close();
   }
}