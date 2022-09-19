//make sure to make new file!
import java.io.*;
import java.util.*;
//tutorial, Kruskal's Refactoring tree
public class H{
   
   public static int[] a;
   public static int[] parent;
   public static int[] sizes;
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] nodevals;
   
   public static int MAXD = 17;
   public static int[][] jmp;
   public static int[][] maxes;
   public static int[] subsums;           //subtree sums, not including itself
   public static int[] need;              //how much you need to start with to go to the parent
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int n = fs.nextInt();
      int m = fs.nextInt();
      int q = fs.nextInt();
      
      a = new int[2*n];
      for(int k = 1; k <= n; k++){
         a[k] = fs.nextInt();
      }
      
      PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
      
      for(int k = 0; k < m; k++){
         int a = fs.nextInt();
         int b = fs.nextInt();
         int w = fs.nextInt();
         
         pq.add(new Edge(a,b,w));
      }
      
      //build kruskal's refactoring tree
      //instantiate dsu arrays
      //ONE INDEXED
      parent = new int[2*n];
      
      for(int k = 1; k <= 2*n-1; k++){
         parent[k] = k;
      }
      
      sizes = new int[2*n];                     //stores depth of every dsu
      Arrays.fill(sizes,1);
      
      //initial tree
      //nodes numbered from 1 to 2*n-1
      adj = new ArrayList<ArrayList<Integer>>(2*n);
      for(int k = 0; k < 2*n; k++){
         adj.add(new ArrayList<Integer>());
      }
      //nodes 1-n are initial nodes, with value 0
      //nodes n+1 to 2*n-1 are nodes from the kruskals algorithm
      nodevals = new int[2*n];
      
      int nodei = n+1;
      while(!pq.isEmpty()){
         Edge e = pq.poll();
         
         int ahead = find(e.a);
         int bhead = find(e.b);
         
         if(ahead == bhead) continue;
         
         //make new node
         nodevals[nodei] = e.w;
         union(ahead,bhead,nodei);
         //edge between e.a and nodei, and edge between e.b and nodei
         adj.get(ahead).add(nodei);
         adj.get(nodei).add(ahead);
         adj.get(bhead).add(nodei);
         adj.get(nodei).add(bhead);
         
         //out.println(e.a + " " + e.b + " " + nodei + " " + e.w);
         
         nodei++;
         
      }
      
      
      //binary lifting, get maxes, precomp subtree sums
      subsums = new int[2*n];
      jmp = new int[2*n][MAXD];
      for(int k = 0; k < 2*n; k++){
         for(int j = 0; j < MAXD; j++){
            jmp[k][j] = 2*n-1;
         }
      }
      maxes = new int[2*n][MAXD];
      dfs(2*n-1,-1);
         
      need = new int[2*n];
      for(int k = 0; k < 2*n-1; k++){
         need[k] = Math.max(0,nodevals[jmp[k][0]]-subsums[k]-a[k]);
         maxes[k][0] = need[k];
      }
      need[2*n-1] = 0;
      
      for(int d = 1; d < MAXD; d++){
         for(int k = 1; k < 2*n; k++){
            jmp[k][d] = jmp[jmp[k][d-1]][d-1];
            maxes[k][d] = Math.max(maxes[k][d-1],maxes[jmp[k][d-1]][d-1]);
         }
      }
      
      for(int qq = 0; qq < q; qq++){
         int ni = fs.nextInt();
         int xi = fs.nextInt();
      
         int i = ni;
         for(int d = MAXD-1; d >= 0; d--){
            if(maxes[i][d] <= xi){
               i = jmp[i][d];
            }
         }
         
         int answer = a[i]+subsums[i]+xi;
         out.println(answer);
      }
         
      
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         jmp[nei][0] = v;
         dfs(nei,v);
         subsums[v] += a[nei] + subsums[nei];
      }
   }
   
   
   public static void union(int u, int v, int nodei){
      int findv = find(v);
      int findu = find(u);
      //sizes[findv] += sizes[findu];
      //parent[findu] = findv;
      
      parent[findv] = nodei;
      parent[findu] = nodei;
      sizes[nodei] += sizes[findv] + sizes[findu];
      
   }
   
   public static int find(int v){
      if(parent[v] == v) 
         return v;
      else{
         parent[v] = find(parent[v]);
         return parent[v];
         //return find(parent[v]);
      }
   }

   
   
   public static class Edge implements Comparable<Edge>{
      int a;
      int b;
      int w;
      
      public Edge(int c, int d, int e){
         a = c;
         b = d;
         w = e;
      }
      
      public int compareTo(Edge e){
         return w-e.w;
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
        int[] res = new int[N+1];
        for (int i = 1; i <= N; i++) {
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