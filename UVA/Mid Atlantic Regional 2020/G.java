//make sure to make new file!
import java.io.*;
import java.util.*;

public class G{
   
   public static void main(String[] args)throws IOException{
      //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      //StringTokenizer st = new StringTokenizer(f.readLine());
      
      FastScanner fs = new FastScanner();
      
      int n  = fs.nextInt();
      int m  = fs.nextInt();
      int rs = fs.nextInt();
      int bs = fs.nextInt();
      int ys = fs.nextInt();
      
      ArrayList<ArrayList<Edge>> adjr = new ArrayList<ArrayList<Edge>>(n+1);
      ArrayList<ArrayList<Edge>> adjo = new ArrayList<ArrayList<Edge>>(n+1);
      ArrayList<ArrayList<Edge>> adjy = new ArrayList<ArrayList<Edge>>(n+1);
      ArrayList<ArrayList<Edge>> adjg = new ArrayList<ArrayList<Edge>>(n+1);
      ArrayList<ArrayList<Edge>> adjb = new ArrayList<ArrayList<Edge>>(n+1);
      ArrayList<ArrayList<Edge>> adjp = new ArrayList<ArrayList<Edge>>(n+1);
      ArrayList<ArrayList<Edge>> adjx = new ArrayList<ArrayList<Edge>>(n+1);
      
      for(int k = 0; k <= n; k++){
         adjr.add(new ArrayList<Edge>());
         adjo.add(new ArrayList<Edge>());
         adjy.add(new ArrayList<Edge>());
         adjg.add(new ArrayList<Edge>());
         adjb.add(new ArrayList<Edge>());
         adjp.add(new ArrayList<Edge>());
         adjx.add(new ArrayList<Edge>());
      }
      
      int numx = 0;
      for(int k = 0; k < m; k++){
         //st = new StringTokenizer(f.readLine());
         
         int a = fs.nextInt();
         int b = fs.nextInt();
         
         char c = fs.getChar();
         
         if(c == 'R'){
            adjr.get(a).add(new Edge(b,k));
            adjr.get(b).add(new Edge(a,k));
         }
         if(c == 'O'){
            adjo.get(a).add(new Edge(b,k));
            adjo.get(b).add(new Edge(a,k));
         }
         if(c == 'Y'){
            adjy.get(a).add(new Edge(b,k));
            adjy.get(b).add(new Edge(a,k));
         }
         if(c == 'G'){
            adjg.get(a).add(new Edge(b,k));
            adjg.get(b).add(new Edge(a,k));
         }
         if(c == 'B'){
            adjb.get(a).add(new Edge(b,k));
            adjb.get(b).add(new Edge(a,k));
         }
         if(c == 'P'){
            adjp.get(a).add(new Edge(b,k));
            adjp.get(b).add(new Edge(a,k));
         }
         if(c == 'X'){
            adjx.get(a).add(new Edge(b,k));
            adjx.get(b).add(new Edge(a,k));
            numx++;
         }
      }
      
      boolean[][][] seen = new boolean[n+1][n+1][n+1];
      seen[rs][bs][ys] = true;
      Queue<State> q = new LinkedList<State>();
      q.add(new State(rs,bs,ys));
      
      HashSet<Integer> edgesseen = new HashSet<Integer>();
      
      while(!q.isEmpty()){
         State s = q.poll();
         
         int r = s.r;
         int b = s.b;
         int y = s.y;
         
         /*
         if(seen[r][b][y]) 
            continue;
         seen[r][b][y] = true;
         */
         
         //r
         for(Edge e : adjr.get(r)){
            edgesseen.add(e.index);
            if(!seen[e.to][b][y]){                     //don't need to check edgesseen
               q.add(new State(e.to,b,y));
               seen[e.to][b][y] = true;
            }
         }
         //b
         for(Edge e : adjb.get(b)){
            edgesseen.add(e.index);
            if(!seen[r][e.to][y]){                     //don't need to check edgesseen
               q.add(new State(r,e.to,y));
               seen[r][e.to][y] = true;
            }
         }
         //y
         for(Edge e : adjy.get(y)){
            edgesseen.add(e.index);
            if(!seen[r][b][e.to]){                     //don't need to check edgesseen
               q.add(new State(r,b,e.to));
               seen[r][b][e.to] = true;
            }
         }
         
         //o
         if(r == y){
            for(Edge e : adjo.get(r)){
               edgesseen.add(e.index);
               if(!seen[e.to][b][e.to]){
                  q.add(new State(e.to,b,e.to));
                  seen[e.to][b][e.to] = true;
               }
            }
         }
         
         //g
         if(b == y){
            for(Edge e : adjg.get(b)){
               edgesseen.add(e.index);
               if(!seen[r][e.to][e.to]){
                  q.add(new State(r,e.to,e.to));
                  seen[r][e.to][e.to] = true;
               }
            }
         }
         
         //p
         if(r == b){
            for(Edge e : adjp.get(b)){
               edgesseen.add(e.index);
               if(!seen[e.to][e.to][y]){
                  q.add(new State(e.to,e.to,y));
                  seen[e.to][e.to][y] = true;
               }
            }
         }
      
         //x
         for(Edge e : adjx.get(r)){
            if(!seen[e.to][b][y]){
               q.add(new State(e.to,b,y));
               seen[e.to][b][y] = true;
            }
         }
         for(Edge e : adjx.get(b)){
            if(!seen[r][e.to][y]){
               q.add(new State(r,e.to,y));
               seen[r][e.to][y] = true;
            }
         }
         for(Edge e : adjx.get(y)){
            if(!seen[r][b][e.to]){
               q.add(new State(r,b,e.to));
               seen[r][b][e.to] = true;
            }
         }
      }
      
      if(edgesseen.size() == m-numx){
         out.println(1);
      } else {
         out.println(0);
      }
      
      
      out.close();
   }
   
   public static class Edge{
      int to;
      int index;
      public Edge(int a, int c){
         to = a;
         index = c;
      }
   }
   
   public static class State{
      int r;
      int b;
      int y;
      public State(int a, int bb, int c){
         r = a;
         b = bb;
         y = c;
      }
   }
   
   public static class FastScanner
   {
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
      
}