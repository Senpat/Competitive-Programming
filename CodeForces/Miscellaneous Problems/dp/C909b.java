//Python Indentation
import java.io.*;
import java.util.*;

public class C909b{

   public static long MOD = 1000000007;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      char[] array = new char[n];
      
      for(int k = 0; k < n; k++){
         array[k] = f.readLine().charAt(0);
      }
      
      long[][] dp = new long[n][n];
      
      dp[0][0] = 1;
      
      for(int k = 1; k < n; k++){
         for(int j = 0; j < n; j++){
         
            if(array[k-1] == 'f' && j > 0){
               dp[k][j]+=dp[k-1][j-1];
            } 
            
            if(array[k-1] == 's'){
               for(int h = 0; h <= j; h++){
                  dp[k+1][h]+=dp[k][j];
                  if(dp[k+1][h] > MOD) dp[k+1][h] -= MOD;
               }
            }
         }
      }
      
      long answer = 0L;
      
      for(int k = 0; k < n; k++){
         answer = (answer + dp[n-1][k] + MOD) % MOD;
      }
      
      out.println(answer);
      
      out.close();
   }
   
}