//Sum in the tree
import java.io.*;
import java.util.*;

public class D1099{

   public static int[] parent;
   public static ArrayList<ArrayList<Integer>> adj;
   public static long[] array,value;
   public static long answer;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      parent = new int[n+1];
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 2; k <= n; k++){
         
         int i = Integer.parseInt(st.nextToken());
         
         parent[k] = i;
         adj.get(i).add(k);
         adj.get(k).add(i);
      }
      
      array = new long[n+1];
      
      st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= n; k++){
         
         array[k] = Long.parseLong(st.nextToken());
      }
      
      value = new long[n+1];
      value[1] = array[1];
      
      answer = 0L;
      
      dfs(1,-1);
      
      out.println(answer);
      out.close();
   }
   
   public static void dfs(int v, int p){
      if(array[v] != -1){
         answer+=value[v];
         
         for(int nei : adj.get(v)){
            if(nei == p) continue;
            dfs(nei,v);
         }
      } else {
         long min = Long.MAX_VALUE;
         for(int nei : adj.get(v)){
            if(nei == p) continue;
            min = Math.min(min,array[nei]);
         }
         if(min == Long.MAX_VALUE) return;                        //leaf,set to 0;
         
         if(min < array[p]){
            out.println("-1");
            out.close();
            System.exit(0);
         }
         
         value[v] = min - array[p];
         answer+=value[v];
         
         for(int nei : adj.get(v)){
            if(nei == p) continue;
            
            value[nei] = array[nei] - min;
            dfs(nei,v);
         } 
      }
   }
         
         
         
      
   
}