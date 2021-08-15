//Place
import java.io.*;
import java.util.*;

public class Place{

   public static ArrayList<ArrayList<Integer>> adj;
   
   public static int[] order;
   public static int i;
   
   public static int[] subsize;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      long[] val = new long[n+1];
      
      //get 1
      val[1] = Long.parseLong(f.readLine());
      
      for(int k = 2; k <= n; k++){
         st = new StringTokenizer(f.readLine());
      
         long b = Long.parseLong(st.nextToken());
         int a = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(k);
         adj.get(k).add(a);
         
         val[k] = b;
         
      }
      
      order = new int[n+1];
      subsize = new int[n+1];
      
      i = 1;
      dfs(1,-1);
      
      /*
      int[] oindex = new int[n+1];
      for(int k = 1; k <= n; k++){
         oindex[order[k]] = k;
      }*/
      
      LazySegmentTree lst = new LazySegmentTree(1,n);
      
      for(int k = 1; k <= n; k++){
         lst.update(order[k],order[k],val[k]);
      }
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         char c = st.nextToken().charAt(0);
         
         if(c == 'p'){
            int i = Integer.parseInt(st.nextToken());
            long x = Long.parseLong(st.nextToken());
            
            lst.update(order[i]+1,order[i]+subsize[i]-1,x);
         }
         
         if(c == 'u'){
            int i = Integer.parseInt(st.nextToken());
            long answer = lst.get(order[i],order[i]);
            out.println(answer);
         }
      }
            
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      order[v] = i;
      i++;
      
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         dfs(nei,v);
         subsize[v] += subsize[nei];
      }
      
      subsize[v] += 1;
   }

}

//lazy segment tree from danny
class LazySegmentTree{
   int treeFrom;
   int treeTo;
      
   long[] value;
   long[] lazy;
            
   public LazySegmentTree(int tFrom, int tTo){
      treeFrom = tFrom;
      treeTo = tTo;
      int length = treeTo - treeFrom + 1;
      int e = 0;
      while ((1 << e) < length) {
         e++;
      }
      value = new long[1 << (e + 1)];
      lazy = new long[1 << (e + 1)];
   }
   
   public void update(int from, int to, long delta) {
      update(from, to, treeFrom, treeTo, 1, delta);
   }
   
   public long update(int from, int to, int segFrom, int segTo, int node, long delta){
      if (from > segTo || to < segFrom) {
         
      } else if (from <= segFrom && to >= segTo) {
         value[node] += delta;
         lazy[node] += delta;
      } else {
         int mid = (segFrom + segTo) / 2;
         value[node] = lazy[node] + Math.max(
                    update(from, to, segFrom, mid, 2 * node, delta),
                    update(from, to, mid + 1, segTo, (2 * node) + 1, delta)
               );
      }
      return value[node];
   }
      
   public long get(int from, int to){
      return query(from, to, treeFrom, treeTo, 1);
   }
   
   public long query(int from, int to, int segFrom, int segTo, int node){
      if (from > segTo || to < segFrom) {
         return 0L;
      } else if (from <= segFrom && to >= segTo) {
         return value[node];
      } else {
         int mid = (segFrom + segTo) / 2;
         return lazy[node] + Math.max(
                    query(from, to, segFrom, mid, 2 * node),
                    query(from, to, mid + 1, segTo, (2 * node) + 1)
               );
      }
   }
      
      
   
      
}