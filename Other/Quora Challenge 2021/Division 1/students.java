//make sure to make new file!
import java.io.*;
import java.util.*;

public class students{
   
   public static ArrayList<ArrayList<Integer>> adj;   
   public static HashSet<Integer> seen;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int N = n*n-1;
      
      adj = new ArrayList<ArrayList<Integer>>(N+1);
      for(int k = 0; k <= N; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a1 = Integer.parseInt(st.nextToken())-1;
         int b1 = Integer.parseInt(st.nextToken())-1;
         int a2 = Integer.parseInt(st.nextToken())-1;
         int b2 = Integer.parseInt(st.nextToken())-1;
         
         adj.get(n*a1+b1).add(n*a2+b2);
         adj.get(n*a2+b2).add(n*a1+b1);
      }
      
      seen = new HashSet<Integer>();
      int answer = 0;
      for(int k = 0; k <= N; k++){
         if(!seen.contains(k)){
            componentsize = 0;
            coloring = 0;
            dfs(k,0);
            answer += Math.max(coloring,componentsize-coloring);
         }
      }
         
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   

   
      
}