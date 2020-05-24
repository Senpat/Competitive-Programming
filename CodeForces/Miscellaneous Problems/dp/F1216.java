//make sure to make new file!
import java.io.*;
import java.util.*;

public class F1216{
   public static long[] t;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      char[] array = f.readLine().toCharArray();
      
      int[] router = new int[n];            //index of cheapest router in prev range
      
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
      for(int k = 0; k < n; k++){
         if(array[k] == '1'){
            pq.add(k);
         }
      }
      
      for(int k = 0; k < n; k++){
         if(pq.isEmpty() || pq.peek() > k){
            router[k] = -1;
         } else {
            router[k] = pq.peek();
            if(pq.peek() == k-m){
               pq.poll();
            }
         }
      }
      
      t = new long[4*200005];
      Arrays.fill(t,Long.MAX_VALUE);
      
      long[] dp = new long[n];
      dp[0] = 1;
      update(1,0,n-1,1,1);
      
      for(int k = 1; k < n; k++){
         if(router[k] == -1)                 dp[k] = dp[k-1]+k+1;
         else if(router[k] == router[k-1])   dp[k] = dp[k-1];
         else
            if(router[k] <= m)               dp[k] = router[k]+1;
            else                             dp[k] = min(1,0,n-1,router[k]-m,k-1)+router[k]+1;
         update(1,0,n-1,k+1,dp[k]);
      }
      
      out.println(dp[n-1]); 
      
      
      
      
      
      out.close();
   }
   
   public static void build(int[] a, int v, int tl, int tr) {
      if (tl == tr) {
         t[v] = a[tl];
      } else {
         int tm = (tl + tr) / 2;
         build(a, v*2, tl, tm);
         build(a, v*2+1, tm+1, tr);
         t[v] = Math.min(t[v*2],t[v*2+1]);
      }
   }

   public static long min(int v, int tl, int tr, int l, long r) {
      if (l > r) 
         return Long.MAX_VALUE;
      if (l == tl && r == tr) {
         return t[v];
      }
      int tm = (tl + tr) / 2;
      return Math.min(min(v*2, tl, tm, l, Math.min(r, tm)),min(v*2+1, tm+1, tr, Math.max(l, tm+1), r));
   }
   public static void update(int v, int tl, int tr, int pos, long new_val) {
      if (tl == tr) {
         t[v] = new_val;
      } else {
         int tm = (tl + tr) / 2;
         if (pos <= tm)
            update(v*2, tl, tm, pos, new_val);
         else
            update(v*2+1, tm+1, tr, pos, new_val);
         t[v] = Math.min(t[v*2],t[v*2+1]);
      }
   }
   
      
}