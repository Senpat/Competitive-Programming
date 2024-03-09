//Ciel and Commander
import java.io.*;
import java.util.*;
//centroid decomposition
public class C321b{
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static boolean[] marked;
   
   public static int[] level;
   
   public static int[] subsize;
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));      //fast scanner is slower
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      level = new int[n+1];
      marked = new boolean[n+1];
      subsize = new int[n+1];
      solve(1,0);
      
      char[] answer = new char[n+1];
      for(int k = 1; k <= n; k++){
         answer[k] = (char)('A' + level[k]);
      }
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 1; k <= n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      out.close();
   }
   
   public static void dfs_size(int v, int p){
      subsize[v] = 1;
      for(int nei : adj.get(v)){
         if(nei == p || marked[nei]) continue;
         dfs_size(nei,v);
         subsize[v] += subsize[nei];
      }
   }
   
   public static int dfs_centroid(int v, int p, int sz){
      
      for(int nei : adj.get(v)){
         if(nei == p || marked[nei]) continue;
         if(subsize[nei] > sz/2) return dfs_centroid(nei,v,sz);
      }
      
      return v;
   }
   
   public static void solve(int v, int l){
      dfs_size(v,-1);
      
      int centroid = dfs_centroid(v,-1,subsize[v]);
      level[centroid] = l;
      
      marked[centroid] = true;
      
      for(int nei : adj.get(centroid)){
         if(marked[nei]) continue;
         solve(nei,l+1);
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