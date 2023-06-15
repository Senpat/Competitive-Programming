//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1143{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int n = fs.nextInt();
      int m = fs.nextInt();
      
      int[] array = fs.nextInts(n);
      
      Segtree segtree = new Segtree(n);
      segtree.build(0,0,n-1,array);
      
      int[] rooms = fs.nextInts(m);
      StringJoiner sj = new StringJoiner(" ");
      for(int t = 0; t < m; t++){
         int i = segtree.getfirst(0,0,n-1,rooms[t]);
         if(i == -1){
            sj.add("0");
         } else {
            sj.add("" + (i+1));
            segtree.update(0,0,n-1,i,-rooms[t]);
         }
      }
      
      out.println(sj.toString());
      
      
      
      
      out.close();
   }
   
   public static class Segtree{
   
      private int[] a;     //stores max in segment
      
      public Segtree(int size){
         a = new int[4*size];
      }
      
      public void build(int v, int l, int r, int[] array){
         if(l == r){
            a[v] = array[l];
         } else {
            int mid = (l+r)/2;
            
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            a[v] = Math.max(a[2*v+1],a[2*v+2]);
         }
      }
      
      public void update(int v, int l, int r, int i, int x){
         if(l == r){
            a[v] += x;
         } else {
            int mid = (l+r)/2;
            
            if(i <= mid){
               update(2*v+1,l,mid,i,x);
            } else {
               update(2*v+2,mid+1,r,i,x);
            }
            
            a[v] = Math.max(a[2*v+1],a[2*v+2]);
         }
      }
      
      //returns index of earliest index >= x
      public int getfirst(int v, int l, int r, int x){
         if(a[v] < x){
            return -1;
         }
         if(l == r){
            return l;
         } else {
            int mid = (l+r)/2;
            
            if(a[2*v+1] >= x){
               return getfirst(2*v+1,l,mid,x);
            } else {
               return getfirst(2*v+2,mid+1,r,x);
            }
         }
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