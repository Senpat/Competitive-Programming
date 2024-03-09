//Tree XOR
import java.io.*;
import java.util.*;

public class D1882{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static long[] val;
   
   public static long[] answer;
   
   public static long[] subsize;
   public static int[] parent;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         
         adj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
         
         val = new long[n+1];
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int k = 1; k <= n; k++){
            val[k] = Long.parseLong(st.nextToken());
         }
         
         for(int k = 0; k < n-1; k++){
            st = new StringTokenizer(f.readLine());
         
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(b);
            adj.get(b).add(a);
         }
         
         subsize = new long[n+1];
         parent = new int[n+1];
         dfs_size(1,-1);
         
         answer = new long[n+1];
         //get the answer for 1
         for(int k = 2; k <= n; k++){
            answer[1] += subsize[k] * (val[k] ^ val[parent[k]]);
         }
         dfs(1);
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 1; k <= n; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      for(int nei : adj.get(v)){
         if(nei == parent[v]) continue;
         answer[nei] = answer[v] + (subsize[1] - 2L*subsize[nei]) * (val[v] ^ val[nei]);
         dfs(nei);
      }
   }
   
   public static void dfs_size(int v, int p){
      parent[v] = p;
      subsize[v] = 1L;
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfs_size(nei,v);
         subsize[v] += subsize[nei];
      }
   }
      
}