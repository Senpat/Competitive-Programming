//make sure to make new file!
import java.io.*;
import java.util.*;
//MAJOR BUG: calculate depth using children without recursing, so all depths were 0 or 1.
/*
find a cycle and make sure each node in the cycle has the same tree(s)
6 6
1 2
2 3
3 1
4 1
5 2
6 3
close: from tutorial: trees can alternate on the root
try different way of getting cycle https://zhuanlan.zhihu.com/p/592934588
*/
public class Gc{
   
   public static long MOD = 1000000007L;
   public static long[] rand;
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static boolean[] iscycle;
   public static int[] depth;
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 100005;
      rand = new long[N];
      //rand[0] = 1L;
      for(int k = 0; k < N; k++){
         rand[k] = (long)(Math.random() * 1000000000L) + 5L;
      }
      
      
      int t = fs.nextInt();
      
      for(int qq = 1; qq <= t; qq++){
         int n = fs.nextInt();
         int m = fs.nextInt();
         
         adj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
         
         int[] deg = new int[n+1];
         for(int k = 0; k < m; k++){
            int a = fs.nextInt();
            int b = fs.nextInt();
            
            adj.get(a).add(b);
            adj.get(b).add(a);
            
            deg[a]++;
            deg[b]++;
         }
         
         if(m == n-1){
            out.println("YES");
         } else if(m == n){
         
            //find the cycle
            Queue<Integer> q = new LinkedList<Integer>();
            boolean[] seen = new boolean[n+1];
            
            for(int k = 1; k <= n; k++){
               if(deg[k] == 1){
                  seen[k] = true;
                  q.add(k);
               }
            }
            
            while(!q.isEmpty()){
               int v = q.poll();
               
               for(int nei : adj.get(v)){
                  if(seen[nei]) continue;
                  deg[nei]--;
                  if(deg[nei] == 1){
                     seen[nei] = true;
                     q.add(nei);
                  }
               }
            }
            
            iscycle = new boolean[n+1];
            int cur = -1;
            for(int k = 1; k <= n; k++){
               if(!seen[k]){
                  iscycle[k] = true;
                  cur = k;
               }
            }
            
            ArrayList<Integer> cycle = new ArrayList<Integer>();
            cycle.add(cur);
            
            int next = -1;
            for(int nei : adj.get(cur)){
               if(iscycle[nei]){
                  next = nei;
                  break;
               }
            }
            
            //go around the cycle
            int prev = cur;
            int i = next;
            while(i != cur){
               cycle.add(i);
               for(int nei : adj.get(i)){
                  if(nei != prev && iscycle[nei]){
                     prev = i;
                     i = nei;
                     break;
                  }
               }
            }
                  
            
            ArrayList<Long> hashes = new ArrayList<Long>();
            depth = new int[n+1];
            
            for(int root : cycle){
               //out.println(root);
               getdepth(root,-1);
               hashes.add(gethash(root,-1));
            }
            
            //check that all hashes are the same or are alternating
            boolean fail = false;
            for(int k = 0; k < hashes.size(); k++){
               prev = k-2;
               if(prev < 0) prev += hashes.size();
               
               if((long)hashes.get(k) != (long)hashes.get(prev)){
                  fail = true;
                  break;
               }
            }
            
            if(fail){
               out.println("NO");
            } else {
               out.println("YES");
            }
            
         } else {
            out.println("NO");
         }

      }
      
      
      
      
      out.close();
   }
   
   public static void getdepth(int v, int p){
      depth[v] = 0;
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         if(iscycle[nei]) continue;
         getdepth(nei,v);
         depth[v] = Math.max(depth[v],depth[nei]+1);
      }
   }
   
   public static long gethash(int v, int p){
      long hash = 1L;
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         if(iscycle[nei]) continue;
         long neihash = gethash(nei,v);
         neihash = neihash + rand[depth[v]];
         if(neihash >= MOD) neihash -= MOD;
         hash = (hash * neihash + MOD)%MOD;
      }
      
      return hash;
      
      
      
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