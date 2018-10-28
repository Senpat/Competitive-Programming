//Arpa's weak amphitheater and Mehrdad's valuable Hoses
import java.io.*;
import java.util.*;

public class B741{

   public static ArrayList<ArrayList<Integer>> adj,dsus;
   public static int n,m,w,id;
   public static int[] weights,beauty,tw,tb;
   //public static ArrayList<Integer> tw,tb;
   public static boolean[] seen;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      w = Integer.parseInt(st.nextToken());
      
      weights = new int[n+1];
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 1; k <= n; k++) weights[k] = Integer.parseInt(st.nextToken());
      
      
      beauty = new int[n+1];
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 1; k <= n; k++) beauty[k] = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0 ; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
   //       tw = new ArrayList<Integer>();
   //       tb = new ArrayList<Integer>();
      tw = new int[1001];
      tb = new int[1001];
   
      dsus = new ArrayList<ArrayList<Integer>>();             //get(id) = list is vertices in dsu
      dsus.add(new ArrayList<Integer>()); //blank
      id = 1;
      seen = new boolean[n+1];
      
      
      for(int k = 1; k <= n; k++){
         if(!seen[k]){
            dsus.add(new ArrayList<Integer>());
            dsu(k,id);
            id++;
         }
      }
         
      
      int[][] dp = new int[id][w+1];                          //[dsu id][weight] = beauty
      
      int maxb = 0;
      
      for(int k = 0; k < id-1; k++){
         for(int j = 0; j < w; j++){
            if(j!=0 && dp[k][j] == 0) continue;
            if(j+tw[k+1] <= w){
            
               dp[k+1][j+tw[k+1]] = Math.max(dp[k+1][j+tw[k+1]],dp[k][j] + tb[k+1]);
               maxb = Math.max(maxb,dp[k+1][j+tw[k+1]]);
            }
            for(int i : dsus.get(k+1)){
               if(j+weights[i] <= w){
                  dp[k+1][j+weights[i]] = Math.max(dp[k+1][j+weights[i]],dp[k][j] + beauty[i]);
                  maxb = Math.max(maxb,dp[k+1][j+weights[i]]);
               }
            }
            //dont add anything in that dsu
            dp[k+1][j] = Math.max(dp[k+1][j],dp[k][j]);
            maxb = Math.max(maxb,dp[k+1][j]);
         }
      }
      
      out.println(maxb);
      
            
      
      out.close();
   }
   
   public static void dsu(int v,int id){
      if(!seen[v]){
         seen[v] = true;
         dsus.get(id).add(v);
         tw[id]+=weights[v];
         tb[id]+=beauty[v];
         for(int nei : adj.get(v)){
            if(!seen[nei]){
               dsu(nei,id);
            }
         }
      }
   }
   
}