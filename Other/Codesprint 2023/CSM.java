//make sure to make new file!
import java.io.*;
import java.util.*;
//tle tc 45
public class CSM{

   public static int n;

   public static ArrayList<ArrayList<Edge>> adj;
   
   public static HashMap<Long,Long> dists;
   
   public static int compnum;
   public static int[] components;
   public static HashSet<Integer> incomponent;
   public static int extra_edge;
      
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      n = fs.nextInt();
      
      adj = new ArrayList<>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      int edge_index = 0;
      ArrayList<FullEdge> fulledges = new ArrayList<FullEdge>();
      //initial graph is a forest of (trees + 1 edge)
      for(int k = 1; k <= n; k++){
         int to = fs.nextInt();
         long w = fs.nextLong();
         
         adj.get(k).add(new Edge(to,w,edge_index));
         adj.get(to).add(new Edge(k,w,edge_index));
         
         fulledges.add(new FullEdge(k,to,w));
         edge_index++;
      }
      
      //additional edges
      int L = fs.nextInt();
      FullEdge[] additional = new FullEdge[L];
      for(int k = 0; k < L; k++){
         int a = fs.nextInt();
         int b = fs.nextInt();
         long w = fs.nextLong();
         
         additional[k] = new FullEdge(a,b,w);
      }
      
      int q = fs.nextInt();
      Query[] queries = new Query[q];
      HashSet<Long> allpoints = new HashSet<Long>();
      for(int k = 0; k < q; k++){
         int a = fs.nextInt();
         int b = fs.nextInt();
         
         queries[k] = new Query(a,b);
         
         allpoints.add(getlong(a,b));
      }
      
      dists = new HashMap<Long,Long>();
      
      lca = new int[n+1][MAXD];
      depth = new int[n+1];
      rootdist = new long[n+1];
      
      compnum = 0;
      components = new int[n+1];
      Arrays.fill(components,-1);
      ArrayList<Integer> comp_extra_edges = new ArrayList<Integer>();
      for(int k = 1; k <= n; k++){
         if(components[k] == -1){
            extra_edge = -1;
            incomponent = new HashSet<Integer>();
            depth[k] = 0;
            initdfs(k,-1);
            
            comp_extra_edges.add(extra_edge);
            
            compnum++;
         }
      }
      
      initLCA();
      
      ArrayList<ArrayList<Point>> comp_points = new ArrayList<>(compnum);
      for(int k = 0; k < compnum; k++){
         comp_points.add(new ArrayList<Point>());
      }
      
      for(int v = 1; v <= n; v++){
         for(int k = 0; k < L; k++){
            if(v != additional[k].a) allpoints.add(getlong(v,additional[k].a));
            if(v != additional[k].b) allpoints.add(getlong(v,additional[k].b));
         }
      }
      
      //add all points to dists (should be all points needed to query)
      for(long pl : allpoints){
         dists.put(pl,Long.MAX_VALUE);
         
         Point p = fromlong(pl);
         if(components[p.a] == components[p.b]){
            comp_points.get(components[p.a]).add(p);
         }
      }
      
      //incorporate extra edge
      for(int comp = 0; comp < compnum; comp++){
         FullEdge fe = fulledges.get(comp_extra_edges.get(comp));
         for(Point p : comp_points.get(comp)){
            long dist = dist(p.a,p.b);
            //use the extra edge
            dist = Math.min(dist,dist(p.a,fe.a) + fe.w + dist(fe.b,p.b));
            dist = Math.min(dist,dist(p.a,fe.b) + fe.w + dist(fe.a,p.b));
            setdist(p,dist);
         }
      }
      
      //incorporate additional edges
      for(int k = 0; k < L; k++){
         for(long pl : allpoints){
            long startdist = dists.get(pl);
            long curdist = startdist;
            long d1,d2;
            int pa = (int)(pl & mask);
            int pb = (int)(pl >> P);
            d1 = getdist(pa,additional[k].a);
            d2 = getdist(additional[k].b,pb);
            if(d1 < Long.MAX_VALUE && d2 < Long.MAX_VALUE){
               curdist = Math.min(curdist,d1+d2+additional[k].w);
            }
            d1 = getdist(pa,additional[k].b);
            d2 = getdist(additional[k].a,pb);
            if(d1 < Long.MAX_VALUE && d2 < Long.MAX_VALUE){
               curdist = Math.min(curdist,d1+d2+additional[k].w);
            }
            
            if(curdist < startdist){
               dists.put(pl,curdist);
            }
         }
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int t = 0; t < q; t++){
         long dist = getdist(queries[t].a,queries[t].b);
         if(dist == Long.MAX_VALUE){
            sj.add("-1");
         } else {
            sj.add("" + dist);
         }
      }
      out.println(sj.toString());
      
      
      
      out.close();
   }
   
   public static long getdist(int a, int b){
      if(a == b) return 0L;
      return dists.get(getlong(a,b));
   }
   
   public static void setdist(int a, int b, long d){
      dists.put(getlong(a,b),d);
   }
   
   public static void setdist(Point p, long d){
      dists.put(p.pl,d);
   }
   
   public static int P = 20;
   public static long mask = (1L << P)-1;
   public static long getlong(int a, int b){
      long x = (long)Math.min(a,b);
      long y = (long)Math.max(a,b);
      
      return (x << P) + y;
   }
   
   public static Point fromlong(long pl){
      return new Point((int)(pl & mask),(int)(pl >> P));
   }
   
   public static class Point{
      int a;
      int b;
      long pl;
      public Point(int c, int d){
         a = Math.min(c,d);
         b = Math.max(c,d);
         pl = ((long)a << P) + (long)b;
      }
      
      public String toString(){
         return a + " " + b;
      }
   }
   
   public static class Edge{
      int to;
      long w;
      int i;
      public Edge(int a, long b, int c){
         to = a;
         w = b;
         i = c;
      }
   } 
   
   public static class FullEdge{
      int a;
      int b;
      long w;
      public FullEdge(int c, int d, long e){
         a = c;
         b = d;
         w = e;
      }
   }
   
   public static class Query{
      int a;
      int b;
      public Query(int c, int d){
         a = c;
         b = d;
      }
   }
   
   
   
   //lca and dist stuff
   //with weighted edges
   public static int MAXD = 17;
   public static int[][] lca;
   public static int[] depth;
   public static long[] rootdist;            //distance from root
   
   public static void initLCA() {
      for(int d = 1; d < MAXD; d++) {
         for(int i = 1; i < n+1; i++) {
            lca[i][d] = lca[lca[i][d-1]][d-1];
         }
      }
   }
   
   public static int lca(int a, int b){
      
      if(depth[a] < depth[b]){
         //swap a and b
         int temp = a;
         a = b;
         b = temp;
      }
      
      for(int i = MAXD-1; i >= 0; i--){
         if (((depth[a]-depth[b]) & (1<<i)) != 0){
         //if(depth[a] - (1<<i) > depth[b]){
            a = lca[a][i];
         }
      }
      if(a==b) return a;
      
      for(int i = MAXD-1; i >= 0; i--){
         if(lca[a][i] != lca[b][i]){
            a = lca[a][i];
            b = lca[b][i];
         }
      }
      return lca[a][0];
   }
            
   
   
   public static long dist(int a, int b){
      //System.out.println("lca: " + lca(a,b));
      return rootdist[a] + rootdist[b] - 2*rootdist[lca(a,b)];
   }
   
   public static void initdfs(int v, int p){
      components[v] = compnum;
      incomponent.add(v);
      
      for(Edge e : adj.get(v)){
         if(e.to == p) continue;
         if(components[e.to] != -1){
            //this is the extra edge
            extra_edge = e.i;
            continue;
         }
         depth[e.to] = depth[v]+1;
         lca[e.to][0] = v;
         rootdist[e.to] = rootdist[v] + e.w;
         initdfs(e.to,v);
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
        if (c > 32) return true;
        while (true) {
            c = getChar();
            if (c == NC) return false;
            else if (c > 32) return true;
        }
    }
}