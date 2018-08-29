//Party Lemonade
//semi-t
import java.io.*;
import java.util.*;

public class C913b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      long[] array = new long[n];
      long[] two = new long[n];
      for(int k = 0; k < n; k++){
         two[k] = (long)Math.pow(2,k);
         array[k] = Long.parseLong(st.nextToken());
      }
      
      long[] dp = new long[n];
      dp[0] = array[0];
      
      for(int k = 1; k < n; k++){
         dp[k] = Math.min(array[k],dp[k-1]*2);
      }
      
      long[] mins = new long[n];
      mins[n-1] = dp[n-1];
      int[] mind = new int[n];
      mind[n-1] = n-1;
      for(int k = n-2; k >= 0; k--){
         if(dp[k]<mins[k+1]){
            mins[k] = dp[k];
            mind[k] = k;
         } else {
            mins[k] = mins[k+1];
            mind[k] = mind[k+1];
         }
      }
      
      long cur = m;
      long cost = 0;
      
      for(int k = n-1; k >= 0; k--){
         while(cur>=two[k]){
            cur-=two[mind[k]];
            cost+=mins[k];
         }
      }
      
      // while(cur>=0){
//          cur-=two[mind[0]];
//          cost+=mins[0];
//       }
      
      out.println(cost);
            
      
      out.close();
   }
   
}