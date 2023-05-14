//make sure to make new file!
import java.io.*;
import java.util.*;

public class E144{
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static TreeMap<Integer,Integer> tmap;
   public static Stack<Op> ops;
   
   public static int answer;
   public static int[] color_depth;
   
   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int t = fs.nextInt();
      
      for(int q = 1; q <= t; q++){

         int n = fs.nextInt();
      
         adj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
         
         for(int k = 0; k < n-1; k++){
            int a = fs.nextInt();
            int b = fs.nextInt();
            
            adj.get(a).add(b);
            adj.get(b).add(a);
         }
         
         tmap = new TreeMap<Integer,Integer>();
         ops = new Stack<Op>();
      
         answer = 0;
         color_depth = new int[n+1];
         
         dfs(1,-1);
         
         add(color_depth[1],0);
         answer = Math.max(answer,tmap.firstKey());
         
         reroot(1,-1);
         
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
   public static void reroot(int v, int p){
      
      //get smallest and second smallest color_depth
      //min1 is not added, min2 is
      int min1 = Integer.MAX_VALUE;
      int min2 = Integer.MAX_VALUE;
      
      for(int nei : adj.get(v)){
         if(color_depth[nei] < min1){
            min2 = min1;
            min1 = color_depth[nei];
         } else if(color_depth[nei] < min2){
            min2 = color_depth[nei];
         }
      }
      
      int prev_color_v = color_depth[v];
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         int prev_color_nei = color_depth[nei];
         
         int prestacksize = ops.size();
         //do changes for new root
         if(min2 == Integer.MAX_VALUE){
            remove(color_depth[v],1);
            color_depth[v] = 1;
            add(color_depth[nei]-1,1);
            color_depth[nei] = 2;
            add(color_depth[nei],1);
            
         } else if(color_depth[nei] == color_depth[v]-1){
            remove(color_depth[v],1);
            //min2 is now inactive
            remove(min2,1);
            color_depth[v] = min2+1;
            if(color_depth[nei] == 1){
               color_depth[nei] = color_depth[v]+1;
            } else {  
               add(color_depth[v],1);
            }
            add(color_depth[nei],1);
          
            
         } else if(color_depth[nei] > color_depth[v]+1){
            remove(color_depth[nei],1);
            
            if(color_depth[nei] > 1){
               add(color_depth[nei]-1,1);
            }
            
            color_depth[nei] = color_depth[v]+1;
            add(color_depth[nei],1);
            remove(color_depth[v],1);
         
         }     //if color_depth[nei] == color_depth[v] or color_depth[v]+1, do nothing
         answer = Math.max(answer,tmap.firstKey());
         reroot(nei,v);
         
         color_depth[nei] = prev_color_nei;
         color_depth[v] = prev_color_v;
         
         //undo operations
         while(ops.size() > prestacksize){
            Op op = ops.pop();
            if(op.type == 0) remove(op.x,0);
            else add(op.x,0);
         }
      }
      
   }
   
   public static void dfs(int v, int p){
   
      //find minimum colordepth, and the minimum doesn't get added
      int min = -1;
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfs(nei,v);
         if(min == -1 || color_depth[nei] < color_depth[min]){
            if(min != -1) add(color_depth[min],0);
            min = nei;
         } else {
            add(color_depth[nei],0);
         }
      }
      
      if(min == -1) color_depth[v] = 1;
      else color_depth[v] = color_depth[min]+1;
      
   }
   
   public static void add(int x,int save){
      if(tmap.containsKey(x)){
         tmap.put(x,tmap.get(x)+1);
      } else {
         tmap.put(x,1);
      }
      
      if(save == 1) ops.add(new Op(0,x));
   }
   
   public static void remove(int x,int save){
      if(tmap.get(x) == 1){
         tmap.remove(x);
      } else {
         tmap.put(x,tmap.get(x)-1);
      }
         
      if(save == 1) ops.add(new Op(1,x));
   }
   
   public static class Op{
      int type;      //0 is add, 1 is remove
      int x;
      public Op(int a, int b){
         type = a;
         x = b;
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
        if (c > 32) return true;
        while (true) {
            c = getChar();
            if (c == NC) return false;
            else if (c > 32) return true;
        }
    }
}