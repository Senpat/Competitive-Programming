//make sure to make new file!
import java.io.*;
import java.util.*;
//tle
public class D19{
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int q = fs.nextInt();
      
      Query[] queries = new Query[q];
      HashSet<Integer> hset = new HashSet<Integer>();
      for(int k = 0; k < q; k++){
         String s = fs.next();
         int x = fs.nextInt();
         int y = fs.nextInt();
         
         queries[k] = new Query(s,x,y);
         
         hset.add(y);
      
      }
      
      ArrayList<Integer> alist = new ArrayList<Integer>();
      for(int i : hset){
         alist.add(i);
      }
      
      Collections.sort(alist);
      
      HashMap<Integer,Integer> compress = new HashMap<Integer,Integer>();
      for(int k = 0; k < alist.size(); k++){
         compress.put(alist.get(k),k);
      }
      
      int N = 200005;
      Segtree segtree = new Segtree(N);
      
      for(int t = 0; t < q; t++){
         int x = queries[t].x;
         int y = queries[t].y;
         if(queries[t].s.equals("add")){
            segtree.update(0,0,N-1,new Point(x,compress.get(y)),true);
         } else if(queries[t].s.equals("remove")){
            segtree.update(0,0,N-1,new Point(x,compress.get(y)),false);
         } else {
            Point p = segtree.find(0,0,N-1,compress.get(y)+1,N-1,x);  
            if(p == null) out.println(-1);
            else out.println(p.x + " " + alist.get(p.y));
         }
      }
      
      
      
      
      out.close();
   }
   
   public static class Segtree{
   
      private ArrayList<TreeSet<Point>> a;
      
      public Segtree(int size){
         a = new ArrayList<>(4*size);
         for(int k = 0; k < 4*size; k++){
            a.add(new TreeSet<Point>());
         }
      }
      
      public void update(int v, int l, int r, Point p, boolean add){
         if(add){
            a.get(v).add(p);
         } else {
            a.get(v).remove(p);
         }
         
         if(l != r){
            int mid = (l+r)/2;
            
            if(p.y <= mid){
               update(2*v+1,l,mid,p,add);
            } else {
               update(2*v+2,mid+1,r,p,add);
            }
            
         }
      }
      
      //returns the Point that is leftmost and bottommost after x, and y in the range [ql,qr]
      public Point find(int v, int l, int r, int ql, int qr, int x){
         if(l >= ql && r <= qr){
            //next point after Point(x,inf)
            Point p = a.get(v).higher(new Point(x,Integer.MAX_VALUE));
            return p;
         } else if(r < ql || l > qr){
            return null;
         } else {
            int mid = (l+r)/2;
            
            Point pl = find(2*v+1,l,mid,ql,qr,x);
            Point pr = find(2*v+2,mid+1,r,ql,qr,x);
            
            //if one is null, return the other
            if(pl == null) return pr;
            if(pr == null) return pl;
            
            //both are not null, prioritize pl
            if(pl.x <= pr.x) return pl;
            return pr;
         }
      }
   
   }
   
   
   public static class Point implements Comparable<Point>{
      int x;
      int y;
      public Point(int a, int b){
         x = a;
         y = b;
      }
      
      //sort by x, then by y
      public int compareTo(Point p){
         if(x == p.x) return y-p.y;
         return x-p.x;
      }
   }
   
   public static class Query{
      String s;
      int x;
      int y;
      
      public Query(String a, int b, int c){
         s = a;
         x = b;
         y = c;
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