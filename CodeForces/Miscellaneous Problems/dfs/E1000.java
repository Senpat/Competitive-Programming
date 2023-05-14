//We Need More Bosses
import java.io.*;
import java.util.*;

public class E1000{

   public static ArrayList<ArrayList<Edge>> adj;
   
   public static boolean[] seen;
   public static boolean[] isbackedge;
   public static int[] backto;
   public static int[] backfrom;
   public static int[] parentedge;
   
   public static int[] backedges;            //# of backedges going over edge x
   
   public static int[][] subbridges;         //first and second most bridges in path to leaf
   
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
      
      dfs(1,-1);
      
      backedges = new int[m];
      
      findbridges(1,-1);
      
      subbridges = new int[n+1][2];
      
      dfs2(1,-1);
      
      int answer = 0;
      for(int k = 1; k <= n; k++){
         answer = Math.max(answer,subbridges[k][0] + subbridges[k][1]);
      }
      out.println(answer);
      
      out.close();
   }
   
   public static void dfs2(int v, int p){
      
      for(Edge e : adj.get(v)){
         if(e.to == p) continue;
         if(isbackedge[e.i]) continue;
         
         dfs2(e.to,v);
         
         int cur = subbridges[e.to][0];
         if(backedges[e.i] == 0) cur++;
         
         if(cur > subbridges[v][0]){
            subbridges[v][1] = subbridges[v][0];
            subbridges[v][0] = cur;
         } else if(cur > subbridges[v][1]){
            subbridges[v][1] = cur;
         }
      }
      
   }
   
   public static void findbridges(int v, int p){
      
      if(parentedge[v] != -1){
         backedges[parentedge[v]] = backfrom[v] - backto[v];
      }
      
      for(Edge e : adj.get(v)){
         if(e.to == p) continue;
         if(isbackedge[e.i]) continue;
         
         findbridges(e.to,v);
         
         if(parentedge[v] != -1){
            backedges[parentedge[v]] += backedges[e.i];
         }
      }
      
   }
   
   //dfs tree
   public static void dfs(int v, int p){
      seen[v] = true;
      
      for(Edge e : adj.get(v)){
         if(e.to == p) continue;
         if(isbackedge[e.i]) continue;
         
         if(seen[e.to]){
            isbackedge[e.i] = true;
            backfrom[v]++;
            backto[e.to]++;
         } else {
            parentedge[e.to] = e.i;
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