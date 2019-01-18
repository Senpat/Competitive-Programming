//Minimum Diameter Tree
//upsolve
import java.io.*;
import java.util.*;

public class D528{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      double m = Double.parseDouble(st.nextToken());
      
      ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      double leaves = 0.0;
      
      for(int k = 1; k <= n; k++){
         if(adj.get(k).size() == 1){
            leaves++;
         }
      }
      
      double answer = 2 * m / leaves;
      
      out.println(answer);
      
      out.close();
   }
   
}