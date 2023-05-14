//make sure to make new file!
import java.io.*;
import java.util.*;

public class B869{

   public static ArrayList<ArrayList<Edge>> adj;
   
   public static ArrayList<Edge2> answer;
   
   public static boolean[] seen;
   public static int[] parent;
   public static ArrayList<ArrayList<Integer>> backto;
   public static ArrayList<ArrayList<Integer>> backfrom;
   public static int[] depth;
   public static boolean[] isbackedge;
   
   public static ArrayList<Integer> curcomp;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         adj = new ArrayList<>(n+1);
         backto = new ArrayList<>(n+1);
         backfrom = new ArrayList<>(n+1);
         for(int k = 0; k <= n; k++){
            adj.add(new ArrayList<Edge>());
            backto.add(new ArrayList<Integer>());
            backfrom.add(new ArrayList<Integer>());
         }
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
      
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(new Edge(b,k));
            adj.get(b).add(new Edge(a,k));
         }
         
         answer = new ArrayList<Edge2>();
         
         seen = new boolean[n+1];
         parent = new int[n+1];
         Arrays.fill(parent,-1);
         depth = new int[n+1];
         isbackedge = new boolean[m];
         
         for(int root = 1; root <= n; root++){
            if(seen[root]) continue;
            
            calc(root);
            if(answer.size() > 0) break;
         }
         
         if(answer.size() > 0){
            out.println("YES");
            StringJoiner sj = new StringJoiner("\n");
            sj.add("" + answer.size());
            for(Edge2 e : answer){
               sj.add(e.toString());
            }
            out.println(sj.toString());
         } else {
            out.println("NO");
         }
      

      }
      
      
      
      
      out.close();
   }
   
   
   public static void dfs(int v, int p){
      seen[v] = true;
      curcomp.add(v);
      
      for(Edge e : adj.get(v)){
         if(e.to == p || isbackedge[e.i]) continue;
         
         if(seen[e.to]){
            isbackedge[e.i] = true;
            backfrom.get(v).add(e.to);
            backto.get(e.to).add(v);
         } else {
            parent[e.to] = v;
            depth[e.to] = depth[v]+1;
            dfs(e.to,v);
         }
      }
   }
   
   public static void calc(int root){
      depth[root] = 1;
      curcomp = new ArrayList<Integer>();
      dfs(root,-1);
      
      for(int v : curcomp){
         //cases
         //node is not root and has backedge to and from
         if(v != root && backto.get(v).size() > 0 && backfrom.get(v).size() > 0){
            answer.add(new Edge2(v,parent[v]));
            answer.add(new Edge2(v,backfrom.get(v).get(0)));
            getcycle(backto.get(v).get(0),v);
            continue;
         }
         
         //node is not root and has 2 backedge to
         if(v != root && backto.get(v).size() >= 2){
            int be1 = backto.get(v).get(0);
            int be2 = backto.get(v).get(1);
            //be1 is deeper one
            if(depth[be1] < depth[be2]){
               int temp = be1;
               be1 = be2;
               be2 = temp;
            }
            
            answer.add(new Edge2(v,parent[v]));
            answer.add(new Edge2(v,be1));
            getcycle(be2,v);
         }
         
         //node is not leaf and has 2 backedge from
         int child = -1;
         for(Edge e : adj.get(v)){
            if(e.to != parent[v] && !isbackedge[e.i]){
               child = e.to;
               break;
            }
         }
         
         if(child != -1 && backfrom.get(v).size() >= 2){
            int be1 = backfrom.get(v).get(0);
            int be2 = backfrom.get(v).get(1);
            //be1 is deeper one
            if(depth[be1] < depth[be2]){
               int temp = be1;
               be1 = be2;
               be2 = temp;
            }
            
            answer.add(new Edge2(v,child));
            answer.add(new Edge2(v,be2));
            getcycle(v,be1);
         }
         
         //node has 3 backedge from
         if(backfrom.get(v).size() >= 3){
            Collections.sort(backfrom.get(v), (a,b) -> depth[a]-depth[b]);
            answer.add(new Edge2(v,backfrom.get(v).get(0)));
            answer.add(new Edge2(v,backfrom.get(v).get(1)));
            getcycle(v,backfrom.get(v).get(2));
         }
         
         //node has 3 backedge to
         if(backto.get(v).size() >= 3){
            Collections.sort(backto.get(v), (a,b) -> depth[a]-depth[b]);
            answer.add(new Edge2(v,backfrom.get(v).get(2)));
            answer.add(new Edge2(v,backfrom.get(v).get(1)));
            getcycle(v,backfrom.get(v).get(0));
         }
         
         
      }
   }
   
   public static void getcycle(int start, int end){
      answer.add(new Edge2(start,end));
      int i = start;
      while(i != end){
         answer.add(new Edge2(i,parent[i]));
         i = parent[i];
      }
   }
   
   public static class Edge2{
      int a;
      int b;
      public Edge2(int c, int d){
         a = c;
         b = d;
      }
   
      public String toString(){
         return a + " " + b;
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