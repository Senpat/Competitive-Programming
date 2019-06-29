//make sure to make new file!
import java.io.*;
import java.util.*;

public class E565{

   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] color;
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         adj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++){
            adj.add(new ArrayList<Integer>());
         }
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
      
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(b);
            adj.get(b).add(a);
         }
            
         color = new int[n+1];
         
         color[1] = 1;
         dfs(1);
         
         ArrayList<Integer> a1 = new ArrayList<Integer>();
         ArrayList<Integer> a2 = new ArrayList<Integer>();
         
         for(int k = 1; k <= n; k++){
            if(color[k] == 1) a1.add(k);
            if(color[k] == 2) a2.add(k);
         }
         
         if(a1.size() <= n/2){
            out.println(a1.size());
            for(int i : a1) out.print(i + " ");
         } else {
            out.println(a2.size());
            for(int i : a2) out.print(i + " ");
         } 
         out.println();
         
         
      
      
      }
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      
      for(int nei : adj.get(v)){
         if(color[nei] != 0) continue;
         color[nei] = 3-color[v];
         dfs(nei);
      }
   }
      
}