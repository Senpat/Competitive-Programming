//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1695{
   
   public static MaxFlow flow;
   public static boolean[] reachable;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      flow = new MaxFlow(n,1,n);
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         flow.addEdge(a,b,1L);
         flow.addEdge(b,a,1L);
      }
      
      long ret = flow.mfmc();
      
      out.println(ret);
      
      reachable = new boolean[n+1];
      dfs(1);
      
      for(int v = 1; v <= n; v++){
         for(MaxFlow.Edge e : flow.edges[v]){
            //all edges that lead from a reachable node to a nonreachable node
            if(reachable[e.from] && !reachable[e.to] && !e.isresidual){
               out.println(e.from + " " + e.to);
            }
         }
      }
      
      
      
      out.close();
   }
   
   
   public static void dfs(int v){
      reachable[v] = true;
      
      for(MaxFlow.Edge e : flow.edges[v]){
         if(reachable[e.to]) continue;
         //only use edges that are not at full capacity
         if(e.flow == e.capacity) continue;
         
         dfs(e.to);
      }
   }
      
}

//from ray
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
   public Edge addEdge(int from, int to, long cap)
   {
      Edge forward = new Edge(from, to, cap, false);
      Edge backward = new Edge(to, from, 0L, true);
      forward.residual = backward;
      backward.residual = forward;
      edges[from].add(forward);
      edges[to].add(backward);
      
      return forward;
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
      boolean isresidual;
   
      public Edge(int f, int t, long cap, boolean b)
      {
         from = f;
         to = t;
         capacity = cap;
         isresidual = b;
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



class FastScanner
{
   //I don't understand how this works lmao
   private int BS = 1 << 16;
   private char NC = (char) 0;
   private byte[] buf = new byte[BS];
   private int bId = 0, size = 0;
   private char c = NC;
   private double cnt = 1;
   private BufferedInputStream in;

   public FastScanner() {
      in = new BufferedInputStream(System.in, BS);
   }

   public FastScanner(String s) {
      try {
         in = new BufferedInputStream(new FileInputStream(new File(s)), BS);
      } catch (Exception e) {
         in = new BufferedInputStream(System.in, BS);
      }
   }

   private char getChar() {
      while (bId == size) {
         try {
            size = in.read(buf);
         } catch (Exception e) {
            return NC;
         }
         if (size == -1) 
            return NC;
         bId = 0;
      }
      return (char) buf[bId++];
   }

   public int nextInt() {
      return (int) nextLong();
   }

   public int[] nextInts(int N) {
      int[] res = new int[N];
      for (int i = 0; i < N; i++) {
         res[i] = (int) nextLong();
      }
      return res;
   }

   public long[] nextLongs(int N) {
      long[] res = new long[N+1];
      for (int i = 1; i <= N; i++) {
         res[i] = nextLong();
      }
      return res;
   }

   public long nextLong() {
      cnt = 1;
      boolean neg = false;
      if (c == NC) c = getChar();
      for (; (c < '0' || c > '9'); c = getChar()) {
         if (c == '-') neg = true;
      }
      long res = 0;
      for (; c >= '0' && c <= '9'; c = getChar()) {
         res = (res << 3) + (res << 1) + c - '0';
         cnt *= 10;
      }
      return neg ? -res : res;
   }

   public double nextDouble() {
      double cur = nextLong();
      return c != '.' ? cur : cur + nextLong() / cnt;
   }

   public double[] nextDoubles(int N) {
      double[] res = new double[N];
      for (int i = 0; i < N; i++) {
         res[i] = nextDouble();
      }
      return res;
   }

   public String next() {
      StringBuilder res = new StringBuilder();
      while (c <= 32) c = getChar();
      while (c > 32) {
         res.append(c);
         c = getChar();
      }
      return res.toString();
   }

   public String nextLine() {
      StringBuilder res = new StringBuilder();
      while (c <= 32) c = getChar();
      while (c != '\n') {
         res.append(c);
         c = getChar();
      }
      return res.toString();
   }

   public boolean hasNext() {
      if (c > 32) 
         return true;
      while (true) {
         c = getChar();
         if (c == NC) 
            return false;
         else if (c > 32) 
            return true;
      }
   }
}