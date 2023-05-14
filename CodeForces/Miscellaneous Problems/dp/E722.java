//Research Rover
import java.io.*;
import java.util.*;
//tutorial
public class E722{

   public static long MOD = 1000000007L;
   
   public static long[] fac;
   public static long[] ifac;
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 2000005;
      fac = new long[N];
      ifac = new long[N];
      
      fac[0] = 1L;
      for(int k = 1; k < N; k++){
         fac[k] = (fac[k-1] * k + MOD)%MOD;
      }
      
      ifac[N-1] = modInverse(fac[N-1],MOD);
      
      for(int k = N-2; k >= 0; k--){
         ifac[k] = (ifac[k+1] * (k+1) + MOD)%MOD;
      }
      
      
      int n = fs.nextInt();
      int m = fs.nextInt();
      int a = fs.nextInt();
      long s = fs.nextLong();
      
      ArrayList<Anomaly> anomalies = new ArrayList<Anomaly>();
      boolean has11 = false;
      for(int k = 0; k < a; k++){
         int x = fs.nextInt();
         int y = fs.nextInt();
         
         anomalies.add(new Anomaly(x,y));
         
         if(x == 1 && y == 1) has11 = true;
      }
      
      if(!has11){
         anomalies.add(new Anomaly(1,1));
         a++;
      }
      
      Collections.sort(anomalies);
     
      long[][] allpath = new long[a][a];
      for(int k = 0; k < a; k++){
         for(int j = 0; j < a; j++){
            if(k == j) continue;
            Anomaly anomaly1 = anomalies.get(k);
            Anomaly anomaly2 = anomalies.get(j);
            
            if(anomaly2.x >= anomaly1.x && anomaly2.y >= anomaly1.y){
               allpath[k][j] = getpath(anomaly2.x-anomaly1.x,anomaly2.y-anomaly1.y);
            }
         }
      }
      
      
      int M = 21;
      //G[a][b] = # of ways to go from anomaly a to (n,m) and use b (not including itself)
      //G[a][0] = F[a] from the tutorial
      long[][] G = new long[a][M];
      
      //fill G[a][0]
      for(int k = a-1; k >= 0; k--){
         Anomaly anomaly = anomalies.get(k);
         long path = getpath(n-anomaly.x,m-anomaly.y);
         
         for(int j = k+1; j < a; j++){
            Anomaly anomaly2 = anomalies.get(j);
            if(anomaly2.x >= anomaly.x && anomaly2.y >= anomaly.y){
               path -= (allpath[k][j] * G[j][0] + MOD)%MOD;
               if(path < 0) path += MOD;
            }
         }
         
         G[k][0] = path;
      }
      
      //fill G
      for(int v = 1; v < M; v++){  
         for(int k = a-1; k >= 0; k--){
            Anomaly anomaly = anomalies.get(k);
            long path = getpath(n-anomaly.x,m-anomaly.y);
            for(int j = k+1; j < a; j++){
               Anomaly anomaly2 = anomalies.get(j);
               if(anomaly2.x >= anomaly.x && anomaly2.y >= anomaly.y){
                  path -= (allpath[k][j] * G[j][v] + MOD)%MOD;
                  if(path < 0) path += MOD;
               }
            }
            
            for(int j = 0; j < v; j++){
               path -= G[k][j];
               if(path < 0) path += MOD;
            }
            G[k][v] = path;
         }
         
      }
      
      long[] battery = new long[M];
      battery[0] = s;
      for(int k = 1; k < M; k++){
         battery[k] = (battery[k-1] + 1)/2;
      }
      
      long path = getpath(n-1,m-1);
      long answer = 0L;
      
      int offset = has11 ? 1 : 0;
      for(int v = 0; v < M; v++){
         if(v+offset >= M) continue;
         long prod = (G[0][v] * battery[v+offset] + MOD)%MOD;
         answer = answer + prod;
         if(answer >= MOD) answer -= MOD;
         path -= G[0][v];
         if(path < 0) path += MOD;
      }
      
      //>= M anomalies
      answer = answer + path;
      if(answer >= MOD) answer -= MOD;
      
      answer = (answer * modInverse(getpath(n-1,m-1),MOD) + MOD)%MOD;
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static long getpath(int x, int y){
      return choose(x+y,y);
   }
   
   public static long choose(int n, int r){
      long prod = (fac[n] * ifac[r] + MOD)%MOD;
      return (prod * ifac[n-r] + MOD)%MOD;
   }
   
   
   public static class Anomaly implements Comparable<Anomaly>{
      int x;
      int y;
      public Anomaly(int a, int b){
         x = a;
         y = b;
      }
      public int compareTo(Anomaly a){
         if(x == a.x) return y-a.y;
         return x-a.x;
      }
   }
   
   
   
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