//make sure to make new file!
import java.io.*;
import java.util.*;
//MAJOR BUG: set equal to 1 in initial states instead of +=1
//because mod, some initial states could be the same
public class J{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int t = fs.nextInt();
      
      for(int q = 1; q <= t; q++){
      
         char[] lin = fs.next().toCharArray();
         char[] rin = fs.next().toCharArray();
         
         int n = rin.length;
         
         int[] l = new int[n];
         int[] r = new int[n];
         for(int k = 0; k < n; k++){
            if(k+lin.length < n) l[k] = 0;
            else l[k] = lin[k-(n-lin.length)]-'0';
            //out.println(l[k]);
            r[k] = rin[k]-'0';
         }
         
         int mod = fs.nextInt();
      
         int[] pow10 = new int[n];
         pow10[n-1] = 1;
         for(int k = n-2; k >= 0; k--){
            pow10[k] = mul(10,pow10[k+1],mod);
         }
      
         //# digit, sum of digits mod m, f(x)-x mod m, is min, is max
         long[][][][][] dp = new long[n][mod][mod][2][2];
         
         if(r[0] == l[0]){
            dp[0][r[0]%mod][sub(0,mul(pow10[0],r[0],mod),mod)][1][1] = 1L;
         } else {
            dp[0][l[0]%mod][sub(0,mul(pow10[0],l[0],mod),mod)][1][0] = 1L;
            dp[0][r[0]%mod][sub(0,mul(pow10[0],r[0],mod),mod)][0][1] += 1L;
            for(int k = l[0]+1; k <= r[0]-1; k++){
               dp[0][k%mod][sub(0,mul(pow10[0],k,mod),mod)][0][0] += 1L;
            }
         }
         
         for(int d = 0; d < n-1; d++){
            for(int s = 0; s < mod; s++){
               for(int m = 0; m < mod; m++){
                  for(int x = 0; x <= 9; x++){
                     int ns = add(s,x,mod);
                     int m1 = mul(s,x,mod);
                     int a1 = add(m,m1,mod);
                     int m2 = mul(pow10[d+1],x,mod);
                     int nm = sub(a1,m2,mod);
                     dp[d+1][ns][nm][0][0] = add(dp[d+1][ns][nm][0][0],dp[d][s][m][0][0]);
                     
                     //is min
                     if(x == l[d+1]){
                        dp[d+1][ns][nm][1][0] = add(dp[d+1][ns][nm][1][0],dp[d][s][m][1][0]);
                        if(l[d+1] != r[d+1]){
                           dp[d+1][ns][nm][1][0] = add(dp[d+1][ns][nm][1][0],dp[d][s][m][1][1]);
                        }
                     } else if(x > l[d+1]){
                        dp[d+1][ns][nm][0][0] = add(dp[d+1][ns][nm][0][0],dp[d][s][m][1][0]);   
                     }
                     
                     //is max
                     if(x == r[d+1]){
                        dp[d+1][ns][nm][0][1] = add(dp[d+1][ns][nm][0][1],dp[d][s][m][0][1]);
                        if(l[d+1] != r[d+1]){
                           dp[d+1][ns][nm][0][1] = add(dp[d+1][ns][nm][0][1],dp[d][s][m][1][1]);
                        }
                     } else if(x < r[d+1]){
                        dp[d+1][ns][nm][0][0] = add(dp[d+1][ns][nm][0][0],dp[d][s][m][0][1]);
                     }
                     
                     if(x > l[d+1] && x < r[d+1]){
                        dp[d+1][ns][nm][0][0] = add(dp[d+1][ns][nm][0][0],dp[d][s][m][1][1]);
                     }
                     
                     if(l[d+1] == r[d+1] && x == l[d+1]){
                        dp[d+1][ns][nm][1][1] = add(dp[d+1][ns][nm][1][1],dp[d][s][m][1][1]);
                     }
                  }
               }
            }
            
            
         }
         
         long answer = 0L;
         for(int s = 0; s < mod; s++){
            answer = add(answer,dp[n-1][s][0][0][0]);
            answer = add(answer,dp[n-1][s][0][1][0]);
            answer = add(answer,dp[n-1][s][0][0][1]);
            answer = add(answer,dp[n-1][s][0][1][1]);
            
            //out.println(dp[n-1][s][0][0][0] + " " + dp[n-1][s][0][1][0] + " " + dp[n-1][s][0][0][1] + " " + dp[n-1][s][0][1][1]);
         }
         
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
   public static int add(int a, int b, int mod){
      int ret = a+b;
      while(ret >= mod) ret -= mod;
      return ret;
   }
   
   public static long add(long a, long b){
      long ret = a+b;
      while(ret >= MOD) ret -= MOD;
      return ret;
   }
   
   public static int sub(int a, int b, int mod){
      int ret = a-b;
      if(ret < 0) ret += mod;
      return ret;
   }
   
   public static int mul(int a, int b, int mod){
      return (a*b + mod)%mod;
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