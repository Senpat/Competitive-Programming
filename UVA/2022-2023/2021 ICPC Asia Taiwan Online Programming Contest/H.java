//make sure to make new file!
import java.io.*;
import java.util.*;

public class H{
   
   public static int M = 16;
   
   public static void main(String[] args)throws IOException{
      //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      FastScanner fs = new FastScanner();
      
      int n = fs.nextInt();
      int m = fs.nextInt();
      
      Edge[] edges = new Edge[m];
      for(int k = 0; k < m; k++){
         int a = fs.nextInt()+1;
         int b = fs.nextInt()+1;
         
         edges[k] = new Edge(a,b);
      }  
      
      int[] v = new int[n+1];
      for(int k = 1; k <= n; k++){
         v[k] = fs.nextInt();
      }
      
      int q = fs.nextInt();
      
      Constraint[] cons = new Constraint[q];
      for(int k = 0; k < q; k++){
         int t = fs.nextInt();
         int u = fs.nextInt()+1;
         int i = fs.nextInt();
         int w = fs.nextInt()+1;    //v
         int j = fs.nextInt();   
         
         cons[k] = new Constraint(t,u,i,w,j);
      }
      
      int answer = Integer.MAX_VALUE;
      
      
      for(int mask = 0; mask < (1 << q); mask++){
         int[][] nodes = new int[n+1][M];
         for(int k = 1; k <= n; k++){
            if(v[k] == -1) Arrays.fill(nodes[k],-1);
            else{
               for(int i = 0; i < M; i++){
                  if((v[k] & (1 << i)) == 0){
                     nodes[k][i] = 0;
                  } else {
                     nodes[k][i] = 1;
                  }
               }
            }
         }
         
         //fill in constraints
         boolean fail = false;
         for(int c = 0; c < q; c++){
            
            int x1 = (mask >> c) & 1;
            int x2 = x1 ^ cons[c].t;
            if(nodes[cons[c].u][cons[c].i] == 1-x1 || nodes[cons[c].v][cons[c].j] == 1-x2){
               fail = true;
               break;
            }
            nodes[cons[c].u][cons[c].i] = x1;
            nodes[cons[c].v][cons[c].j] = x2;
            
         }
         
         if(fail) 
            continue;
         
         int curanswer = 0;
         for(int b = 0; b < M; b++){
            ArrayList<Integer> sources = new ArrayList<Integer>();
            ArrayList<Integer> sinks = new ArrayList<Integer>();
            
            for(int k = 1; k <= n; k++){
               if(nodes[k][b] == 0){
                  sources.add(k);
               }
               if(nodes[k][b] == 1){
                  sinks.add(k);
               }
            }
            
            if(sources.size() == 0 || sinks.size() == 0){      //0 for this bit
               continue;
            }
            
            MaxFlow mf = new MaxFlow(n+2,n+1,n+2);
            
            //add graph edges
            for(Edge e : edges){
               mf.addEdge(e.a,e.b,1);
               mf.addEdge(e.b,e.a,1);
            }
            
            //add souce edges
            for(int source : sources){
               mf.addEdge(n+1,source,Integer.MAX_VALUE/2);
            }
            
            //add sink edges
            for(int sink :    sinks){
               mf.addEdge(sink,n+2,Integer.MAX_VALUE/2);
            }
            
            curanswer += mf.mfmc();
         }
         answer = Math.min(answer,curanswer);
      }
      
      
      
      
      
      
      if(answer == Integer.MAX_VALUE){
         out.println(-1);
      } else {
         out.println(answer);
      }
      
      
      out.close();
   }
   
   public static class Constraint{
      int t;
      int u;
      int i;
      int v;
      int j;
      public Constraint(int a, int b, int c, int d, int e){
         t = a;
         u = b;
         i = c;
         v = d;
         j = e;
      }
   }
   
   public static class Edge{
      int a;
      int b;
      public Edge(int c, int d){
         a = c;
         b = d;
      }
   }
   
   //thanks ray
   
   public static class MaxFlow
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
      public void addEdge(int from, int to, int cap)
      {
         Edge forward = new Edge(from, to, cap);
         Edge backward = new Edge(to, from, 0);
         forward.residual = backward;
         backward.residual = forward;
         edges[from].add(forward);
         edges[to].add(backward);
      }
      public long mfmc()
      {
         int res = 0;
         int[] magic = new int[N+1];
         while(assignDepths())
         {
            int flow = dfs(source, Integer.MAX_VALUE/2, magic);
            while(flow > 0)
            {
               res += flow;
               flow = dfs(source, Integer.MAX_VALUE/2, magic);
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
      private int dfs(int curr, int bottleneck, int[] magic)
      {
         if(curr == sink)
            return bottleneck;
         for(; magic[curr] < edges[curr].size(); magic[curr]++)
         {
            Edge e = edges[curr].get(magic[curr]);
            if(e.capacityLeft() > 0 && depth[e.to]-depth[curr] == 1)
            {
               int val = dfs(e.to, Math.min(bottleneck, e.capacityLeft()), magic);
               if(val > 0)
               {
                  e.augment(val);
                  return val;
               }
            }
         }
         return 0;  //no flow
      }
      private class Edge
      {
         public int from, to;
         public int flow, capacity;
         public Edge residual;
      
         public Edge(int f, int t, int cap)
         {
            from = f;
            to = t;
            capacity = cap;
         }
         public int capacityLeft()
         {
            return capacity-flow;
         }
         public void augment(int val)
         {
            flow += val;
            residual.flow -= val;
         }
      }
   }

}

//thanks ray part 2
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
            if (size == -1) return NC;
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
        long[] res = new long[N];
        for (int i = 0; i < N; i++) {
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
        if (c > 32) return true;
        while (true) {
            c = getChar();
            if (c == NC) return false;
            else if (c > 32) return true;
        }
    }
}