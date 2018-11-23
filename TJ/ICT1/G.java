//Russian Tunnels
import java.io.*;
import java.util.*;

public class G{

   public static ArrayList<ArrayList<Integer>> alist;
   public static boolean[] seen;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      alist = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++) alist.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         alist.get(a).add(b);
         alist.get(b).add(a);
         
      }
      
      int count = 0;
      seen = new boolean[n+1];
      
      for(int k = 1; k <= n; k++){
         if(!seen[k]){
            dfs(k);
            count++;
         }
      }
      
      out.println(count-1);
      
      
      out.close();
   }
   
   public static void dfs(int v){
      if(!seen[v]){
         seen[v] = true;
         for(int nei : alist.get(v)){
            if(!seen[nei]){
               dfs(nei);
            }
         }
      }
   }
   
}