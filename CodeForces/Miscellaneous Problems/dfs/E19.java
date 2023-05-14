//Fairy
import java.io.*;
import java.util.*;

public class E19{
   
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static int[] color;
   public static ArrayList<Integer> badcomps;
   public static boolean fail;
   
   public static int[] depth;
   //contradictory edge is a back edge that joins two vertices of the same color
   public static int[][] backfrom;           //0 -> # of contradictory, 1 -> # of not contradictory
   public static int[][] backto;
   public static int[] parentedge;
   public static boolean[] isbackedge;
   
   public static ArrayList<Integer> contedges;     //contradictory edges
   
   public static int[][] numbackedge;
   public static ArrayList<Integer> answer;
   
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
      
      //stores root of bad comps
      badcomps = new ArrayList<Integer>();
      color = new int[n+1];
      
      for(int k = 1; k <= n; k++){
         if(color[k] != 0) continue;
         fail = false;
         color[k] = 1;
         coloring(k);
         
         if(fail){
            badcomps.add(k);
         }
      }
      
      if(badcomps.size() == 0){
         out.println(m);
         for(int k = 1; k <= m; k++){
            out.print(k + " ");
         }
         out.println();
         out.close();
         return;
      }
      
      if(badcomps.size() >= 2){
         out.println(0);
         out.close();
         return;
      }
      
      
      //get dfs tree starting at root
      int root = badcomps.get(0);
      depth = new int[n+1];
      Arrays.fill(depth,-1);
      backfrom = new int[n+1][2];
      backto = new int[n+1][2];
      parentedge = new int[n+1];
      Arrays.fill(parentedge,-1);
      isbackedge = new boolean[m+1];
      contedges = new ArrayList<Integer>();
      
      depth[root] = 0;
      dfs(root,-1);
      
      answer = new ArrayList<Integer>();
      //if there is only one contradictory back edge, add that back edge
      if(contedges.size() == 1) answer.add(contedges.get(0));
      
      //add span edges
      //add if all of the contradictory back edges go over it, and none of the non-contradictory back edges
      numbackedge = new int[m+1][2];
      dfs2(root,-1);
      
      Collections.sort(answer);
      
      out.println(answer.size());
      for(int i : answer){
         out.print(i + " ");
      }
      out.println();
      
      
      out.close();
   }
   
   public static void dfs2(int v, int p){
      if(parentedge[v] != -1){
         numbackedge[parentedge[v]][0] = backfrom[v][0] - backto[v][0];
         numbackedge[parentedge[v]][1] = backfrom[v][1] - backto[v][1];
      }
      
      for(Edge e : adj.get(v)){
         if(e.to == p) continue;
         if(isbackedge[e.i]) continue;
         
         dfs2(e.to,v);
         
         if(parentedge[v] != -1){
            numbackedge[parentedge[v]][0] += numbackedge[e.i][0];
            numbackedge[parentedge[v]][1] += numbackedge[e.i][1];
         }
      }
      
      if(parentedge[v] != -1){
         if(numbackedge[parentedge[v]][0] == contedges.size() && numbackedge[parentedge[v]][1] == 0){
            answer.add(parentedge[v]);
         }
      }
      
   }
   
   
   public static void dfs(int v, int p){
      
      for(Edge e : adj.get(v)){
         if(e.to == p) continue;
         if(isbackedge[e.i]) continue;
         
         if(depth[e.to] != -1){
            isbackedge[e.i] = true;
            
            int d = (depth[v] - depth[e.to])%2;
            //d = 0 is contradictory, 1 is not contradictory
            backfrom[v][d]++;
            backto[e.to][d]++;
            
            if(d == 0){
               contedges.add(e.i);
            }
         } else {
            depth[e.to] = depth[v]+1;
            parentedge[e.to] = e.i;
            dfs(e.to,v);
         }
      }
   }
   
   
   public static void coloring(int v){
      
      for(Edge e : adj.get(v)){
         if(color[e.to] != 0){
            if(color[e.to] == color[v]){
               fail = true;
            }
         } else {
            color[e.to] = 3-color[v];
            coloring(e.to);
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