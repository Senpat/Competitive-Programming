//make sure to make new file!
import java.io.*;
import java.util.*;

public class D1156{

   public static ArrayList<ArrayList<Edge>> adj;
   public static long[][] sub;
   public static long[][] dp;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Edge>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      for(int k = 0; k < n-1; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(new Edge(b,c));
         adj.get(b).add(new Edge(a,c));
      }
      
      
      sub = new long[n+1][2];                          //[node][0 or 1];
      dp = new long[n+1][2];
      
      
      subdfs(1,-1);
      
      dpdfs(1,-1);
      
      long answer = 0L;
      for(int k = 1; k <= n; k++){
         for(int j = 0; j <= 1; j++){
            answer+=dp[k][j];
         }
      }
      
      out.println(answer);
      
      out.close();
   }
   
   public static void dpdfs(int v, int p){
      dp[v][0] += sub[v][0];
      dp[v][1] += sub[v][1];
      
      for(Edge e : adj.get(v)){
         if(e.nei == p) continue;
         if(e.w == 0){
            dp[e.nei][0] = dp[v][0] + dp[v][1] - sub[e.nei][0] - sub[e.nei][1];
         } else {
            dp[e.nei][1] = dp[v][1] - sub[e.nei][1];
         }
         dpdfs(e.nei,v);
      }
   }
   
   public static void subdfs(int v, int p){
      
      sub[v][0] = 0L;
      sub[v][1] = 0L;
      
      for(Edge e : adj.get(v)){
         if(e.nei == p) continue;
         subdfs(e.nei,v);
         if(e.w == 0){
            sub[v][0] += sub[e.nei][0] + sub[e.nei][1] + 1;     
         } else {
            sub[v][1] += sub[e.nei][1] + 1;
         }
      }
   }
   
   
   public static class Edge{
      int nei;
      int w;
      public Edge(int a, int b){
         nei = a;
         w = b;
      }
   }
   
      
}