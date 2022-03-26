//make sure to make new file!
import java.io.*;
import java.util.*;

public class D774b{

   public static ArrayList<ArrayList<Integer>> adj;
   
   public static int[] depth;
   public static int maxdepth;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      if(n==2){
         out.println("2 2");
         out.println("1 1");
         out.close();
         return;
      }
      
      depth = new int[n+1];
      
      depth[1] = 0;
      maxdepth = 0;
      dfs(1,-1);
      
      int[] numd = new int[maxdepth+1];
      long[] sumd = new long[maxdepth+1];
      
      for(int k = 1; k <= n; k++){
         numd[depth[k]]++;
         sumd[depth[k]]+=(long)adj.get(k).size();
      }
      
      int[] dp = new int[maxdepth+1];
      Arrays.fill(dp,Integer.MAX_VALUE);
      long[] dpsum = new long[maxdepth+1];
      Arrays.fill(dpsum,-1L);
      int[] par = new int[maxdepth+1];
      Arrays.fill(par,-1);
      
      dp[0] = numd[0];
      dpsum[0] = sumd[0];
      
      dp[1] = numd[1];
      dpsum[1] = sumd[1]+numd[0];
      
      for(int k = 2; k <= maxdepth; k++){
         //go two back
         dp[k] = numd[k]+dp[k-2];
         dpsum[k] = sumd[k]+dpsum[k-2]+numd[k-1];
         par[k] = k-2;
      
         if(k >= 3){
            //go three back
            int dp3 = numd[k] + dp[k-3];
            long sum3 = sumd[k] + dpsum[k-3] + numd[k-1] + numd[k-2];
            
            if(dp3 > dp[k] || (dp3 == dp[k] && sum3 < dpsum[k])){
               dp[k] = dp3;
               dpsum[k] = sum3;
               par[k] = k-3;
            }
         }
      }
      
      //start at n
      int numn = dp[maxdepth];
      long sumn = dpsum[maxdepth];
      
      //start at n-1
      int numnm1 = dp[maxdepth-1];
      long sumnm1 = dpsum[maxdepth-1] + numd[maxdepth];
      
      int i = maxdepth;
      if(numnm1 > numn || (numnm1 == numn && sumnm1 < sumn)) i = maxdepth-1;
      
      if(i == maxdepth){
         out.println(numn + " " + sumn);
      } else {
         out.println(numnm1 + " "+ sumnm1);
      }
      
      
      HashSet<Integer> depths = new HashSet<Integer>();
      while(i != -1){
         depths.add(i);
         i = par[i];
      }
      
      
      StringJoiner sj = new StringJoiner(" ");
      
      for(int k = 1; k <= n; k++){
         if(depths.contains(depth[k])){
            sj.add("" + adj.get(k).size());
         } else {
            sj.add("1");
         }
      }
      
      out.println(sj.toString());
      
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      
      maxdepth = Math.max(maxdepth,depth[v]);
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         depth[nei] = 1+depth[v];
         dfs(nei,v);
      }
   }
      
}