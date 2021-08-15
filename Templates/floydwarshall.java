//make sure to make new file!
import java.io.*;
import java.util.*;

public class floydwarshall{

   public static ArrayList<ArrayList<Integer>> adj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      //floyd warshall
      long[][] d = new long[n+1][n+1];
      for(int k = 0; k <= n; k++){
         Arrays.fill(d[k],1000000);
         d[k][k] = 0L;
      }
      
      for(int k = 0; k < n-1; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
         d[a][b] = 1L;
         d[b][a] = 1L;
      }
      
      for (int k = 1; k <= n; ++k) {
          for (int i = 1; i <= n; ++i) {
              for (int j = 1; j <= n; ++j) {
                  d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]); 
              }
          }
      }
      
      
      
      
      
      
      out.close();
   }
   
      
}