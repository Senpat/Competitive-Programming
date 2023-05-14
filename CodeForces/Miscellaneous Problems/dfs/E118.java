//Bertown Roads
import java.io.*;
import java.util.*;
//dfs tree practice https://codeforces.com/blog/entry/68138
public class E118{
   
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static int[] backfrom;
   public static int[] backto;
   public static boolean[] seen;
   public static boolean[] back;             //is back edge
   
   public static int[][] answer;
   
   public static int[] dp;       //dp[v] counts # of backedges that go over edge between v and its parent
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int n = fs.nextInt();
      int m = fs.nextInt();
      
      adj = new ArrayList<ArrayList<Edge>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      for(int k = 0; k < m; k++){
         int a = fs.nextInt();
         int b = fs.nextInt();
         
         adj.get(a).add(new Edge(b,k));
         adj.get(b).add(new Edge(a,k));
      }
      
      //dfs tree
      backfrom = new int[n+1];
      backto = new int[n+1];
      seen = new boolean[n+1];
      seen[1] = true;
      back = new boolean[m];
      
      answer = new int[m][2];
      dfstree(1,-1);
      
      //calculate bridges
      dp = new int[n+1];
      
      bridge(1,-1);
      int numbridges = 0;
      for(int k = 2; k <= n; k++){
         if(dp[k] == 0) numbridges++;
      }
      
      if(numbridges > 0){
         out.println(0);
      } else {
         StringJoiner sj = new StringJoiner("\n");
         for(int k = 0; k < m; k++){
            sj.add(answer[k][0] + " " + answer[k][1]);
         }
         out.println(sj.toString());
      }
      
      
      
      
      out.close();
   }
   
   public static void bridge(int v, int p){
      int pre = 0;
      for(Edge e : adj.get(v)){
         if(back[e.i] || e.to == p) continue;
         bridge(e.to,v);
         pre += dp[e.to];
      }
      
      dp[v] = backfrom[v] - backto[v] + pre;
   }
   
   public static void dfstree(int v, int p){
      for(Edge e: adj.get(v)){   
         if(e.to == p || back[e.i]) continue;
         answer[e.i][0] = v;
         answer[e.i][1] = e.to;
         if(seen[e.to]){
            backfrom[v]++;
            backto[e.to]++;
            back[e.i] = true;
         } else {
            seen[e.to] = true;
            back[e.i] = false;
            dfstree(e.to,v);
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