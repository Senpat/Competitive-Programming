//make sure to make new file!
import java.io.*;
import java.util.*;
//similar to CF EDU Segment Tree 2, problem 4A
public class P1735{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int n = fs.nextInt();
      int q = fs.nextInt();
      
      long[] array = fs.nextLongs(n);
      
      Segtree segtree = new Segtree(n);
      segtree.build(0,0,n-1,array);
      for(int t = 0; t < q; t++){
         int type = fs.nextInt();
         int l = fs.nextInt()-1;
         int r = fs.nextInt()-1;
         
         if(type == 1 || type == 2){
            long x = fs.nextLong();
            
            segtree.update(0,0,n-1,l,r,new Op(3-type,x));
         } else {
            out.println(segtree.sum(0,0,n-1,l,r));
         }
      }
         
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Segtree{
      
      private Op[] ops;
      private long[] a;       //stores sum on segment
      
      public Segtree(int size){
         ops = new Op[4*size];
         a = new long[4*size];  
      
         Arrays.fill(ops,new Op());
      }
      
      public void build(int v, int l, int r, long[] array){
         if(l == r){
            ops[v] = new Op(1,array[l]);
            a[v] = array[l];
         } else {
            int mid = (l+r)/2;
            
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            a[v] = a[2*v+1] + a[2*v+2];
         }
      }
      
      public Op combineOp(Op prev, Op op){
         if(op.type == 1){
            return op;
         } else {
            return new Op(prev.type,prev.value + op.value);
         }
      }
      
      public void applyOp(int v, Op op, long len){
         
         if(op.type == 1){
            a[v] = len*op.value;
         } else {
            a[v] += len*op.value;
         }
         
         ops[v] = combineOp(ops[v],op);
      }
      
      public void propagate(int v, int l, int r){
         int mid = (l+r)/2;
         long lenl = (long)(mid-l+1);
         long lenr = (long)(r-(mid+1)+1);
         
         applyOp(2*v+1,ops[v],lenl);
         applyOp(2*v+2,ops[v],lenr);
         ops[v] = new Op();
      }
      
      public void update(int v, int l, int r, int ql, int qr, Op op){
         if(l >= ql && r <= qr){
            long len = (long)(r-l+1);
            applyOp(v,op,len);
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            propagate(v,l,r);
            
            update(2*v+1,l,mid,ql,qr,op);
            update(2*v+2,mid+1,r,ql,qr,op);
            
            a[v] = a[2*v+1] + a[2*v+2];
         }
            
      }
      
      public long sum(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return 0L;
         } else {
            int mid = (l+r)/2;
            
            propagate(v,l,r);
            
            return sum(2*v+1,l,mid,ql,qr) + sum(2*v+2,mid+1,r,ql,qr);
         }
      }
      
   }
   
   public static class Op{
      int type;         //1 for assign, 2 for add
      long value;
      
      //default - add 0 is the same as doing nothing
      public Op(){
         this(2,0L);
      }
      
      public Op(int a, long b){
         type = a;
         value = b;
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