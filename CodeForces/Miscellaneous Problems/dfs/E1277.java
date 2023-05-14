//Two Fairs
import java.io.*;
import java.util.*;

public class E1277{

   public static int a,b;
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static int[] parent;
   public static boolean[] seen;
   public static boolean[] isbackedge;
   public static int[] backto;
   public static int[] backfrom;
   
   public static int[] todiff;               //for children of b, # of backedges that go from a subtree of x to b
   
   public static int[] backedges;            //# of backedges going over vertex
   public static int[] subsize;              //size of subtree not including itself
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int t = fs.nextInt();
      
      for(int q = 1; q <= t; q++){
      
         int n = fs.nextInt();
         int m = fs.nextInt();
         a = fs.nextInt();
         b = fs.nextInt();
         
         adj = new ArrayList<>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
         
         //get rid of multiedges
         HashSet<Long> hset = new HashSet<Long>();
         long N = 200005L;
         for(int k = 0; k < m; k++){
            int v1 = fs.nextInt();
            int v2 = fs.nextInt();
            
            long hash = (long)Math.min(v1,v2) * N + (long)Math.max(v1,v2);
            if(hset.contains(hash)) continue;
            hset.add(hash);
            
            adj.get(v1).add(new Edge(v2,k));
            adj.get(v2).add(new Edge(v1,k));
         }
         
         parent = new int[n+1];
         Arrays.fill(parent,-1);
         seen = new boolean[n+1];
         isbackedge = new boolean[m];
         backto = new int[n+1];
         backfrom = new int[n+1];
         todiff = new int[n+1];
         
         //root at a
         dfs(a,-1);
         
         backedges = new int[n+1];
         subsize = new int[n+1];
         
         calc(a,-1);
         
         //check if a is articulation point
         int achildren = 0;
         for(Edge e : adj.get(a)){
            if(!isbackedge[e.i]) achildren++;
         }
         
         if(achildren <= 1){
            out.println(0);
            continue;
         }
         
         /*
         if(backedges[b] > 0){
            out.println(0);
            continue;
         }*/
         
         //find the subtree of a that includes b
         int i = b;
         int sub = -1;
         while(i != a){
            if(parent[i] == a){
               sub = i;
            }
            i = parent[i];
         }
         
         long apart = (long)(subsize[a] - (subsize[sub]+1));
         long bpart = 0L;
         for(Edge e : adj.get(b)){
            if(e.to == parent[b] || isbackedge[e.i]) continue;
            if(backedges[e.to] + backfrom[e.to] - todiff[e.to] == 0){
               bpart += (long)subsize[e.to]+1;  
            }
         }
         
         out.println(apart*bpart);
         
      }
      
      
      
      
      out.close();
   }
   
   
   
   public static void calc(int v, int p){
      
      
      for(Edge e : adj.get(v)){
         if(e.to == p || isbackedge[e.i]) 
            continue;
         
         calc(e.to,v);
         
         backedges[v] += backfrom[e.to] + backedges[e.to];
         subsize[v] += subsize[e.to]+1;
         
      }
      
      backedges[v] -= backto[v];
   }
   
   //dfs tree
   public static void dfs(int v, int p){
      parent[v] = p;
      seen[v] = true;
      
      for(Edge e : adj.get(v)){
         if(e.to == p || isbackedge[e.i]) 
            continue;
         
         if(seen[e.to]){
            isbackedge[e.i] = true;
            backto[e.to]++;
            backfrom[v]++;
         } else {
            int prevto = backto[v];
            dfs(e.to,v);
            int afterto = backto[v];
            
            if(v == b) todiff[e.to] = afterto-prevto;
         }
      }
         
   }
   
   public static class Edge{
      int to;
      int i;
      public Edge(int a, int b){
         to = a;
         i = b;
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
         if (size == -1) 
            return NC;
         bId = 0;
      }
      return (char) buf[bId++];
   }
 
   public int nextInt() {
      return (int) nextLong();
   }
 
   public int[] nextInts(int N) {
      int[] res = new int[N+1];
      for (int i = 1; i <= N; i++) {
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
      if (c > 32) 
         return true;
      while (true) {
         c = getChar();
         if (c == NC) 
            return false;
         else if (c > 32) 
            return true;
      }
   }
}