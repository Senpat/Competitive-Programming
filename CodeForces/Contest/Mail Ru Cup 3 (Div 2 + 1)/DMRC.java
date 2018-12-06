//make sure to make new file!
import java.io.*;
import java.util.*;

public class DMRC{

   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] count;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      if(n==1){
         out.println(1);
         out.close();
         System.exit(0);
      }
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 2; k <= n; k++){
         int i = Integer.parseInt(st.nextToken());
         
         adj.get(k).add(i);
         adj.get(i).add(k);
      }
      
      count = new int[n+1];
      dfs(1,0);
      
      
      Arrays.sort(count);
      
      for(int k = 1; k <= n; k++){
         out.print(count[k] + " ");
      }
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      if(adj.get(v).size() == 1 && v!=1){
         count[v] = 1;
      } else {
         count[v] = 0;
      }
      for(int i : adj.get(v)){
         if(i==p) continue;
         
         dfs(i,v);
         
         count[v] += count[i];
      }
   }
   
}