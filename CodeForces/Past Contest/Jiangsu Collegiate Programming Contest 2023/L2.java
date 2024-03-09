//make sure to make new file!
import java.io.*;
import java.util.*;
//tutorial, solution 2
public class L2{
   
   public static HashMap<Long,Integer> hmap;
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int t = fs.nextInt();
      
      for(int q = 1; q <= t; q++){
         long w = fs.nextLong();
         long h = fs.nextLong();
         long l = fs.nextLong();
         
         int n = fs.nextInt();
         
         hmap = new HashMap<Long,Integer>();
         
         setvals(0,0,0,w,h,l,-1);
         
         for(int k = 0; k < n; k++){
            long xl = fs.nextLong();
            long yl = fs.nextLong();
            long zl = fs.nextLong();
            long xr = fs.nextLong();
            long yr = fs.nextLong();
            long zr = fs.nextLong();
            
            setvals(xl,yl,zl,xr,yr,zr,1);
         }
         
         boolean fail = false;
         for(long p : hmap.keySet()){
            //out.println(p + " " + hmap.get(p));
            if(hmap.get(p) != 0){
               fail = true;
               break;
            }
         }
         
         if(fail){
            out.println("No");
         } else {
            out.println("Yes");
         }
      }
      
      
      
      
      out.close();
   }
   
   public static void setvals(long xl, long yl, long zl, long xr, long yr, long zr, int x){
      add(hash(xl,yl,zl),x);
      add(hash(xl,yl,zr),-x);
      add(hash(xl,yr,zl),-x);
      add(hash(xr,yl,zl),-x);
      add(hash(xr,yr,zl),x);
      add(hash(xr,yl,zr),x);
      add(hash(xl,yr,zr),x);
      add(hash(xr,yr,zr),-x);
   }
   
   public static void add(long p, int x){
      if(hmap.containsKey(p)){
         hmap.put(p,hmap.get(p)+x);
      } else {
         hmap.put(p,x);
      }
   }
   
   public static long hash(long x, long y, long z){
      long hash = (x << 30) + (y << 15) + z;
      return hash;
   }
   
   /*
   public static class Point{
      int x;
      int y;
      int z;
      public Point(int a, int b, int c){
         x = a;
         y = b;
         z = c;
      }
      
      public int hashCode(){
         int hash = x^xx + y^xy;
         while(hash >= mod) hash -= mod;
         hash += z^xz;
         if(hash >= mod) hash -= mod;
         return hash;
      }
      
      public boolean equals(Object o){
         Point p = (Point)o;
         return x == p.x && y == p.y && z == p.z;
      }
      
      public String toString(){
         return x + " " + y + " " + z;
      }
   }*/
      
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