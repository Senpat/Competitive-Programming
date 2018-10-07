//DZY Loves Chemistry
import java.io.*;
import java.util.*;

public class B445{

   public static HashSet<Integer> seen;
   public static ArrayList<HashSet<Integer>> adj;
   public static int cursize;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<HashSet<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new HashSet<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      long answer = 1L;
      
      seen = new HashSet<Integer>();
      cursize = 0;
      for(int k = 1; k <= n; k++){
         if(seen.contains(k)) 
            continue;
         
         if(adj.get(k).size()>0){
            dfs(k);
            answer*=Math.pow(2,cursize-1);
            cursize = 0;
         }
      }
      
      out.println(answer);
      
      out.close();
   }
   
   public static void dfs(int v){
      if(seen.contains(v)) 
         return;
      cursize++;
      seen.add(v);
      for(int nei : adj.get(v)){
         if(!seen.contains(nei)){
            dfs(nei);
         }
      }
   }
      
   
}