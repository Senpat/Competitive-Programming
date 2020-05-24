//make sure to make new file!
import java.io.*;
import java.util.*;

public class K{

   public static ArrayList<ArrayList<Integer>> adj;
   public static HashSet<Integer> seen;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++)adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      seen = new HashSet<Integer>();
      
      int comp = 0;
      
      for(int k = 1; k <= n; k++){
         if(!seen.contains(k)){
            comp++;
            dfs(k);
         }
      }
      
      out.println(comp-1);
      

      
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      seen.add(v);
      
      for(int nei : adj.get(v)){
         if(!seen.contains(nei)) dfs(nei);
      }
   }
      
}