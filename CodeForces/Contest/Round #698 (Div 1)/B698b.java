//make sure to make new file!
import java.io.*;
import java.util.*;

public class B698b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         String ssa = f.readLine();
         String ssb = f.readLine();
         
         int[] sa = new int[n];
         int[] sb = new int[n];
         
         for(int k = 0; k < n; k++){
            if(ssa.charAt(k) == '0') sa[k] = 0;
            else sa[k] = 1;
            if(ssb.charAt(k) == '0') sb[k] = 0;
            else sb[k] = 1;
         }
         
         Query[] queries = new Query[m];
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
      
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            
            queries[k] = new Query(a,b);
         }
         
         SegmentTree segtree = new SegmentTree(n);
         for(int k = 0; k < n; k++){
            segtree.modify(k,k,sb[k]);
         }
         
         
         boolean fail = false;
         
         for(int k = m-1; k >= 0; k--){
            if(queries[k].l == queries[k].r) continue; 
         
            int sum = segtree.query(queries[k].l,queries[k].r);
            int thresh = (queries[k].r-queries[k].l+1)/2;
            if((queries[k].r-queries[k].l+1)%2 == 0 && sum == thresh){
               fail = true;
               break;
            }
            
            if(sum <= thresh) segtree.modify(queries[k].l,queries[k].r,0);
            else segtree.modify(queries[k].l,queries[k].r,1);
            /*
            for(int j = 0; j < n; j++){
               out.print(segtree.query(j,j) + " ");
            }
            out.println();*/
         }
         
         if(!fail){
            for(int k = 0; k < n; k++){
               if(segtree.query(k,k) != sa[k]){
                  fail = true;
                  break;
               }
            }
         }
         
         if(!fail){
            out.println("YES");
         } else {
            out.println("NO");
         }
      
      
      }
      
      
      
      
      out.close();
   }
   
   public static class Query{
      int l;
      int r;
      public Query(int a, int b){
         l = a;
         r = b;
      }
   }
   
   //from https://sites.google.com/site/indy256/algo/segment_tree_fast
   public static class SegmentTree{
   
   // Modify the following 5 methods to implement your custom operations on the tree.
   // This example implements Add/Sum operations. Operations like Add/Max, Set/Max can also be implemented.
      int modifyOperation(int x, int y) {    //SET OPERATION
         return y;
      }
   
   // query (or combine) operation
      int queryOperation(int leftValue, int rightValue) {   //SUM OPERATION
         return leftValue + rightValue;
      }
   
      int deltaEffectOnSegment(int delta, int segmentLength) {
         if (delta == getNeutralDelta()) 
            return getNeutralDelta();
      // Here you must write a fast equivalent of following slow code:
      // int result = delta;
      // for (int i = 1; i < segmentLength; i++) result = queryOperation(result, delta);
      // return result;
         return delta * segmentLength;
      }
   
      int getNeutralDelta() {
         return -1;
      }
   
      int getInitValue() {
         return 0;
      }
   
   // generic code
      int[] value;
      int[] delta; // delta[i] affects value[i], delta[2*i+1] and delta[2*i+2]
   
      int joinValueWithDelta(int value, int delta) {
         if (delta == getNeutralDelta()) 
            return value;
         return modifyOperation(value, delta);
      }
   
      int joinDeltas(int delta1, int delta2) {
         if (delta1 == getNeutralDelta()) 
            return delta2;
         if (delta2 == getNeutralDelta()) 
            return delta1;
         return modifyOperation(delta1, delta2);
      }
   
      void pushDelta(int i) {
         int d = 0;
         for (; (i >> d) > 0; d++) {
         }
         for (d -= 2; d >= 0; d--) {
            int x = i >> d;
            value[x >> 1] = joinNodeValueWithDelta(x >> 1, 1 << (d + 1));
            delta[x] = joinDeltas(delta[x], delta[x >> 1]);
            delta[x ^ 1] = joinDeltas(delta[x ^ 1], delta[x >> 1]);
            delta[x >> 1] = getNeutralDelta();
         }
      }
   
      public SegmentTree(int n) {
         value = new int[2 * n];
         for (int i = 0; i < n; i++) {
            value[i + n] = getInitValue();
            //value[i+n] = initarray[i];
         }
         for (int i = 2 * n - 1; i > 1; i -= 2) {
            value[i >> 1] = queryOperation(value[i], value[i ^ 1]);
         }
         delta = new int[2 * n];
         Arrays.fill(delta, getNeutralDelta());
      }
   
      int joinNodeValueWithDelta(int i, int len) {
         return joinValueWithDelta(value[i], deltaEffectOnSegment(delta[i], len));
      }
   
      public int query(int from, int to) {
         from += value.length >> 1;
         to += value.length >> 1;
         pushDelta(from);
         pushDelta(to);
         int res = 0;
         boolean found = false;
         for (int len = 1; from <= to; from = (from + 1) >> 1, to = (to - 1) >> 1, len <<= 1) {
            if ((from & 1) != 0) {
               res = found ? queryOperation(res, joinNodeValueWithDelta(from, len)) : joinNodeValueWithDelta(from, len);
               found = true;
            }
            if ((to & 1) == 0) {
               res = found ? queryOperation(res, joinNodeValueWithDelta(to, len)) : joinNodeValueWithDelta(to, len);
               found = true;
            }
         }
         if (!found) throw new RuntimeException();
         return res;
      }
   
      public void modify(int from, int to, int delta) {
         from += value.length >> 1;
         to += value.length >> 1;
         pushDelta(from);
         pushDelta(to);
         int a = from;
         int b = to;
         for (; from <= to; from = (from + 1) >> 1, to = (to - 1) >> 1) {
            if ((from & 1) != 0) {
               this.delta[from] = joinDeltas(this.delta[from], delta);
            }
            if ((to & 1) == 0) {
               this.delta[to] = joinDeltas(this.delta[to], delta);
            }
         }
         for (int i = a, len = 1; i > 1; i >>= 1, len <<= 1) {
            value[i >> 1] = queryOperation(joinNodeValueWithDelta(i, len), joinNodeValueWithDelta(i ^ 1, len));
         }
         for (int i = b, len = 1; i > 1; i >>= 1, len <<= 1) {
            value[i >> 1] = queryOperation(joinNodeValueWithDelta(i, len), joinNodeValueWithDelta(i ^ 1, len));
         }
      }
   }
      
}