//make sure to make new file!
import java.io.*;
import java.util.*;
//should tle
public class Z{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] dists;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      dists = new int[n+1];
      Arrays.fill(dists,Integer.MAX_VALUE);
      dists[1] = 0;
      dfs(1,-1);
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
      
         int i = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         
         if(i == 1){
            dists[x] = 0;
            dfs(x,-1);
         } else {
            out.println(dists[x]);
         }
      }
      
      
      
      
      
      
      out.close();
   }
   
   
   public static void dfs(int v, int p){
      
      for(int nei : adj.get(v)){
         if(nei == p || dists[nei] <= dists[v]+1) continue;
         dists[nei] = dists[v] + 1;
         dfs(nei,v);
      }
   }
   
}