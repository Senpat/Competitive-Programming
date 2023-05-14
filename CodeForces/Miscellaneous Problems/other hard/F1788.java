//XOR, Tree, and Queries
import java.io.*;
import java.util.*;
//hint
//xor of path from a to b is (xor from root to a) xor (xor from root to b)
public class F1788{
   
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static int[] parent;
   public static long[] subsize;
   
   public static int[] answer;
   
   public static ArrayList<ArrayList<Edge>> curadj;
   public static int[] curval;
   public static boolean fail;
   public static HashSet<Integer> hset;
   public static long curflipsize;
   public static int[] curanswer;
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int n = fs.nextInt();
      int q = fs.nextInt();
      
      adj = new ArrayList<ArrayList<Edge>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      for(int k = 0; k < n-1; k++){
         int a = fs.nextInt();
         int b = fs.nextInt();
         
         adj.get(a).add(new Edge(b,k));
         adj.get(b).add(new Edge(a,k));
      }
      
      Res[] res = new Res[q];
      for(int k = 0; k < q; k++){
         int a = fs.nextInt();
         int b = fs.nextInt();
         int x = fs.nextInt();
         
         res[k] = new Res(a,b,x);
      }
      
      parent = new int[n+1];
      subsize = new long[n+1];
      parent[1] = -1;
      dfs(1,-1);
      
      
      //for every bit
      answer = new int[n-1];
      curval = new int[n+1];
      
      fail = false;
      for(int i = 0; i < 30; i++){
         
         curadj = new ArrayList<ArrayList<Edge>>(n+1);
         for(int k = 0; k <= n; k++) curadj.add(new ArrayList<Edge>());
         
         for(Res r : res){
            int type = (r.x >> i) & 1;
            curadj.get(r.a).add(new Edge(r.b,type));
            curadj.get(r.b).add(new Edge(r.a,type));
         }
         
         //check
         Arrays.fill(curval,-1);
         
         HashSet<Integer> oddsize = new HashSet<Integer>();
         for(int k = 1; k <= n; k++){
            if(curval[k] == -1){
               curval[k] = 0;
               curflipsize = 0L;
               hset = new HashSet<Integer>();
               fill(k);
               if(k != 1 && curflipsize % 2 == 1 && oddsize.size() == 0){
                  oddsize = hset;
               }
            }
            
            if(fail) break;
         }
         
         if(fail) break;
         
         curanswer = new int[n-1];
         populate(1,-1,0);
         
         if(oddsize.size() != 0){
            //see if you can flip the xor
            int xor = 0;
            for(int k = 0; k < n-1; k++){
               xor ^= curanswer[k];
            }
            if(xor == 1){
               for(int x : oddsize){
                  for(Edge e : adj.get(x)){
                     curanswer[e.index] ^= 1;
                  }
               }
            }
         }
         
         for(int k = 0; k < n-1; k++){
            answer[k] |= (curanswer[k] << i);
         }
         
      }
      
      if(fail){
         out.println("No");
      } else {
         out.println("Yes");
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n-1; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
      }
      
      
      
      
      
      out.close();
   }
   
   public static void populate(int v, int p, int xor){
      
      for(Edge e : adj.get(v)){
         if(e.to == p) continue;
         curanswer[e.index] = xor ^ curval[e.to];
         populate(e.to,v,curval[e.to]);  
      }
   }
   
   public static void fill(int v){
      hset.add(v);
      curflipsize += adj.get(v).size();
      
      for(Edge e : curadj.get(v)){
         if(curval[e.to] == -1){
            curval[e.to] = curval[v] ^ e.type;
            fill(e.to);
         } else {
            if(curval[e.to] != (curval[v] ^ e.type)){
               fail = true;
               return;
            }
         }
      }
   }
   
   public static void dfs(int v, int p){
      
      for(Edge e : adj.get(v)){
         if(e.to == p) continue;
         dfs(e.to,v);
         subsize[v] += 1L+subsize[e.to];
         parent[e.to] = e.index;
      }
   }
   
   public static class Edge{
      int to;
      int type;
      int index;
      public Edge(int a, int b){
         to = a;
         type = b;
         index = b;
      }
   }
   
   //Restriction
   public static class Res{
      int a;
      int b;
      int x;
      public Res(int c, int d, int e){
         a = c;
         b = d;
         x = e;
      }
   }
   
      
}

class FastScanner {
   private int BS = 1<<16;
   private char NC = (char) 0;
   private byte[] buf = new byte[BS];
   private int bId = 0, size = 0;
   private char c = NC;
   private double cnt = 1;
   private BufferedInputStream in;
   
   public FastScanner() {
      in = new BufferedInputStream(System.in,BS);
   }
   
   private char getChar(){
      while (bId == size){
         try {
            size = in.read(buf);
         } catch (Exception e){
            return NC;
         }
         if(size == -1){
            return NC;
         }
         
         bId = 0;
      }
      
      return (char) buf[bId++];
   }
   
   public int nextInt() {
      return (int) nextLong();
   }
   
   public long nextLong() {
      cnt = 1;
      boolean neg = false;
      if(c == NC) c = getChar();
      for(; (c < '0' || c > '9'); c = getChar()){
         if (c == '-') neg = true;
      }
      
      long res = 0;
      for(; (c >= '0' && c <= '9'); c = getChar()){
         res = (res << 3) + (res << 1) + c - '0';
         cnt *= 10;
      }
      
      return neg ? -res : res;
   }
   
}