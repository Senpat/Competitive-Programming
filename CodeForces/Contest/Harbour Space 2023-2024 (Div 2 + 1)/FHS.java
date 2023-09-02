//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, danny
public class FHS{
   
   public static int n;
   public static int[] bits;
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      n = fs.nextInt();
      int q = fs.nextInt();
      
      int[] array = fs.nextInts(n);
      ArrayList<ArrayList<Integer>> indeces = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++){
         indeces.add(new ArrayList<Integer>());
      }
      for(int k = 0; k < n; k++){
         indeces.get(array[k]).add(k);
      }
      
      
      Query[] queries = new Query[q];
      for(int k = 0; k < q; k++){
         int l = fs.nextInt();
         int r = fs.nextInt();
         
         queries[k] = new Query(l,r,k);
      }
      
      Arrays.sort(queries);
      
      //generate updates
      ArrayList<ArrayList<Integer>> updates = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++){
         updates.add(new ArrayList<Integer>());
      }
      
      Segtree segtree = new Segtree(n);
      for(int k = 1; k <= n; k++){
         for(int i = 0; i+1 < indeces.get(k).size(); i++){
            int query = segtree.max(0,0,n-1,indeces.get(k).get(i),indeces.get(k).get(i+1));
            if(query != 0){
               updates.get(query).add(k);
            }
         }
         
         for(int i : indeces.get(k)){
            segtree.set(0,0,n-1,i,k);
         }
         
         if(indeces.get(k).size() > 0){
            updates.get(k).add(k);
         }
      }
      
      bits = new int[n+1];
      
      int[] answer = new int[q];
      int qi = q-1;
      for(int k = n; k >= 1; k--){
         for(int update : updates.get(k)){
            update(update,1);
         }
         
         while(qi >= 0 && queries[qi].l >= k){
            answer[queries[qi].index] = psum(queries[qi].r)-psum(queries[qi].l-1);
            qi--;
         }
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < q; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      out.close();
   }
   
   public static class Query implements Comparable<Query>{
      int l;
      int r;
      int index;
      public Query(int a, int b, int c){
         l = a;
         r = b;
         index = c;
      }
      //sort by l
      public int compareTo(Query q){
         return l-q.l;
      }
   }
   
   
   
   //point update, range max query
   //to call, v = 0, l = 0, r = size-1
   //l,r and ql,qr are inclusive
   public static class Segtree{
      
      private int[] a;
      
      public Segtree(int size){
         a = new int[4*size];
      }
      
      public void set(int v, int l, int r, int i, int x){
         if(l == r){
            a[v] = x;
         } else {
            int mid = (l+r)/2;
            if(i <= mid){
               //go left
               set(2*v+1,l,mid,i,x);
            } else {
               //go right
               set(2*v+2,mid+1,r,i,x);
            }
            
            a[v] = Math.max(a[2*v+1],a[2*v+2]);
         }
      }
      
      public int max(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return 0;
         } else {
            int mid = (l+r)/2;
            //left
            int lmax = max(2*v+1,l,mid,ql,qr);
            //right
            int rmax = max(2*v+2,mid+1,r,ql,qr);
            return Math.max(lmax,rmax);
         }
      }
      
   }
   
   
   public static void update(int i, int x){
      for(; i <= n; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static int psum(int i){
      int sum = 0;
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