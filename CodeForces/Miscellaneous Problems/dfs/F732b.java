//Tourist Reform
import java.io.*;
import java.util.*;
//based on F732.java, uses iterative bfs instead of recursive dfs, still mle
public class F732b{
   
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static boolean[] seen;
   public static boolean[] isbackedge;
   public static int[] backto;
   public static int[] backfrom;
   public static int[] parentedge;
   
   public static int[] numbackedges;            //num backedges that go over that edge
   
   public static int size;
   
   public static int[][] edges;                 //stores orientation of edges (answer)
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int n = fs.nextInt();
      int m = fs.nextInt();
      
      adj = new ArrayList<>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      for(int k = 0; k < m; k++){
         int a = fs.nextInt();
         int b = fs.nextInt();
         
         adj.get(a).add(new Edge(b,k));
         adj.get(b).add(new Edge(a,k));
      }
      
      seen = new boolean[n+1];
      isbackedge = new boolean[m];
      backto = new int[n+1];
      backfrom = new int[n+1];
      parentedge = new int[n+1];
      Arrays.fill(parentedge,-1);
      
      edges = new int[m][2];
      
      dfs(1);
      
      if(n+m == 800000){
         out.println("finish dfs");
         out.close();
         return;
      }
      
      numbackedges = new int[m];
      
      calcbridges(1);
      
      //get maximum size of component after removing all bridges
      int max = 0;
      int maxroot = 1;
      Arrays.fill(seen,false);
      for(int k = 1; k <= n; k++){
         if(seen[k]) 
            continue;
         seen[k] = true;
         size = 0;
         bfs2(k);
         if(size > max){
            max = size;
            maxroot = k;
         }
      }
      
      
      Arrays.fill(seen,false);
      //get orientation of bridges
      seen[maxroot] = true;
      bfs3(maxroot);
      
      StringJoiner sj = new StringJoiner("\n");
      sj.add("" + max);
      for(int k = 0; k < m; k++){
         sj.add("" + edges[k][0] + " " + edges[k][1]);
      }
      out.println(sj.toString());
      
      
      out.close();
   }
   
   public static void bfs3(int start){
      ArrayDeque<Integer> q = new ArrayDeque<Integer>();
      seen[start] = true;
      q.add(start);
      while(!q.isEmpty()){
         int v = q.poll();
      
         for(Edge e: adj.get(v)){
            if(seen[e.to]) 
               continue;
            if(numbackedges[e.i] == 0 && !isbackedge[e.i]){
               //edge is bridge
               //point towards maxroot
               edges[e.i][0] = e.to;
               edges[e.i][1] = v;
            }
            
            seen[e.to] = true;
            q.add(e.to);
         }
      }
   }
   
   public static void bfs2(int start){
      ArrayDeque<Integer> q = new ArrayDeque<Integer>();
      seen[start] = true;
      q.add(start);
      
      while(!q.isEmpty()){
         size++;
         int v = q.poll();
         for(Edge e : adj.get(v)){
            if(seen[e.to] || numbackedges[e.i] == 0) 
               continue;       //skip edge if edge is bridge or node is seen
            seen[e.to] = true;
            q.add(e.to);   
         }
      }
      
   }
   
   public static void calcbridges(int start){
      Stack<State> stack = new Stack<State>();
      stack.add(new State(start,-1));
      
      while(!stack.isEmpty()){
         State s = stack.pop();
         int v = s.v;
         int p = s.p;
         
         if(p == -2){
            //after visiting children
            for(Edge e : adj.get(v)){
               if(e.i == parentedge[v] || isbackedge[e.i]) 
                  continue;
               if(parentedge[v] != -1){
                  numbackedges[parentedge[v]] += numbackedges[e.i];
               }
            }
            //System.out.println("done " + v);
            continue;
         }
         //System.out.println("start " + v);
         if(parentedge[v] != -1){
            numbackedges[parentedge[v]] = backfrom[v] - backto[v];
         }
         
         stack.push(new State(v,-2));
         
         for(Edge e : adj.get(v)){
            if(e.to == p || isbackedge[e.i]) 
               continue;
         
            stack.push(new State(e.to,v));
         
         }
      
      }
   
   }
   
   public static void dfs(int v){
      seen[v] = true;
      
      for(Edge e : adj.get(v)){
         if(e.i == parentedge[v] || isbackedge[e.i]) 
            continue;
         
         //scan edges directed down the tree, backedges directed up
         edges[e.i][0] = v;
         edges[e.i][1] = e.to;
         
         if(seen[e.to]){
            isbackedge[e.i] = true;
            backfrom[v]++;
            backto[e.to]++;
         } else {
            parentedge[e.to] = e.i;
            dfs(e.to);
         }
      }
   }   
   
   public static class State{
      int v;
      int p;
      public State(int a, int b){
         v = a;
         p = b;
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