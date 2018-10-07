//Checkposts
import java.io.*;
import java.util.*;

public class C427{
   
   public static Stack<Integer> stk;
   public static ArrayList<ArrayList<Integer>> adj,tadj,scc;
   public static boolean[] seen1,seen2;
   public static long MOD = 1000000007;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] costs = new int[n+1];
      
      for(int k = 1; k <= n; k++) costs[k] = Integer.parseInt(st.nextToken());
      
      int m = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Integer>>();
      tadj = new ArrayList<ArrayList<Integer>>();
      
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Integer>());
         tadj.add(new ArrayList<Integer>());
      }
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         tadj.get(b).add(a);
      }
      
      stk = new Stack<Integer>();
      seen1 = new boolean[n+1];
      seen2 = new boolean[n+1];
      
      //step 1: initial dfs, fill stack
      for(int k = 1; k <= n; k++){
         if(!seen1[k]){
            dfs(k);
         }
      }
      
      scc = new ArrayList<ArrayList<Integer>>();
      
      //dfs on transposed graph  
      while(!stk.empty()){
         int cur = stk.pop();
         if(!seen2[cur]){
            scc.add(new ArrayList<Integer>());
            dfs2(cur);
         }
      }
      
      //find mins
      long ways = 1L;
      long mincost = 0L;
      for(ArrayList<Integer> alist : scc){
         long curmin = Long.MAX_VALUE;
         long minfreq = 1L;
         for(int i : alist){
            if(costs[i] == curmin){
               minfreq++;
            } else if(costs[i] < curmin){
               curmin = costs[i];
               minfreq = 1L;
            }
         }
         mincost+=curmin;
         ways = (ways * minfreq + MOD) % MOD;
      }
      
      out.println(mincost + " " + (ways % MOD));
      
      out.close();
   }
   
   public static void dfs(int v){
      if(!seen1[v]){
         seen1[v] = true;
         for(int nei : adj.get(v)){
            if(!seen1[nei]){
               dfs(nei);
            }
         }
         stk.push(v);
      }
   }
   
   public static void dfs2(int v){
      if(!seen2[v]){
         seen2[v] = true;
         for(int nei : tadj.get(v)){
            if(!seen2[nei]){
               dfs2(nei);
            }
         }
         scc.get(scc.size()-1).add(v);
      }
   }
   
}