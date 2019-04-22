//make sure to make new file!
import java.io.*;
import java.util.*;

public class C549{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] par;
   public static boolean[] respect;
   
   public static int[] subnum;
   public static int[] rsubnum;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      par = new int[n+1];
      
      respect = new boolean[n+1];
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      int root = -1;
      for(int k = 1; k <= n; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         if(a == -1){
            par[k] = -1;
            respect[k] = b == 0;
            root = k;
         } else {
            par[k] = a;
            respect[k] = b == 0;
            adj.get(a).add(k);
            adj.get(k).add(a);
         }
      
      }
      
      subnum = new int[n+1];
      rsubnum = new int[n+1];
      
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
      dfs(root,-1);
      /*
      for(int k = 1; k <= n; k++){
         out.println(subnum[k] + " " + rsubnum[k]);
      }*/
      //out.close();
      //System.exit(0);
      for(int k = 1; k <= n; k++){
         if(!respect[k] && subnum[k] == rsubnum[k]){
            pq.add(k);
         }
         
      }
      
      if(pq.isEmpty()){
         out.println(-1);
         out.close();
         System.exit(0);
      }
      
      while(!pq.isEmpty()){
         int curv = pq.poll();
         out.print(curv + " ");
         //remove this vertex
         int parent = par[curv];
         subnum[parent] = subnum[parent] + subnum[curv] - 1;
         rsubnum[parent] = rsubnum[parent] + rsubnum[curv];
         if(!respect[curv]) rsubnum[parent] --;
         
         if(subnum[parent] == rsubnum[parent]){
            pq.add(parent);
         }
         
         for(int child : adj.get(curv)){
            if(child == par[curv]) continue;
            par[child] = parent;
         }
      }
         
         
         

      /*
      while(!pq.isEmpty()){
         int p = pq.poll();
         out.print(p + " ");
      }*/
      
      out.close();
   }
   
   
   public static void dfs(int v, int p){
   
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfs(nei,v);
         subnum[v] ++;
         if(!respect[nei]) rsubnum[v]++;
      }
   }
}