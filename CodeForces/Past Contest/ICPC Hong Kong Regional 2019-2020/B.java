//make sure to make new file!
import java.io.*;
import java.util.*;

public class B{

   public static ArrayList<ArrayList<Integer>> adj;
   /*
   public static int[] depth;
   public static int[] subsize;
   
   public static int count;
   */
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         adj = new ArrayList<>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
         for(int k = 0; k < n-1; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
         
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(b);
            adj.get(b).add(a);
         }
         
         //every move removes an odd # of nodes
         if(n%2 == 1){
            //need to remove an odd # of odd # of nodes
            out.println("Alice");
         } else {
            //need to remove an even # of odd # of nodes
            out.println("Bob");
         }
      }
      
      
      
      
      out.close();
   }
   /*
   public static void dfs2(int v, int p){
      if(
   }
   
   public static void dfs1(int v, int p, int d){
      depth[v] = d;
      subsize[v] = 1;
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfs1(nei,v,d+1);
         
         subsize[v] += subsize[nei];
      }
   }
   
   */
   
      
}