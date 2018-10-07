//Cthulhu
//bad
import java.io.*;
import java.util.*;

public class B80{

   public static ArrayList<ArrayList<Integer>> adj;
   public static boolean[] seen;
   public static int cycles;
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>();
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      seen = new boolean[n+1];
      cycles = 0;
      for(int k = 1; k <= n; k++){
         if(!seen[k]){
            dfs(k,-1);
         }
      }
      
      out.println(cycles);
      if(cycles == 1){
         out.println("FHTAGN!");
      } else {
         out.println("NO");
      }
      
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      seen[v] = true;
      
      for(int nei : adj.get(v)){
         if(nei != p){
            if(seen[nei]){
               cycles++;
            } else {
               dfs(nei,v);
            }
         }
      }
   }
      
   
     
}