//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, editorial
public class G317{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[][] board = new int[n][m];
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < m; j++){
            board[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      boolean[][] used = new boolean[n][m];
      int[][] answer = new int[n][m];
      
      boolean fail = false;
      //need to find m perfect matchings - find them one at a time and remove used edges in between
      for(int column = 0; column < m; column++){
         //x is 1 to n, y is n+1 to 2*n, source is 2*n+1, sink is 2*n+2
         //2*n+2 total nodes
         MaxFlow flow = new MaxFlow(2*n+2,2*n+1,2*n+2);
         
         for(int k = 0; k < n; k++){
            for(int j = 0; j < m; j++){
               if(!used[k][j]){
                  flow.addEdge(k+1,board[k][j]+n,1L,k,j);
               }
            }
         }
         
         for(int k = 1; k <= n; k++){
            flow.addEdge(2*n+1,k,1L,-1,-1);           //source to x
            flow.addEdge(k+n,2*n+2,1L,-1,-1);         //sink to y
         }
         
         long ret = flow.mfmc();
         //out.println(ret);
         if(ret != (long)n){
            fail = true;
            break;
         }
         
         //loop through all edges
         for(int x = 1; x <= n; x++){
            for(MaxFlow.Edge edge : flow.edges[x]){
               if(edge.flow == 1L && edge.to >= n+1 && edge.to <= 2*n){
                  int y = edge.to-n;
                  used[edge.bx][edge.by] = true;
                  answer[x-1][column] = y;
               }
            }
         }
         
      }
      
      if(fail){
         out.println("No");
      } else {
         out.println("Yes");
         StringJoiner rows = new StringJoiner("\n");
         for(int k = 0; k < n; k++){
            StringJoiner col = new StringJoiner(" ");
            for(int j = 0; j < m; j++){
               col.add("" + answer[k][j]);
            }
            rows.add(col.toString());
         }
         out.println(rows.toString());
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}

class MaxFlow
{
    //Dinic with optimizations (see magic array in dfs function)
    public int N, source, sink;
    public ArrayList<Edge>[] edges;
    private int[] depth;
 
    public MaxFlow(int n, int x, int y)
    {
        N = n;
        source = x;
        sink = y;
        edges = new ArrayList[N+1];
        for(int i=0; i <= N; i++)
            edges[i] = new ArrayList<Edge>();
        depth = new int[N+1];
    }
    public void addEdge(int from, int to, long cap, int bx, int by)
    {
        Edge forward = new Edge(from, to, cap, bx, by);
        Edge backward = new Edge(to, from, 0L, bx, by);
        forward.residual = backward;
        backward.residual = forward;
        edges[from].add(forward);
        edges[to].add(backward);
    }
    public long mfmc()
    {
        long res = 0L;
        int[] magic = new int[N+1];
        while(assignDepths())
        {
            long flow = dfs(source, Long.MAX_VALUE/2, magic);
            while(flow > 0)
            {
                res += flow;
                flow = dfs(source, Long.MAX_VALUE/2, magic);
            }
            magic = new int[N+1];
        }
        return res;
    }
    private boolean assignDepths()
    {
        Arrays.fill(depth, -69);
        ArrayDeque<Integer> q = new ArrayDeque<Integer>();
        q.add(source);
        depth[source] = 0;
        while(q.size() > 0)
        {
            int curr = q.poll();
            for(Edge e: edges[curr])
                if(e.capacityLeft() > 0 && depth[e.to] == -69)
                {
                    depth[e.to] = depth[curr]+1;
                    q.add(e.to);
                }
        }
        return depth[sink] != -69;
    }
    private long dfs(int curr, long bottleneck, int[] magic)
    {
        if(curr == sink)
            return bottleneck;
        for(; magic[curr] < edges[curr].size(); magic[curr]++)
        {
            Edge e = edges[curr].get(magic[curr]);
            if(e.capacityLeft() > 0 && depth[e.to]-depth[curr] == 1)
            {
                long val = dfs(e.to, Math.min(bottleneck, e.capacityLeft()), magic);
                if(val > 0)
                {
                    e.augment(val);
                    return val;
                }
            }
        }
        return 0L;  //no flow
    }
    public class Edge
    {
        public int from, to;
        public long flow, capacity;
        public Edge residual;
        
        //coordinates of the board that this edge corresponds to
        public int bx,by;
 
        public Edge(int f, int t, long cap, int x, int y)
        {
            from = f;
            to = t;
            capacity = cap;
            
            bx = x;
            by = y;
        }
        public long capacityLeft()
        {
            return capacity-flow;
        }
        public void augment(long val)
        {
            flow += val;
            residual.flow -= val;
        }
    }
}