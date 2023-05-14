//make sure to make new file!
import java.io.*;
import java.util.*;
//based off of B869.java but less casework
public class B869b{

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
            if(depth[root] > 0) continue;
            
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
         if(adj.get(v).size() < 4) continue;
         if(backfrom.get(v).size() > 0){
            //get deepest backfrom neighbor
            int bf = backfrom.get(v).get(0);
            for(int nei : backfrom.get(v)){
               if(depth[bf] < depth[nei]){
                  bf = nei;
               }
            }
            
            getcycle(v,bf);
            int added = 0;
            for(Edge e : adj.get(v)){
               if(!incycle.contains(e.to)){
                  answer.add(new Edge2(v,e.to));
                  added++;
                  if(added == 2) break;
               }
            }
            break;
         } else if(backto.get(v).size() > 0){
            //get shallowest backto neighbor
            int bt = backto.get(v).get(0);
            for(int nei : backto.get(v)){
               if(depth[bt] > depth[nei]){
                  bt = nei;
               }
            }
            
            getcycle(bt,v);
            int added = 0;
            for(Edge e : adj.get(v)){
               if(!incycle.contains(e.to)){
                  answer.add(new Edge2(v,e.to));
                  added++;
                  if(added == 2) break;
               }
            }
            break;
         }
      }
   }
   
   public static HashSet<Integer> incycle;
   public static void getcycle(int start, int end){
      incycle = new HashSet<Integer>();
      incycle.add(start);
      incycle.add(end);
      answer.add(new Edge2(start,end));
      int i = start;
      while(i != end){
         answer.add(new Edge2(i,parent[i]));
         incycle.add(i);
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