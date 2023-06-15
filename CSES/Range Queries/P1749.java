//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1749{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int n = fs.nextInt();
      
      int[] array = fs.nextInts(n);
      int[] queries = fs.nextInts(n);
      
      Segtree segtree = new Segtree(n);
      segtree.fill(0,0,n-1);
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n; k++){
         int answer = segtree.xth(0,0,n-1,queries[k]);
         sj.add("" + array[answer]);
         
         segtree.set0(0,0,n-1,answer);
      }
      
      out.println(sj.toString());
      
      
      
      
      
      
      out.close();
   }
   
   public static class Segtree{
   
      //stores sum on segment (aka # of 1s)
      private int[] a;
      
      public Segtree(int size){
         a = new int[4*size];
      }
      
      public void fill(int v, int l, int r){
         if(l == r){
            a[v] = 1;
         } else {
            int mid = (l+r)/2;
            
            fill(2*v+1,l,mid);
            fill(2*v+2,mid+1,r);
            
            a[v] = a[2*v+1] + a[2*v+2];
         }
      }
      
      public void set0(int v, int l, int r, int i){
         if(l == r){
            a[v] = 0;
         } else {
            int mid = (l+r)/2;
            
            if(i <= mid){
               set0(2*v+1,l,mid,i);
            } else {
               set0(2*v+2,mid+1,r,i);
            }
            
            a[v] = a[2*v+1] + a[2*v+2];
         }
      }
      
      //returns index of xth 1
      public int xth(int v, int l, int r, int x){
         if(l == r){
            return l;
         } else {
            int mid = (l+r)/2;
            
            if(a[2*v+1] >= x){
               return xth(2*v+1,l,mid,x);
            } else {
               return xth(2*v+2,mid+1,r,x-a[2*v+1]);
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