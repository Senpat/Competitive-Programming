//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1190{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int n = fs.nextInt();
      int q = fs.nextInt();
      
      long[] array = fs.nextLongs(n);
      
      Segtree segtree = new Segtree(n);
      segtree.build(0,0,n-1,array);
      
      for(int t = 0; t < q; t++){
         
         int i = fs.nextInt()-1;
         long x = fs.nextLong();
         
         segtree.set(0,0,n-1,i,x);
         out.println(segtree.get());
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static class Segtree{
      
      private Node[] a;
      
      public Segtree(int n){
         a = new Node[4*n];
      }
      
      private Node genNode(long x){
         if(x <= 0) 
            return new Node(0,0,0,x);
         else 
            return new Node(x,x,x,x);
      }
      
      private void combine(int v){
         Node left = a[2*v+1];
         Node right = a[2*v+2];
         
         long max = Math.max(left.max,right.max);
         max = Math.max(max,left.suff+right.pref);
         long pref = Math.max(left.pref,left.sum + right.pref);
         long suff = Math.max(right.suff,right.sum + left.suff);
         long sum = left.sum + right.sum;
         
         a[v] = new Node(max,pref,suff,sum);
      }
      
      public void build(int v, int l, int r, long[] array){
         if(l == r){
            a[v] = genNode(array[l]);
         } else {
            int mid = (l+r)/2;
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            combine(v);
         }
      }
      
      public void set(int v, int l, int r, int i, long x){
         if(l == r){
            a[v] = genNode(x);
         } else {
            int mid = (l+r)/2;
            if(i <= mid){
               set(2*v+1,l,mid,i,x);
            } else {
               set(2*v+2,mid+1,r,i,x);
            }
            
            //combine
            combine(v);
         }
         
      }
      
      public long get(){
         return a[0].max;
      }
      
   }
   
   
   
   public static class Node{
      long max;
      long pref;
      long suff;
      long sum;
      
      public Node(long a, long b, long c, long d){
         max = a;
         pref = b;
         suff = c;
         sum = d;
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