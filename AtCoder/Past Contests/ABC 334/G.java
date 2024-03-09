//make sure to make new file!
import java.io.*;
import java.util.*;

public class G{

   public static long MOD = 998244353L;
   
   public static int n,m;
   public static char[][] board;
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static boolean[] seen;
   public static int[] disc;
   public static int[] low;
   public static int time;
   
   public static long[] split;
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      n = fs.nextInt();
      m = fs.nextInt();
      int N = n*m;
      
      board = new char[n][m];
      for(int k = 0; k < n; k++){
         String s = fs.next();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
         }
      }
      
      adj = new ArrayList<>(N);
      for(int k = 0; k < N; k++){
         adj.add(new ArrayList<Integer>());
      }
      
      boolean[] green = new boolean[N];
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(board[k][j] != '#') continue;
            int to = to(k,j);
            green[to] = true;
            if(k+1 < n && board[k+1][j] == '#'){
               int to2 = to(k+1,j);
               adj.get(to).add(to2);
               adj.get(to2).add(to);
            }
            if(j+1 < m && board[k][j+1] == '#'){
               int to2 = to(k,j+1);
               adj.get(to).add(to2);
               adj.get(to2).add(to);
            }
         }
      }
      
      //find articulation points
      //https://www.geeksforgeeks.org/articulation-points-or-cut-vertices-in-a-graph/
      seen = new boolean[N];
      disc = new int[N];
      low = new int[N];
      Arrays.fill(low,Integer.MAX_VALUE);
      
      split = new long[N];
      
      long numcomp = 0L;
      for(int k = 0; k < N; k++){
         if(!green[k] || seen[k]) continue;
         time = 0;
         dfs(k,-1);
         numcomp++;
      }
      
      long numer = 0L;
      long denom = 0L;
      
      for(int k = 0; k < N; k++){
         if(green[k]){
            denom++;
            numer = add(numer,add(numcomp,split[k]));
         }
      }
      
      long answer = (numer * modInverse(denom,MOD) + MOD)%MOD;
      out.println(answer);
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      seen[v] = true;
      disc[v] = time;
      time++;
      
      long children = 0;
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         if(seen[nei]){
            low[v] = Math.min(low[v],disc[nei]);
         } else {
            children++;
            dfs(nei,v);
            
            low[v] = Math.min(low[v],low[nei]);
            
            if(low[nei] >= disc[v]){
               split[v]++;
            }
            
         }
      }
      
      //special case: root
      if(p == -1){
         split[v] = children-1;
      }
   }
   
   
   public static int to(int x, int y){
      return x*m+y;
   }
   
   public static long add(long a, long b){
      long ret = a+b;
      if(ret >= MOD) ret -= MOD;
      return ret;
   }
   
   /*
   //wrong method of finding articulation points
   public static void dfs2(int v, int p){
      
      for(Edge e : adj.get(v)){
         if(e.to == p || isbackedge[e.i]) continue;
         dfs(e.to,v);
         
         backover[v] += backover[e.to] + backfrom[e.to];
      }
      backover[v] -= backto[v];
   }
   
   public static void dfs(int v, int p){
      seen[v] = true;
      
      for(Edge e : adj.get(v)){
         if(e.to == p || edgeseen[e.i]) continue;
         edgeseen[e.i] = true;
         if(seen[e.to]){
            isbackedge[e.i] = true;
         } else {
            dfs(e.to,v);
         }
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
   */
   
        //from geeksforgeeks
   public static long modInverse(long a, long m) 
   { 
       long m0 = m; 
       long y = 0;
       long x = 1; 
     
       if (m == 1) 
         return 0; 
     
       while (a > 1) 
       { 
           // q is quotient 
           long q = a / m; 
           long t = m; 
     
           // m is remainder now, process same as 
           // Euclid's algo 
           m = a % m;
           a = t; 
           t = y; 
     
           // Update y and x 
           y = x - q * y; 
           x = t; 
       } 
     
       // Make x positive 
       if (x < 0) 
          x += m0; 
       return x; 
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