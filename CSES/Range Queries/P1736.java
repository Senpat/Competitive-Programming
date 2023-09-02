//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1736{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int n = fs.nextInt();
      int q = fs.nextInt();
      
      long[] array = fs.nextLongs(n);
      
      Segtree segtree = new Segtree(n);
      segtree.build(0,0,n-1,array);
      
      for(int t = 0; t < q; t++){
         int qt = fs.nextInt();
         int l = fs.nextInt()-1;
         int r = fs.nextInt()-1;
         
         if(qt == 1){
            segtree.update(0,0,n-1,l,r,1,0);
         } else {
            out.println(segtree.sum(0,0,n-1,l,r));
         }
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static class Segtree{
   
      private Node[] a;
      
      public Segtree(int size){
         a = new Node[4*size];
         for(int k = 0; k < 4*size; k++){
            a[k] = new Node(0L,0L,0L);
         }
      }
      
      public void build(int v, int l, int r, long[] array){
         if(l == r){
            a[v].sum = array[l];
         } else {
            int mid = (l+r)/2;
            
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            a[v].sum = a[2*v+1].sum + a[2*v+2].sum;
         }
      }
      
      private long getsum(long up, long flat, long len){
         return flat*len + (up+up*len)*len/2L;
      }
      
      private void propagate(int v, int l, int r){
         if(a[v].up == 0L && a[v].flat == 0L) return;
         
         int mid = (l+r)/2;
         a[2*v+1].up += a[v].up;
         a[2*v+1].flat += a[v].flat;
         a[2*v+1].sum += getsum(a[v].up,a[v].flat,(long)(mid-l+1));
         
         a[2*v+2].up += a[v].up;
         long rightflat = a[v].flat + (long)(mid-l+1) * a[v].up;
         a[2*v+2].flat += rightflat;
         a[2*v+2].sum += getsum(a[v].up,rightflat,(long)(r-(mid+1)+1));
         
         a[v].up = 0L;
         a[v].flat = 0L;
      }
      
      //first number of up+flat, diff between numbers is up
      public void update(int v, int l, int r, int ql, int qr, long up, long flat){
         if(l >= ql && r <= qr){
            a[v].up += up;
            a[v].flat += flat;
            long len = (long)(r-l+1);
            a[v].sum += getsum(up,flat,len);
         } else if(r < ql || l > qr){
            return;
         } else {
            propagate(v,l,r);
            
            int mid = (l+r)/2;
            
            update(2*v+1,l,mid,ql,qr,up,flat);
            long rightflat = flat + Math.max(0,(long)(mid-Math.max(l,ql)+1) * up);
            update(2*v+2,mid+1,r,ql,qr,up,rightflat);
            
            a[v].sum = a[2*v+1].sum + a[2*v+2].sum;
         }
      }
      
      public long sum(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v].sum;
         } else if(r < ql || l > qr){
            return 0L;
         } else {
            propagate(v,l,r);
            
            int mid = (l+r)/2;
            
            return sum(2*v+1,l,mid,ql,qr) + sum(2*v+2,mid+1,r,ql,qr);
         }
      }
   
   }
   
   //up and flat are operations and are propagated
   public static class Node{
      long up;
      long flat;
      long sum;
      
      public Node(long a, long b, long c){
         up = a;
         flat = b;
         sum = c;
      }
      public String toString(){
         return up + " " + flat + " " + sum;
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