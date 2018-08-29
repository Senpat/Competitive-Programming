//Sereja and Suffixes
import java.io.*;
import java.util.*;

public class B368{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] dp = new int[n];
      
      HashSet<Integer> used = new HashSet<Integer>();
      
      used.add(array[n-1]);
      dp[n-1] = 1;
      
      for(int k = n-2; k >= 0; k--){
         if(used.contains(array[k])){
            dp[k] = dp[k+1];
         } else {
            used.add(array[k]);
            dp[k] = dp[k+1]+1;
         }
      }
      
      for(int k = 0; k < m; k++){
         int i = Integer.parseInt(f.readLine());
         out.println(dp[i-1]);
      }
      
      out.close();
   }
   
}