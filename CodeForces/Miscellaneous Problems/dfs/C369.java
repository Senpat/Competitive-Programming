//Velera and Elections
//semi-t
import java.io.*;
import java.util.*;

public class C369{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static HashSet<Integer> whites;
   public static int[] d;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Integer>());
      }
      
      whites = new HashSet<Integer>();
      
      for(int k = 0; k < n-1; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
      
         adj.get(a).add(b);
         adj.get(b).add(a);
         
         if(c == 2){
            whites.add(a);
            whites.add(b);
         }
      
      }
      
      d = new int[n+1];
      
      dfs(1,-1); 
      
      ArrayList<Integer> answer = new ArrayList<Integer>();
      
      for(int k = 1; k <= n; k++){
         if(whites.contains(k) && d[k] == 1){
            answer.add(k);
         }
      }
      
      out.println(answer.size());
      
      for(int i : answer){
         out.print(i + " ");
      }
      
      
      
      
      out.close();
   }
   
   
   public static void dfs(int v, int p){
      
      if(whites.contains(v)){
         d[v] = 1;
      }
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         
         dfs(nei,v);
         d[v] += d[nei];
      }
   }
   
}