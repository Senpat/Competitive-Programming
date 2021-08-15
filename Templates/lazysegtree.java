import java.io.*;
import java.util.*;

public class lazysegtree{


   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      out.close();
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