//make sure to make new file!
import java.io.*;
import java.util.*;

public class B800{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] p;
   public static long[] l;
   public static long[] r;
   
   public static int answer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         adj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++){
            adj.add(new ArrayList<Integer>());
         }
         
         p = new int[n+1];
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int k = 2; k <= n; k++){
            p[k] = Integer.parseInt(st.nextToken());
            
            adj.get(p[k]).add(k);
            adj.get(k).add(p[k]);
         }
         
         l = new long[n+1];
         r = new long[n+1];
         for(int k = 1; k <= n; k++){
            st = new StringTokenizer(f.readLine());
            l[k] = Long.parseLong(st.nextToken());
            r[k] = Long.parseLong(st.nextToken());
         }
         
         answer = 0;
         
         long i = dfs(1,-1);
         
         out.println(answer);
         
         
      

      }
      
      
      
      
      out.close();
   }
   
   public static long dfs(int v, int p){
      
      long sum = 0L;
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         
         sum += dfs(nei,v);
      }
      
      if(sum < l[v]){
         answer++;
         return r[v];
      }
      
      if(sum < r[v]){
         return sum;
      }
      
      return r[v];
   
      
   
   }
   
      
}