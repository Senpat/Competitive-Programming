//make sure to make new file!
import java.io.*;
import java.util.*;

public class P3C{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int n = fs.nextInt();
      int q = fs.nextInt();
      
      Segtree segtree = new Segtree(n);
      
      for(int t = 0; t < q; t++){
         int qt = fs.nextInt();
         
         if(qt == 1){
            int l = fs.nextInt();
            int r = fs.nextInt()-1;
            int x = fs.nextInt();
            
            segtree.add(0,0,n-1,l,r,x);
         } else {
            int x = fs.nextInt();
            int l = fs.nextInt();
            
            out.println(segtree.query(0,0,n-1,l,x));
         }
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Segtree{
      
      private int[] ops;
      private int[] a;        //stores max on segment
      
      public Segtree(int size){
         ops = new int[4*size];
         a = new int[4*size];
      }
      
      public void propagate(int v){
         ops[2*v+1] += ops[v];
         a[2*v+1] += ops[v];
         ops[2*v+2] += ops[v];
         a[2*v+2] += ops[v];
         ops[v] = 0;
      }
      
      public void add(int v, int l, int r, int ql, int qr, int x){
         if(l >= ql && r <= qr){
            ops[v] += x;
            a[v] += x;
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            propagate(v);
            
            add(2*v+1,l,mid,ql,qr,x);
            add(2*v+2,mid+1,r,ql,qr,x);
            
            a[v] = Math.max(a[2*v+1],a[2*v+2]);
         }
      }
      
      //find minimum index j such that j >= ql and array[j] >= x
      public int query(int v, int l, int r, int ql, int x){
         if(r < ql || a[v] < x){
            return -1;
         } else if(l == r){
            return l;
         } else {
            propagate(v);
            
            int mid = (l+r)/2;
            
            int left = query(2*v+1,l,mid,ql,x);
            if(left == -1) return query(2*v+2,mid+1,r,ql,x);
            return left;
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