//make sure to make new file!
import java.io.*;
import java.util.*;

public class H{

   public static long[] c;
   public static int[] ti;
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static long[][] dp;
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int t = fs.nextInt();
      
      for(int q = 1; q <= t; q++){
      
         int n = fs.nextInt();
      
         c = fs.nextLongs(n);
         ti = fs.nextInts(n);
         
         adj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++){
            adj.add(new ArrayList<Integer>());
         }
         
         for(int k = 0; k < n-1; k++){
            
            int a = fs.nextInt();
            int b = fs.nextInt();       
            
            adj.get(a).add(b);
            adj.get(b).add(a);
         }
         
         //dp[x][y] -> answer (not including itself), 0 is if you can include direct children, 1 if you can't
         dp = new long[n+1][2];
         
         dfs(1,-1);
         
         long answer = dp[1][0] + c[1];
         
         out.println(answer);
         
         
         
         
      
      
      }
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      
      long sum0s = 0L;
      
      int maxnei = -1;
      int maxnei2 = -1;
      
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         dfs(nei,v);
      
         sum0s += dp[nei][0];
         
         if(ti[nei] == 3){
            if(maxnei == -1 || c[nei] > c[maxnei]){
               maxnei2 = maxnei;
               maxnei = nei;
            } else if(maxnei2 == -1 || c[nei] > c[maxnei2]){
               maxnei2 = nei;
            }
         }
      
      }
      
      long max = 0L;
      
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         
         //try using it (go to cur, do some vertex with t=3, and then come right back
         long cur = sum0s-dp[nei][0]+dp[nei][1]+c[nei];
         //you can also add something if it's t==3
         if(maxnei != -1 && maxnei != nei) cur += c[maxnei];
         else if(maxnei2 != -1) cur += c[maxnei2];
         
         max = Math.max(max,cur);
         
         
         //do full nei
         max = Math.max(max,sum0s + c[nei]);
         
         
         
      }
      
      
      
      dp[v][0] = max;
      dp[v][1] = sum0s;
      
      
      
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