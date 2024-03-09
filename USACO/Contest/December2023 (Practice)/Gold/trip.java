//make sure to make new file!
import java.io.*;
import java.util.*;

public class trip{
   
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static boolean[] seen;
   public static Stack<Integer> stack;
   
   public static long base = 400001L;
   public static long MOD = 1000000007L;
   public static long[] pow;
   public static HashMap<Long,Long> compress;
   
   public static int D = 18;
   public static int[][] jump;
   public static long[][] jumplabel;
   public static long[][] jumphash;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<>(n+1);
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Edge>());
      }
      
      int N = 200005;
      pow = new long[N];
      pow[0] = 1L;
      for(int k = 1; k < N; k++){
         pow[k] = (pow[k-1] * base + MOD)%MOD;
      }
      
      compress = new HashMap<Long,Long>();
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         long li = Long.parseLong(st.nextToken());
      
         adj.get(a).add(new Edge(b,li));
         
         compress.put(li,(long)k);
      }
      
      //topological sort
      seen = new boolean[n+1];
      stack = new Stack<Integer>();
      
      for(int k = 1; k <= n; k++){
         if(!seen[k]){
            topsort(k);
         }
      }
      
      ArrayList<Integer> order = new ArrayList<Integer>();
      while(!stack.isEmpty()){
         order.add(stack.pop());
      }
      
      int[] len = new int[n+1];
      long[] sum = new long[n+1];
      
      jump = new int[n+1][D];
      jumplabel = new long[n+1][D];
      jumphash = new long[n+1][D];
      
      for(int k = n-1; k >= 0; k--){
         int v = order.get(k);
         
         Edge next = new Edge(0,0);
         for(Edge e : adj.get(v)){
            if(next.to == 0){
               next = e;
            } else{
               if(len[e.to] > len[next.to] || (len[e.to] == len[next.to] && less(e,next))){
                  next = e;
               }
            }
         }
         
         if(next.to == 0){
            len[v] = 0;
            sum[v] = 0L;
            
            for(int d = 0; d < D; d++){
               jump[v][d] = v;
               jumplabel[v][d] = -1;
               jumphash[v][d] = 0L;
            }
         } else {
            len[v] = len[next.to] + 1;
            sum[v] = sum[next.to] + next.label;
            
            jump[v][0] = next.to;
            jumplabel[v][0] = next.label;
            jumphash[v][0] = compress.get(next.label);
            for(int d = 1; d < D; d++){
               jump[v][d] = jump[jump[v][d-1]][d-1];
               jumplabel[v][d] = jumplabel[jump[v][d-1]][d-1];
               jumphash[v][d] = (jumphash[v][d-1] * pow[1 << (d-1)] + jumphash[jump[v][d-1]][d-1] + MOD)%MOD;
            }
            
            
         }
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 1; k <= n; k++){
         sj.add("" + len[k] + " " + sum[k]);
      }
      out.println(sj.toString());
      
      
      
      out.close();
   }
   
   //see if edge e is lexicographically less than next
   public static boolean less(Edge e, Edge next){
      if(e.label != next.label) return e.label < next.label;
      
      //binary search
      int l = 1;
      int r = 200005;
      boolean ans = false;
      while(l <= r){
         int mid = l + (r-l)/2;
         
         int ex = e.to;
         int ey = next.to;
         
         long hx = 0L;
         long hy = 0L;
         
         long lx = jump[ex][0];
         long ly = jump[ey][0];
         
         int cur = 0;
         for(int d = D-1; d >= 0; d--){
            if(cur + (1 << d) > mid) continue;
            
            hx = (hx * pow[1 << d] + jumphash[ex][d] + MOD)%MOD;
            hy = (hy * pow[1 << d] + jumphash[ey][d] + MOD)%MOD;
            
            lx = jumplabel[ex][d];
            ly = jumplabel[ey][d];
            
            ex = jump[ex][d];
            ey = jump[ey][d];
            
            cur += (1 << d);
         }
         
         if(lx == -1){
            r = mid-1;
            continue;
         }
         
         /*
         if(hx == hy){
            ans = (lx < ly);
            l = mid+1;
         } else {
            r = mid-1;
         }
         */
         if(hx != hy){
            ans = (lx < ly);
            r = mid-1;
         } else {
            l = mid+1;
         }
         
         
      }
      
      return ans;
      
   }
   
   public static void topsort(int v){
      seen[v] = true;
      
      for(Edge e : adj.get(v)){
         if(!seen[e.to]){
            topsort(e.to);
         }
      }
      
      stack.push(v);
   }
   
   public static class Edge{
      int to;
      long label;
      public Edge(int a, long b){
         to = a;
         label = b;
      }
   }
   
      
}