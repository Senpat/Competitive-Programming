//Compatible Numbers
import java.io.*;
import java.util.*;

public class E165{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int P = 23;
      //similar to sos dp
      //-1 means no element
      int[] dp = new int[1 << P];
      Arrays.fill(dp,-1);
      for(int k = 0; k < n; k++){
         dp[array[k]] = array[k];
      }
      
      for(int i = 0; i < P; i++){
         for(int mask = 0; mask < (1 << P); mask++){
            if((mask & (1 << i)) != 0){
               dp[mask] = Math.max(dp[mask],dp[mask ^ (1 << i)]);
            }
         }
      }
      
      int[] answer = new int[n];
      int mask = (1 << P)-1;
      for(int k = 0; k < n; k++){
         answer[k] = dp[mask ^ array[k]];
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}