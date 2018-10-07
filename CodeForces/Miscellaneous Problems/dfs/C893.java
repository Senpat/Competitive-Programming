//Rumor
import java.io.*;
import java.util.*;

public class C893{
   
   public static HashSet<Integer> seen;
   public static long curmin = Long.MAX_VALUE;
   public static ArrayList<HashSet<Integer>> adj;
   public static int[] array;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      array = new int[n+1];
      
      for(int k = 1; k <= n; k++) array[k] = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<HashSet<Integer>>();
      
      for(int k = 0; k <= n; k++) adj.add(new HashSet<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      seen = new HashSet<Integer>();
      
      long answer = 0L;
      for(int k = 1; k <= n; k++){
         if(seen.contains(k)) continue;
         
         dfs(k);
         answer+=curmin;
         curmin = Long.MAX_VALUE;
         
      }
      
      out.println(answer);  
      
      
      
      out.close();
   }
   
   public static void dfs(int x){
      curmin = Math.min(curmin,(long)array[x]);
      seen.add(x);
      for(int nei : adj.get(x)){
         if(!seen.contains(nei)){
            dfs(nei);
         }
      }
   }
            
   
}