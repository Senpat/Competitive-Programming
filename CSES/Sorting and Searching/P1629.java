//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1629{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int n = fs.nextInt();
      
      Movie[] movies = new Movie[n];
      HashSet<Integer> hset = new HashSet<Integer>(n*2);
      ArrayList<Integer> alist = new ArrayList<Integer>(n*2);
      for(int k = 0; k < n; k++){
         int start = fs.nextInt();
         int end = fs.nextInt();
         
         movies[k] = new Movie(start,end);
         
         if(!hset.contains(start)){
            hset.add(start);
            alist.add(start);
         }
         
         if(!hset.contains(end)){
            hset.add(end);
            alist.add(end);
         }
      }
      
      Collections.sort(alist);
      HashMap<Integer,Integer> compress = new HashMap<Integer,Integer>(alist.size()*2);
      int N = alist.size();
      for(int k = 0; k < N; k++){
         compress.put(alist.get(k),k+1);
      }
      
      int[] endtimes = new int[N+1];
      
      for(int k = 0; k < n; k++){
         int start = compress.get(movies[k].start);
         int end = compress.get(movies[k].end);
         endtimes[end] = Math.max(endtimes[end],start);
      }
      
      int[] dp = new int[N+1];
      dp[0] = -1;
      for(int k = 1; k <= N; k++){
         dp[k] = Math.max(dp[k-1],dp[endtimes[k]]+1);
      }
      
      out.println(dp[N]);
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Movie{
      int start;
      int end;
      public Movie(int a, int b){
         start = a;
         end = b;
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