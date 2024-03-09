//make sure to make new file!
import java.io.*;
import java.util.*;
//tutorial, solution 3
public class L3{

   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int t = fs.nextInt();
      
      for(int q = 1; q <= t; q++){
         HashSet<Integer> hset = new HashSet<Integer>();
         hset.add(0);
      
         int w = fs.nextInt();
         int h = fs.nextInt();
         int l = fs.nextInt();
         
         hset.add(w);
         hset.add((int)h);
         hset.add((int)l);
         
         int n = fs.nextInt();
         
         Point[] points = new Point[n];
         
         for(int k = 0; k < n; k++){
            int xl = fs.nextInt();
            int yl = fs.nextInt();
            int zl = fs.nextInt();
            int xr = fs.nextInt();
            int yr = fs.nextInt();
            int zr = fs.nextInt();
            
            points[k] = new Point(xl,yl,zl,xr,yr,zr);
            hset.add(xl);
            hset.add(yl);
            hset.add(zl);
            hset.add(xr);
            hset.add(yr);
            hset.add(zr);
         }
         
         ArrayList<Integer> alist = new ArrayList<Integer>();
         for(int i : hset){
            alist.add(i);
         }
         
         Collections.sort(alist);
         
         HashMap<Integer,Integer> hmap = new HashMap<Integer,Integer>();
         int an = alist.size();
         for(int k = 0; k < alist.size(); k++){
            hmap.put(alist.get(k),k);
         }
         
         for(int k = 0; k < n; k++){
            points[k].xl = hmap.get(points[k].xl);
            points[k].yl = hmap.get(points[k].yl);
            points[k].zl = hmap.get(points[k].zl);
            points[k].xr = hmap.get(points[k].xr);
            points[k].yr = hmap.get(points[k].yr);
            points[k].zr = hmap.get(points[k].zr);
         }
         
         boolean fail = false;
         int REP = 20;
         for(int rep = 0; rep < REP; rep++){
            long[] px = new long[an];
            long[] py = new long[an];
            long[] pz = new long[an];
            
            for(int k = 1; k < an; k++){
               //determine weight of gap between k and k-1
               px[k] = (long)(Math.random()*3) + 1;
               py[k] = (long)(Math.random()*3) + 1;
               pz[k] = (long)(Math.random()*3) + 1;
               
               px[k] += px[k-1];
               py[k] += py[k-1];
               pz[k] += pz[k-1];
            
            }
            
            long big = px[hmap.get(w)] * py[hmap.get(h)] * pz[hmap.get(l)];
            
            long small = 0L;
            for(int k = 0; k < n; k++){
               long x = px[points[k].xr] - px[points[k].xl];
               long y = py[points[k].yr] - py[points[k].yl];
               long z = pz[points[k].zr] - pz[points[k].zl];
               
               //out.println(x*y*z);
               small += x*y*z;
            }
            
            if(big != small){
               //out.println(big + " " + small);
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
   
   
   
   public static class Point{
      int xl;
      int yl;
      int zl;
      int xr;
      int yr;
      int zr;
      public Point(int a, int b, int c, int d, int e, int f){
         xl = a;
         yl = b;
         zl = c;
         xr = d;
         yr = e;
         zr = f;
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