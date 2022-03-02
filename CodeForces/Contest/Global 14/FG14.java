//make sure to make new file!
import java.io.*;
import java.util.*;

public class FG14{
   
   public static int[] parent;
   public static int[] sizes;
   
   public static void main(String[] args)throws IOException{
      //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      //StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = fs.nextInt();
      int m = fs.nextInt();
      long x = fs.nextLong();
      
      //instantiate dsu arrays
      //ONE INDEXED
      parent = new int[n+1];
      
      for(int k = 1; k <= n; k++){
         parent[k] = k;
      }
      
      sizes = new int[n+1];                     //stores depth of every dsu
      Arrays.fill(sizes,1);
      
      long[] asphalt = new long[n+1];
      //st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= n; k++){
         asphalt[k] = fs.nextLong();
      }
      
      ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      Edge[] edges = new Edge[m+1];
      for(int k = 1; k <= m; k++){
         //st = new StringTokenizer(f.readLine());
         
         int a = fs.nextInt();
         int b = fs.nextInt();
         
         adj.get(a).add(new Edge(a,b,k));
         adj.get(b).add(new Edge(b,a,k));
         edges[k] = new Edge(a,b,k);
      }
      
      
      TreeSet<Integer> tset = new TreeSet<Integer>(new Comparator<Integer>(){
         public int compare(Integer i1, Integer i2){
            Edge e1 = edges[(int)i1];
            Edge e2 = edges[(int)i2];
            
            long e1num = asphalt[find(e1.from)] + asphalt[find(e1.to)];
            long e2num = asphalt[find(e2.from)] + asphalt[find(e2.to)];
            
            if(e1num > e2num) return 1;
            else if(e1num < e2num) return -1;
            return (int)(i1-i2);
         }
      });
      
      for(int k = 1; k <= m; k++){
         tset.add(k);
         //out.println(tset.size());
      }
      
      ArrayList<Integer> answer = new ArrayList<Integer>();
      HashSet<Integer> seen = new HashSet<Integer>();
      while(answer.size() < n-1 && !tset.isEmpty()){
         int i = tset.pollLast();
         seen.add(i);
         if(find(edges[i].from) == find(edges[i].to)) continue;
         
         if(asphalt[find(edges[i].from)] + asphalt[find(edges[i].to)] < x) break;
         
         //remove edges from tset
         for(Edge e : adj.get(edges[i].from)){
            tset.remove(e.i);
         }
         for(Edge e : adj.get(edges[i].to)){
            tset.remove(e.i);
         }
         
         long newasphalt = asphalt[find(edges[i].from)] + asphalt[find(edges[i].to)] - x;
         //union
         union(edges[i].from,edges[i].to);
         asphalt[find(edges[i].from)] = newasphalt;
         
         //add the edges back
         for(Edge e : adj.get(edges[i].from)){
            if(!seen.contains(e.i)) tset.add(e.i);
         }
         for(Edge e : adj.get(edges[i].to)){
            if(!seen.contains(e.i)) tset.add(e.i);
         }
         
         answer.add(i);
      }
      
      if(answer.size() == n-1){
         out.println("YES");
         StringJoiner sj = new StringJoiner("\n");
         for(int k = 0; k < n-1; k++){
            sj.add("" + answer.get(k));
         }
         out.println(sj.toString());
      } else {
         out.println("NO");
      }
      
      
      
      
      out.close();
   }
   
   public static void union(int u, int v){
      int findv = find(v);
      int findu = find(u);
      sizes[findv] += sizes[findu];
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
      int from;
      int to;
      int i;
      
      public Edge(int a, int b, int c){
         from = a;
         to = b;
         i = c;
      }
   }
   
      
}

class FastScanner
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