//make sure to make new file!
import java.io.*;
import java.util.*;

public class E{
   
   public static long MOD = 11092019L;
   public static int[] values;
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static int[] bits;
   public static int n;
   
   public static int[] maxlen;
   public static long[] num;
   public static long[] sum;
   
   public static ArrayList<HashSet<Integer>> lentonodes;
   
   public static int curmax;
   
   public static int[] t;
   public static int MAX_N = 1000002;

   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());

      values = new int[n+1];
      for(int k = 1; k <= n; k++){
         values[k] = Integer.parseInt(f.readLine());
      }
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 2; k <= n; k++){
         int i = Integer.parseInt(f.readLine());
         
         adj.get(k).add(i);
         adj.get(i).add(k);
      }
      
      //bits = new int[n+1];
      
      //lentonodes = new ArrayList<HashSet<Integer>>(n+1);
      //for(int k = 0; k <= n; k++) lentonodes.add(new HashSet<Integer>());
      
      t = new int[4*MAX_N];
      Arrays.fill(t,0);      //or build
      
      maxlen = new int[n+1];
      num = new long[n+1];
      
      maxlen[1] = 1;
      curmax = 1;
      
      //lentonodes.get(1).add(1);
      update(1,0,MAX_N-1,values[1],1);

      dfs(1,-1);
      
      /*
      for(int k = 1; k <= n; k++){
         out.println(maxlen[k]);
      }*/
      
      
      num[1] = 1L;
      sum = new long[n+1];
      sum[1] = 1L;
      
      dfs2(1,-1);
         
      int max = 0;
      for(int k = 1; k <= n; k++){
         max = Math.max(max,maxlen[k]);
      }
      
      long nummax = 0L;
      
      for(int k = 1; k <= n; k++){
         if(maxlen[k] == max){
            nummax = (nummax + num[k] + MOD)%MOD;
         }
      }
      
      out.println(max + " " + nummax);
      
      
      
      out.close();
   }
   
   public static void dfs2(int v, int p){
      if(p != -1){
         if(maxlen[v] == 1){
            num[v] = 1L;
            sum[1] = (sum[1] + 1L + MOD)%MOD;
         } else {
            num[v] = sum[maxlen[v]-1];
            sum[maxlen[v]] = (sum[maxlen[v]] + num[v] + MOD)%MOD;
         }
      }
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfs2(nei,v);
      }
      
      //remove
      sum[maxlen[v]] = ((sum[maxlen[v]] - num[v] + MOD)%MOD+MOD)%MOD;
   }
   
   
   public static void dfs(int v, int p){
      int prev = max(1,0,MAX_N-1,values[v],values[v]);
      if(p != -1){
         maxlen[v] = max(1,0,MAX_N-1,0,values[v])+1;
         update(1,0,MAX_N-1,values[v],maxlen[v]);
      }
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfs(nei,v);
      }
      
      //remove
      update(1,0,MAX_N-1,values[v],prev);
   }
   
   
   
   
   
   
      //to call: v=1, tl = 0, tr = n-1
   public static int max(int v, int tl, int tr, int l, int r) {
      if (l > r) 
         return 0;
      if (l == tl && r == tr) {
         return t[v];
      }
      int tm = (tl + tr) / 2;
      return Math.max(max(v*2, tl, tm, l, Math.min(r, tm)),max(v*2+1, tm+1, tr, Math.max(l, tm+1), r));
   }
   
   //to call: v=1, tl = 0, tr = n-1
   public static void update(int v, int tl, int tr, int pos, int new_val) {
      if (tl == tr) {
         t[v] = new_val;
      } else {
         int tm = (tl + tr) / 2;
         if (pos <= tm)
            update(v*2, tl, tm, pos, new_val);
         else
            update(v*2+1, tm+1, tr, pos, new_val);
         t[v] = Math.max(t[v*2],t[v*2+1]);
      }
   }
   
   
   
   
   /*
   public static void update(int i, int x){
      for(; i <= n; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static int psum(int i){
      int sum = 0;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   
   }*/
   
      
}