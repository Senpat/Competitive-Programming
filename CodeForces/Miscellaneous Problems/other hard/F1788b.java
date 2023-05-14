//XOR, Tree, and Queries
import java.io.*;
import java.util.*;
//hint
//xor of path from a to b is (xor from root to a) xor (xor from root to b)
//based on F1788, but does all bits at once
public class F1788b{
   
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static int[] parent;
   
   public static int[] answer;
   
   public static ArrayList<ArrayList<Edge>> curadj;
   public static int[] curval;
   public static boolean fail;
   public static HashSet<Integer> hset;
   public static int curflipsize;
   
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
      
      curadj = new ArrayList<ArrayList<Edge>>(n+1);
      for(int k = 0; k <= n; k++) curadj.add(new ArrayList<Edge>());
      
      for(int k = 0; k < q; k++){
         int a = fs.nextInt();
         int b = fs.nextInt();
         int x = fs.nextInt();
         
         curadj.get(a).add(new Edge(b,x));
         curadj.get(b).add(new Edge(a,x));
      }
      
      parent = new int[n+1];
      parent[1] = -1;
      dfs(1,-1);
      
      
      //for every bit
      answer = new int[n-1];
      curval = new int[n+1];
      
      fail = false;
      
         //check
      Arrays.fill(curval,-1);
         
      HashSet<Integer> oddsize = new HashSet<Integer>();
      for(int k = 1; k <= n; k++){
         if(curval[k] == -1){
            curval[k] = 0;
            curflipsize = 0;
            hset = new HashSet<Integer>();
            fill(k);
            if(k != 1 && curflipsize % 2 == 1 && oddsize.size() == 0){
               oddsize = hset;
            }
         }
            
         if(fail) 
            break;
      }
         
      if(fail){
         out.println("No");
         out.close();
         return;
      }
      
      populate(1,-1);
         
      if(oddsize.size() != 0){
            //see if you can flip the xor
         int xor = 0;
         for(int k = 0; k < n-1; k++){
            xor ^= answer[k];
         }
         if(xor != 0){
            for(int x : oddsize){
               for(Edge e : adj.get(x)){
                  answer[e.index] ^= xor;
               }
            }
         }
      }
      
      
      out.println("Yes");
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n-1; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      out.close();
   }
   
   public static void populate(int v, int p){
      
      for(Edge e : adj.get(v)){
         if(e.to == p) 
            continue;
         answer[e.index] = curval[v] ^ curval[e.to];
         populate(e.to,v);  
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
         if(e.to == p) 
            continue;
         dfs(e.to,v);
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