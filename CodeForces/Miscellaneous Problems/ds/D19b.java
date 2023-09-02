//make sure to make new file!
import java.io.*;
import java.util.*;
//danny's code, improved to O(NlogN)
public class D19b{
   
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
         
         hset.add(x);
      
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
      ArrayList<TreeSet<Integer>> tsets = new ArrayList<>(N);
      for(int k = 0; k < N; k++){
         tsets.add(new TreeSet<Integer>());
      }
      
      Segtree segtree = new Segtree(N);
      StringJoiner sj = new StringJoiner("\n");
      for(int t = 0; t < q; t++){
         int x = compress.get(queries[t].x);
         int y = queries[t].y;
         if(queries[t].s.charAt(0) == 'a'){
            tsets.get(x).add(y);
            segtree.assign(0,0,N-1,x,tsets.get(x).last());
         } else if(queries[t].s.charAt(0) == 'r'){
            tsets.get(x).remove(y);
            int curq = 0;
            if(tsets.get(x).size() > 0) curq = tsets.get(x).last();
            segtree.assign(0,0,N-1,x,curq);
         } else {
            int i = segtree.query(0,0,N-1,x,y);
            if(i == -1){
               sj.add("-1");
            } else {
               int qy = tsets.get(i).higher(y);
               sj.add(alist.get(i) + " " + qy);
            }
         }
      }
      
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Segtree{
   
      private int[] a;        //stores max on interval
      
      public Segtree(int size){
         a = new int[4*size];
      }
      
      public void assign(int v, int l, int r, int i, int x){
         if(l == r){
            a[v] = x;
         } else {
            int mid = (l+r)/2;
            
            if(i <= mid){
               assign(2*v+1,l,mid,i,x);
            } else {
               assign(2*v+2,mid+1,r,i,x);
            }
            
            a[v] = Math.max(a[2*v+1],a[2*v+2]);
         }
      }
      
      //returns leftmost index > i whose element is > x
      public int query(int v, int l, int r, int i, int x){
         if(r <= i || a[v] <= x) return -1;
         else if(l == r){
            if(a[v] > x) return l;
            else return -1;
         } else {
            int mid = (l+r)/2;
            
            int left = query(2*v+1,l,mid,i,x);
            if(left != -1) return left;
            
            return query(2*v+2,mid+1,r,i,x);
         }
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