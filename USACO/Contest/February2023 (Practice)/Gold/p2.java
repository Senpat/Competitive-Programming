//make sure to make new file!
import java.io.*;
import java.util.*;

public class p2{

   public static ArrayList<ArrayList<Integer>> adj;
   
   public static long[] array;
   public static long[] subgrow;
   public static long[][] dp1;
   public static long[][] dp2;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int T = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      array = new long[n+1];
      
      for(int k = 2; k <= n; k++){
         st = new StringTokenizer(f.readLine());
      
         int p = Integer.parseInt(st.nextToken());
         long a = Long.parseLong(st.nextToken());
         
         adj.get(k).add(p);
         adj.get(p).add(k);
         
         array[k] = a;
      }
      
      dp1 = new long[n+1][2];           //time it takes to complete subtree after you enter root node
      dp2 = new long[n+1][2];           //# of fertilizer it takes to complete subtree after you enter root node
      subgrow = new long[n+1];         //how much grass in the subtree grows per second
      
      dfs(1,-1);
      
      out.println(dp1[1][T] + " " + dp2[1][T]);
      
      
      
      
      
      out.close();
   }
   
   
   public static void dfs(int v, int p){
      
      //calc T = 0
      subgrow[v] = array[v];
      dp1[v][0] = 0L;
      dp2[v][0] = 0L;
      
      PriorityQueue<Child> pq0 = new PriorityQueue<Child>();
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfs(nei,v);
         
         dp1[v][0] += dp1[nei][0] + 2L;
         dp2[v][0] += dp2[nei][0] + subgrow[nei];
         subgrow[v] += subgrow[nei];
         pq0.add(new Child(dp1[nei][0]+2L,subgrow[nei]));
      }
      
      long pretime = 0L;
      while(!pq0.isEmpty()){
         Child c = pq0.poll();
         dp2[v][0] += pretime * c.grow;
         pretime += c.time;
      }
      
      
      
   }
   
   public static class Child implements Comparable<Child>{
      long time;
      long grow;
      public Child(long a, long b){
         time = a;
         grow = b;
      }
      public int compareTo(Child c){
         //sort by decreasing grow
         if(grow < c.grow) return 1;
         if(grow > c.grow) return -1;
         return 0;
      }
   }
      
}