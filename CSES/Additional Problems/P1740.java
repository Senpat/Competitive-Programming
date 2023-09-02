//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1740{

   public static int N = 2000001;
   public static long[] bits;
   public static int O = 1000001;
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int n = fs.nextInt();
      
      ArrayList<ArrayList<Integer>> inc = new ArrayList<ArrayList<Integer>>(N+1);
      ArrayList<ArrayList<Integer>> dec = new ArrayList<ArrayList<Integer>>(N+1);
      for(int k = 0; k <= N; k++){
         inc.add(new ArrayList<Integer>());
         dec.add(new ArrayList<Integer>());
      }
      
      ArrayList<Vert> verts = new ArrayList<Vert>();
      
      for(int k = 0; k < n; k++){
         int x1 = fs.nextInt()+O;
         int y1 = fs.nextInt()+O;
         int x2 = fs.nextInt()+O;
         int y2 = fs.nextInt()+O;
         
         if(x1 == x2 && y1 == y2) continue;
         if(x1 == x2){
            verts.add(new Vert(x1,y1,y2));
         } else if(y1 == y2){
            int xmin = Math.min(x1,x2);
            int xmax = Math.max(x1,x2);
            
            inc.get(xmin).add(y1);
            dec.get(xmax).add(y1);
         }  
      }
      
      Collections.sort(verts);
      
      bits = new long[N];
      
      long answer = 0L;
      int vi = 0;
      for(int x = 1; x <= N; x++){
         while(vi < verts.size() && verts.get(vi).x == x){
            answer += psum(verts.get(vi).y2) - psum(verts.get(vi).y1-1);
            vi++;
         }
         
         for(int i : inc.get(x)){
            update(i,1L);
         }
         for(int i : dec.get(x)){
            update(i,-1L);
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Vert implements Comparable<Vert>{
      int x;
      int y1;
      int y2;
      
      public Vert(int a, int b, int c){
         x = a;
         y1 = Math.min(b,c);
         y2 = Math.max(b,c);
      }
      
      public int compareTo(Vert v){
         return x-v.x;
      }
   }
      
   public static void update(int i, long x){
      for(; i <= N; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static long psum(int i){
      long sum = 0L;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   
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