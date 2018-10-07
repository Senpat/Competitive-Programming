//Socks
//tle
import java.io.*;
import java.util.*;

public class C731b{

   public static ArrayList<ArrayList<Integer>> adj,incomp;
   public static boolean[] seen;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int[] colors = new int[n+1];
      
      for(int k = 1; k <= n; k++) colors[k] = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>();
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      incomp = new ArrayList<ArrayList<Integer>>();
      seen = new boolean[n+1];
      for(int k = 1; k <= n; k++){
         if(!seen[k]){
            incomp.add(new ArrayList<Integer>());
            dfs(k);
         }
      }
      
      int answer = 0;
      for(ArrayList<Integer> compo : incomp){
         int[] curarray = new int[c+1];
         int max = 0;
         for(int i : compo){
            curarray[colors[i]]++;
            max = Math.max(curarray[colors[i]],max);
         }
         answer+=compo.size()-max;
      }
      
      
      out.print(answer);
              
      
      out.close();
   }
   
   public static void dfs(int v){
      seen[v] = true;
      incomp.get(incomp.size()-1).add(v);
      
      for(int nei : adj.get(v)){
         if(!seen[nei]){
            dfs(nei);
         }
      }
   }
   
}