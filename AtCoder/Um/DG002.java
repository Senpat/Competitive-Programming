//Stamp Rally
//Um episode 9
import java.io.*;
import java.util.*;

public class DG002{

   public static int[] parent;
   
   //kruskal's refactoring tree nodes
   public static ArrayList<ArrayList<Integer>> kadj;
   public static int[] subsize;
   
   public static int MAXD = 18;
   public static int[][] jmp;
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int n = fs.nextInt();
      int m = fs.nextInt();
      
      Edge[] edges = new Edge[m+1];
      
      for(int k = 1; k <= m; k++){
         int a = fs.nextInt();
         int b = fs.nextInt();
         
         edges[k] = new Edge(a,b);
      }
      
      int N = 2*n-1;                //# of nodes including Kruskal's Refactoring Tree nodes
      
      //instantiate dsu arrays
      //ONE INDEXED
      parent = new int[N+1];
      
      for(int k = 1; k <= N; k++){
         parent[k] = k;
      }
      
      //max edge index to get to that node
      kadj = new ArrayList<ArrayList<Integer>>(N+1);
      for(int k = 0; k <= N; k++) kadj.add(new ArrayList<Integer>());
      
      int[] nodemax = new int[N+1];
      int nodei = n+1;
      for(int k = 1; k <= m; k++){
         int fa = find(edges[k].a);
         int fb = find(edges[k].b);
         if(fa != fb){
            //new node
            kadj.get(nodei).add(fa);
            kadj.get(fa).add(nodei);
            kadj.get(nodei).add(fb);
            kadj.get(fb).add(nodei);
            
            union(fa,nodei);
            union(fb,nodei);
            
            nodemax[nodei] = k;
            
            nodei++;
         }
      }
      
      subsize = new int[N+1];
      jmp = new int[N+1][MAXD+1];
      for(int k = 0; k <= N; k++){
         jmp[k][0] = k;
      }
      
      //root is N
      initdfs(N,-1);
      
      //calculate jmp
      jmp[N][1] = N;
      for(int d = 2; d <= MAXD; d++){
         for(int k = 1; k <= N; k++){
            jmp[k][d] = jmp[jmp[k][d-1]][d-1];
         }
      }
      
      //answer queries
      int Q = fs.nextInt();
      int[] answer = new int[Q];
      for(int q = 0; q < Q; q++){
         int a = fs.nextInt();
         int b = fs.nextInt();
         int z = fs.nextInt();
         
         //log^2(N)
         
         int l = 0;
         int r = m;
         int ans = 0;
         while(l <= r){
            int mid = l + (r-l)/2;
            
            int ai = a;
            int bi = b;
            
            //jump until you reach mid
            for(int d = MAXD; d >= 0; d--){
               if(nodemax[jmp[ai][d]] <= mid){
                  //jump
                  ai = jmp[ai][d];
               }
               
               if(nodemax[jmp[bi][d]] <= mid){
                  bi = jmp[bi][d];
               }
            }
            
            int val = 0;
            if(ai == bi) val = subsize[ai];
            else val = subsize[ai] + subsize[bi];
            
            if(val >= z){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         answer[q] = ans;
         
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int q = 0; q < Q; q++){
         sj.add("" + answer[q]);
      }
      out.println(sj.toString());
      
      out.close();
   }
   
   public static void initdfs(int v, int p){
      if(p != -1){
         jmp[v][1] = p;
      }
      for(int nei : kadj.get(v)){
         if(nei == p) continue;
         initdfs(nei,v);
         subsize[v] += subsize[nei];
      }
      if(p != 1 && kadj.get(v).size() == 1){
         subsize[v] = 1;
      }  
   }
   
   public static void union(int u, int v){
      int findv = find(v);
      int findu = find(u);
      parent[findu] = findv;
   }
   
   public static int find(int v){
      if(parent[v] == v) return v;
      else{
         parent[v] = find(parent[v]);
         return parent[v];
         //return find(parent[v]);
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