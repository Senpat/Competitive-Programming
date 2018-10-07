//Mahmoud and Ehab and the pipartiteness
import java.io.*;
import java.util.*;

public class B862{
   
   public static HashSet<Integer> seen;
   public static HashSet<Integer> a;
   public static HashSet<Integer> b;
   public static ArrayList<HashSet<Integer>> adj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      a = new HashSet<Integer>();
      b = new HashSet<Integer>();
      
      adj = new ArrayList<HashSet<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new HashSet<Integer>());
      
      for(int k = 0; k < n-1; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         
         adj.get(x).add(y);
         adj.get(y).add(x);
      }
      
      seen = new HashSet<Integer>();
      dfs(1,0);
            
      long answer = (long)a.size() * (long)b.size() - n+1;
      
      out.println(answer);
      
      
      out.close();
   }
   
   public static void dfs(int v, int c){
      if(seen.contains(v)) return;
      
      seen.add(v);
      if(c%2==0) a.add(v);
      else b.add(v);
      
      for(int nei : adj.get(v)){
         if(!seen.contains(nei)){
            dfs(nei,c+1);
         }
      }
   }
   
}