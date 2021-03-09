//make sure to make new file!
import java.io.*;
import java.util.*;
//supports first 2 queries
//in contest - wrong
public class skyscraper{

   public static int n;
   public static SegmentTreeFast stheight;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] leftnum = new int[n];                        //number of skyscrapers that this skyscraper can see to the left
      int[] rightnum = new int[n];                       //to the right
      
      int[] leftseen = new int[n];                       //index of skyscraper that can see it to the left
      int[] rightseen = new int[n];                       //to the right
      
      stheight = new SegmentTreeFast(n);
      for(int k = 0; k < n; k++){
         stheight.modify(k,k,array[k]);
      }
      
      for(int k = 0; k < n; k++){
         int lefti = bsleft(k,array[k]);
         int righti = bsright(k,array[k]);
         
         leftseen[k] = lefti;
         rightseen[k] = righti;
         if(lefti != -1) rightnum[lefti]++;
         if(righti != -1) leftnum[righti]++;
      }
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
         
         int i = Integer.parseInt(st.nextToken());
         
         if(i == 1){
            int x = Integer.parseInt(st.nextToken());
            out.println(leftnum[x] + rightnum[x] + 1);
         } else if(i == 2){
            int x = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            
            
            
         } else if(i == 3){
         
         }
      }
         
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int bsleft(int i, int thresh){
      if(i == 0) return -1;
      
      int l = 0;
      int r = i-1;
      int ans = -1;
      int ansheight = -1;
      
      while(l <= r){
         int mid = l + (r-l)/2;
         int maxheight = stheight.query(mid,i-1);
         if(maxheight >= thresh){
            ans = mid;
            l = mid+1;
         } else {
            r = mid-1;
         }
      }
      
      return ans;
      
      /*
      l = ans;
      r = i-1;
      int ans2 = -1;
      
      while(l <= r){
         int mid = l + (r-l)/2;
         int maxheight = stheight.query(mid,i-1);
         if(maxheight != ansheight){
            r = mid-1;
         } else {
            l = mid+1;
            ans2 = mid;
         }
      }
      
      return ans2;
      */
   }
  
   public static int bsright(int i, int thresh){
      if(i == n-1) return -1;
      
      int l = i+1;
      int r = n-1;
      int ans = -1;
      
      while(l <= r){
         int mid = l + (r-l)/2;
         int maxheight = stheight.query(i+1,mid);
         if(maxheight >= thresh){
            ans = mid;
            r = mid-1;
         } else {
            l = mid+1;
         }
      }
      
      return ans;
      
      /*
      
      if(ans == -1) return -1;
      
      l = i+1;
      r = ans;
      int ans2 = -1;
      
      while(l <= r){
         int mid = l + (r-l)/2;
         int maxheight = stheight.query(i+1,mid);
         if(maxheight != ansheight){
            l = mid+1;
         } else {
            r = mid-1;
            ans2 = mid;
         }
      }
      
      return ans2;*/
   }    
      
   
   
   //SET MODIFICATION, MAX QUERY
   public static class SegmentTreeFast {
   
   // Modify the following 5 methods to implement your custom operations on the tree.
   // This example implements Add/Sum operations. Operations like Add/Max, Set/Max can also be implemented.
      int modifyOperation(int x, int y) {
         return y;
      }
   
   // query (or combine) operation
      int queryOperation(int leftValue, int rightValue) {
         return Math.max(leftValue,rightValue);
      }
   
      int deltaEffectOnSegment(int delta, int segmentLength) {
         if (delta == getNeutralDelta()) 
            return getNeutralDelta();
      // Here you must write a fast equivalent of following slow code:
      // int result = delta;
      // for (int i = 1; i < segmentLength; i++) result = queryOperation(result, delta);
      // return result;
         return delta;
      }
   
      int getNeutralDelta() {
         return 0;
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
   
      public SegmentTreeFast(int n) {
         value = new int[2 * n];
         for (int i = 0; i < n; i++) {
            value[i + n] = getInitValue();
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