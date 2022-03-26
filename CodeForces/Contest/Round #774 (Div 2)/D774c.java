//make sure to make new file!
import java.io.*;
import java.util.*;

public class D774c{

   public static ArrayList<ArrayList<Integer>> adj;
   
   public static int[][] dp;
   public static long[][] dpsum;
   
   public static ArrayList<ArrayList<Integer>> paradj;
   
   public static int[] answer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      if(n==2){
         out.println("2 2");
         out.println("1 1");
         out.close();
         return;
      }
   
      paradj = new ArrayList<ArrayList<Integer>>(2*n+1);                //v*2-1 for 0, v*2 for 1
      for(int k = 0; k <= 2*n; k++) paradj.add(new ArrayList<Integer>());
      
      dp = new int[n+1][2];
      dpsum = new long[n+1][2];
      
      dfs(1,-1);
      
      int startnode;
      if(dp[1][1] > dp[1][0] || (dp[1][1] == dp[1][0] && dpsum[1][1] < dpsum[1][0])){
         startnode = 2;
         out.println(dp[1][1] + " " + dpsum[1][1]);
      } else {
         startnode = 1;
         out.println(dp[1][0] + " " + dpsum[1][0]);
      }
      
      answer = new int[n+1];
      backtrack(startnode);
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 1; k <= n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      out.close();
   }
   
   public static void backtrack(int v){
      if(v%2 == 1){
         //use
         answer[(v+1)/2] = adj.get((v+1)/2).size(); 
      } else {
         //sacrificial lamb
         answer[v/2] = 1;
      }
      
      for(int nei : paradj.get(v)){
         //should only be one
         backtrack(nei);
      }
   }
   
   public static void dfs(int v, int p){
      
      dp[v][0] = 1;
      dp[v][1] = 0;
      
      dpsum[v][0] = (long)adj.get(v).size();
      dpsum[v][1] = 1L;
      
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         dfs(nei,v);
         
         dp[v][0] += dp[nei][1];
         dpsum[v][0] += dpsum[nei][1];
         paradj.get(2*v-1).add(2*nei);
         
         if(dp[nei][0] > dp[nei][1] || (dp[nei][0] == dp[nei][1] && dpsum[nei][0] < dpsum[nei][1])){
            //use 0
            dp[v][1] += dp[nei][0];
            dpsum[v][1] += dpsum[nei][0];
            paradj.get(2*v).add(2*nei-1);
         } else {
            //use 1
            dp[v][1] += dp[nei][1];
            dpsum[v][1] += dpsum[nei][1];
            paradj.get(2*v).add(2*nei);
         }
         
      }
      
      
      
      
      
   
   }
      
}