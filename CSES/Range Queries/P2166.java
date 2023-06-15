//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2166{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int n = fs.nextInt();
      int q = fs.nextInt();
      
      long[] array = fs.nextLongs(n);
      long[] psum = new long[n];
      psum[0] = array[0];
      for(int k = 1; k < n; k++){
         psum[k] = psum[k-1] + array[k];
      }
      Segtree segtree = new Segtree(n);
      segtree.build(0,0,n-1,psum);
      
      for(int t = 0; t < q; t++){
         int qt = fs.nextInt();
         if(qt == 1){
            int index = fs.nextInt()-1;
            long x = fs.nextLong();
            long diff = x-array[index];
            segtree.add(0,0,n-1,index,n-1,diff);
            array[index] = x;
         } else {
            int l = fs.nextInt()-1;
            int r = fs.nextInt()-1;
            
            long max = segtree.max(0,0,n-1,l,r);
            if(l > 0) max -= segtree.max(0,0,n-1,l-1,l-1);
            out.println(Math.max(0,max));
         }
      }
      
      
      
      
      
      out.close();
   }
   
   //range update, max query
   public static class Segtree{
      
      private long[] ops;           //stores add operations
      private long[] a;             //stores max segtree,
                                    //incorporates operations from below or same level but not above
      
      public Segtree(int size){
         ops = new long[4*size];
         a = new long[4*size];
      }
      
      public void build(int v, int l, int r, long[] array){
         if(l == r){
            a[v] = array[l];
         } else {
            int mid = (l+r)/2;
            
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            a[v] = Math.max(a[2*v+1],a[2*v+2]);
         }
      }
      
      public void add(int v, int l, int r, int ql, int qr, long x){
         if(l >= ql && r <= qr){
            ops[v] += x;
            a[v] += x;
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            add(2*v+1,l,mid,ql,qr,x);
            add(2*v+2,mid+1,r,ql,qr,x);
            
            a[v] = ops[v] + Math.max(a[2*v+1],a[2*v+2]);
         }
      }
      
      public long max(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return Long.MIN_VALUE;
         } else {
            int mid = (l+r)/2;
            
            return ops[v] + Math.max(max(2*v+1,l,mid,ql,qr),max(2*v+2,mid+1,r,ql,qr));
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