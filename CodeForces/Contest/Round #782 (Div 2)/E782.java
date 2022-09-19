//make sure to make new file!
import java.io.*;
import java.util.*;

public class E782{
   
   public static ArrayList<ArrayList<Edge>> adj;
   public static int[][] comp;
   public static int curcomp;
   public static boolean[] seen;
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int n = fs.nextInt();
      int m = fs.nextInt();
      
      adj = new ArrayList<ArrayList<Edge>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      //adjacent to even edge
      HashSet<Integer> adeven = new HashSet<Integer>(); 
      for(int k = 0; k < m; k++){
         int a = fs.nextInt();
         int b = fs.nextInt();
         int w = fs.nextInt();
         
         adj.get(a).add(new Edge(b,w));
         adj.get(b).add(new Edge(a,w));
         
         if(w%2 == 0){
            adeven.add(a);
            adeven.add(b);
         }
      }
      
      //precompute comps
      comp = new int[n+1][30];
      for(int k = 0; k < 30; k++){
         curcomp = 1;
         seen = new boolean[n+1];
         for(int v = 1; v <= n; v++){
            if(seen[v]) continue;
            dfs(v,k);
            curcomp++;
         }
      }
      
      //true if that node can each an even number without getting to and of 1 (means you can get a mex of 1)
      boolean[] can1 = new boolean[n+1];
      
      for(int x = 1; x < 30; x++){                             //start at 1 because you need a bit other than 1 to be true 
         Queue<Integer> q = new LinkedList<Integer>();
         seen = new boolean[n+1];
         for(int i : adeven){
            q.add(i);
            seen[i] = true;
         }
         
         while(!q.isEmpty()){
            int v = q.poll();
            
            can1[v] = true;
            seen[v] = true;
            
            for(Edge e : adj.get(v)){
               if((e.w & (1 << x)) == 0) continue;
               if(seen[e.to]) continue; 
               q.add(e.to);
            }
         }
      }
      
      int Q = fs.nextInt();
      StringJoiner sj = new StringJoiner("\n");
      for(int q = 0; q < Q; q++){
         int u = fs.nextInt();
         int v = fs.nextInt();
         
         //check 0
         boolean check0 = false;
         for(int k = 0; k < 30; k++){
            if(comp[u][k] == comp[v][k]){
               check0 = true;
               break;
            }
         }
         
         if(check0){
            sj.add("0");
            continue;
         }
         
         if(can1[u]){
            sj.add("1");
         } else {
            sj.add("2");
         }
      }
           
      
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int x){
      
      comp[v][x] = curcomp;
      seen[v] = true;
      
      for(Edge e : adj.get(v)){
         if((e.w & (1 << x)) == 0) continue;
         if(seen[e.to]) continue;
         dfs(e.to,x);
      }
   }
   
   
   public static class Edge{
      int to;
      int w;
      public Edge(int a, int b){
         to = a;
         w = b;
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