//make sure to make new file!
//semi-t
import java.io.*;
import java.util.*;

public class C523c{

   public static long MOD = 1000000007;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long[] array = new long[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      ArrayList<ArrayList<Integer>> div = new ArrayList<ArrayList<Integer>>();
      
      for(int k = 0; k <= n; k++) div.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n; k++){
         for(int j = Math.min(n,(int)Math.sqrt(array[k])); j > 0; j--){
            if(array[k] % j == 0){
               div.get(k).add(j);
            }
         }
         if(array[k] <= n) div.get(k).add((int)array[k]);
      }
      
      
      long[] dp = new long[n+1];
      
      dp[0] = 1L;
      
      for(int k = 0; k < n; k++){
         for(int d : div.get(k)){
            dp[d] = (dp[d] + dp[d-1] + MOD)%MOD;
         }
      }
      
      long answer = 0L;
      
      for(int k = 1; k <= n; k++){
         answer += dp[k];
      }
      
      answer %= MOD;
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   
}