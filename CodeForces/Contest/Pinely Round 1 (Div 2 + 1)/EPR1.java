//make sure to make new file!
import java.io.*;
import java.util.*;

public class EPR1{
   
   public static int n;
   public static boolean[][] adj;
   
   public static boolean[] seen;
   
   public static void dfs(int v){
      seen[v] = true;
      for(int nei = 0; nei < n; nei++){
         if(!seen[nei] && adj[v][nei]) dfs(nei);
      }
   }
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         n = Integer.parseInt(f.readLine());
      
         adj = new boolean[n][n];
         for(int k = 0; k < n; k++){
            String s = f.readLine();
            for(int j = 0; j < n; j++){
               adj[k][j] = (s.charAt(j) == '1');
            }
         }
         
         //check if currently is connected
         seen = new boolean[n];
         dfs(0);
         
         boolean isconnected = true;
         for(int k = 0; k < n; k++){
            isconnected &= seen[k];
         }
         
         if(isconnected){
            out.println(0);
            continue;
         }
         
         int min = n+1;
         int minval = -1;
         for(int k = 0; k < n; k++){
            int neis = 0;
            for(int j = 0; j < n; j++){
               if(adj[k][j]) neis++;
            }
            
            if(neis < min){
               min = neis;
               minval = k;
            }
         }
         
         out.println(min+1);
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            if(adj[minval][k]){
               sj.add("" + (k+1));
            }
         }
         sj.add("" + (minval+1));
         
         out.println(sj.toString());
      }
      
      
      
      
      out.close();
   }
   
      
}