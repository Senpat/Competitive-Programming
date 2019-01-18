//Tree with Maximum Cost
import java.io.*;
import java.util.*;

public class F1092{

   public static long[] cost, sum, answer;
   public static ArrayList<ArrayList<Integer>> adj;
   public static long rootans, max;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      cost = new long[n+1];
      
      for(int k = 1; k <= n; k++){
         cost[k] = Long.parseLong(st.nextToken());
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
      
      //calc sum of nodes in subtree of every node
      sum = new long[n+1];
      
      sums(1,-1);
      
      //for(int k = 1; k <= n; k++) System.out.print(sum[k] + " ");              //test sums
      
      //calc answer for root
      rootans = 0L;
      rootdfs(1,-1,0L);
      
      //System.out.println(rootans);
      
      answer = new long[n+1];
      answer[1] = rootans;
      max = 0L;
      dfs(1,-1);
      
      out.println(max);
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      if(p!=-1) answer[v] = answer[p] + sum[1]-2*sum[v];
      max = Math.max(max,answer[v]);
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfs(nei,v);
      }
   }
      
   
   
   public static void rootdfs(int v, int p,long level){
      rootans += level*cost[v];
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         rootdfs(nei,v,level+1);
      }
   }
      
      
   
   public static long sums(int v,int p){
      if(adj.get(v).size()==1 && p != -1){                     //REMEMBER THE && P != -1
         sum[v] = cost[v];
         return sum[v];
      }
      
      long cur = 0L;
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         cur+=sums(nei,v);
      }
      return sum[v] = cur+cost[v];
   }
   
}