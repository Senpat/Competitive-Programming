//Simple Cycles Edges
import java.io.*;
import java.util.*;

public class F962{
   
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static int[] parent;
   public static int[] parentedge;               //stores index of edge going to parent
   public static boolean[] seen;
   public static boolean[] isbackedge;
   public static int[][] backedges;          //for backedges x, backedges go from backedges[x][0] to backedges[x][1]
   public static int[] backto;               //# of backedges going to v
   public static int[] backfrom;             //# of backedges going from v
   
   public static int[] numbackedges;             //# of backedges that go over that edge
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      for(int k = 1; k <= m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(new Edge(b,k));
         adj.get(b).add(new Edge(a,k));
      }
      
      parent = new int[n+1];
      Arrays.fill(parent,-1);
      parentedge = new int[n+1];
      Arrays.fill(parentedge,-1);
      seen = new boolean[n+1];
      isbackedge = new boolean[m+1];
      backedges = new int[m+1][2];
      backto = new int[n+1];
      backfrom = new int[n+1];
      
      ArrayList<Integer> roots = new ArrayList<Integer>();
      for(int k = 1; k <= n; k++){
         if(!seen[k]){
            roots.add(k);
            dfs(k,-1);
         }
      }
      
      numbackedges = new int[m+1];
      
      for(int root : roots){
         dfs2(root,-1);
      }
      
      ArrayList<Integer> answer = new ArrayList<Integer>();
      
      //loop over back edges and see if all edges in it are 1
      for(int k = 1; k <= m; k++){
         if(isbackedge[k]){
            int i = backedges[k][0];
            
            ArrayList<Integer> path = new ArrayList<Integer>();
            boolean fail = false;
            while(i != backedges[k][1]){
               if(numbackedges[parentedge[i]] != 1){
                  fail = true;
                  break;
               }
               
               path.add(parentedge[i]);
               i = parent[i];
            }
            
            if(fail) continue;
            
            for(int span : path){
               answer.add(span);
            }
            answer.add(k);
         
         } 
      }
      
      
      Collections.sort(answer);
      out.println(answer.size());
      StringJoiner sj = new StringJoiner(" ");
      for(int i : answer){
         sj.add("" + i);
      }
      out.println(sj.toString());
      
      
      out.close();
   }
   
   //calc numbackedges for span edges
   public static void dfs2(int v, int p){
      
      if(parentedge[v] != -1){
         numbackedges[parentedge[v]] += backfrom[v] - backto[v];
      }
      
      for(Edge e : adj.get(v)){
         if(e.to == p) continue;
         if(isbackedge[e.i]) continue;
           
         dfs2(e.to,v);
         
         if(parentedge[v] != -1){
            numbackedges[parentedge[v]] += numbackedges[e.i];
         }   
         
      }
   }
   
   //make dfs tree
   public static void dfs(int v, int p){
      
      seen[v] = true;
      
      for(Edge e : adj.get(v)){
         if(e.to == p) continue;
         if(isbackedge[e.i]) continue;
         
         if(seen[e.to]){
            isbackedge[e.i] = true;
            backfrom[v]++;
            backto[e.to]++;
            backedges[e.i][0] = v;
            backedges[e.i][1] = e.to;
         } else {
            parentedge[e.to] = e.i;
            parent[e.to] = v;
            dfs(e.to,v);
         }  
      }
   }
   
   public static class Edge{
      int to;
      int i;
      public Edge(int a, int b){
         to = a;
         i = b;
      }
   }
   
      
}