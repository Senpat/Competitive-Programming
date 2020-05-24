//make sure to make new file!
import java.io.*;
import java.util.*;

public class C639{

   public static ArrayList<ArrayList<Integer>> adj;
   
   public static boolean cycle = false;
   public static boolean[] seen;
   public static boolean[] stack;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      boolean[] into = new boolean[n+1];
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         into[Math.max(a,b)] = true;
      }
      
      //check for cycles
      seen = new boolean[n+1];
      for(int k = 1; k <= n; k++){
         if(!seen[k]){
            stack = new boolean[n+1];
            dfs(k);
         }
      }
      
      if(cycle){
         out.println("-1");
      } else {
         StringJoiner sj = new StringJoiner("");
         int numa = 0;
         
         for(int k = 1; k <= n; k++){
            if(into[k]){
               sj.add("E");
            } else {
               sj.add("A");
               numa++;
            }
         }
         
         out.println(numa);
         out.println(sj.toString());
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      
      if(stack[v]){
         cycle = true;
      }
      
      if(seen[v]){
         return;
      }
      
      seen[v] = true;
      
      stack[v] = true;
      
      for(int nei : adj.get(v)){
         dfs(nei);
         
      }
      
      stack[v] = false;
   }
   
      
}