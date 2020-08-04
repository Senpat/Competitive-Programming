import java.io.*;
import java.util.*;
//checks using bfs
class P1{
   
   public static ArrayList<ArrayList<Edge>> adj;
   public static int MAX = 55;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("p1.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("p1.out")));
      
      
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         adj = new ArrayList<ArrayList<Edge>>(n+1);
         for(int k = 0; k <= n; k++){
            adj.add(new ArrayList<Edge>());
         }
         
         Req[] reqs = new Req[m];
         
         
         //all vertices not used are attached to maxv
         int maxv = 0;
         
         
         HashSet<Integer> included = new HashSet<Integer>();
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            reqs[k] = new Req(a,b,c);
            
            adj.get(a).add(new Edge(b,c));
            adj.get(b).add(new Edge(a,c));
            
            maxv = Math.max(maxv,a);
            maxv = Math.max(maxv,b);
            
            included.add(a);
            included.add(b);
         }
         
         //make sure shortest paths are all equal to req
         boolean failed = false;
         for(int k = 0; k < m; k++){
            if(count(reqs[k].a,reqs[k].b) != reqs[k].w){
               out.println("Case #" + q + ": Impossible");
               failed = true;
               break;
            }
         }
         
         if(failed) continue;
         int size = m + n - included.size();
         out.println("Case #" + q + ": " + size);
         
         for(int k = 0; k < m; k++){
            out.println(reqs[k].a + " " + reqs[k].b + " " + reqs[k].w);
         }
         
         for(int k = 1; k <= n; k++){
            if(!included.contains(k)){
               out.println(maxv + " " + k + " 1000000");
            }
         }
         
         
         
      
      }
        
        
      out.close();
   }
   
   public static int count(int a, int b){
      //outputs shortest path between a and b
      
      
      int[] seen = new int[MAX];
      Arrays.fill(seen,Integer.MAX_VALUE);
      seen[a] = 0;
            
      PriorityQueue<State> pq = new PriorityQueue<State>();
      
      for(Edge nei : adj.get(a)){
         pq.add(new State(nei.to,nei.w));
         seen[nei.to] = nei.w;
      }
      
      while(!pq.isEmpty()){
         State s = pq.poll();
         
         if(s.v == b){
            return s.d;
         } 
         
         for(Edge nei : adj.get(s.v)){
            if(seen[nei.to] <= s.d + nei.w) continue;
            pq.add(new State(nei.to,s.d + nei.w));
            seen[nei.to] = s.d + nei.w;
         }
      }
      
      return -1;
   }
      
      
      
      
   
   
   public static class State implements Comparable<State>{
      int v;
      int d;
      public State(int a, int b){
         v = a;
         d = b;
      }
      public int compareTo(State s){
         return d - s.d;
      }
   }
      
   
   public static class Req{
      int a;
      int b;
      int w;
      public Req(int p, int o, int i){
         a = p;
         b = o;
         w = i;
      }
   }
   
   public static class Edge{
      int to;
      int w;
      
      public Edge(int a, int b){
         to = a;
         w = b;
      }
   }
      
}