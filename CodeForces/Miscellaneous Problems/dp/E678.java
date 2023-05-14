//Another Sith Tournament
import java.io.*;
import java.util.*;
//tutorial
public class E678{
   
   public static int n,np2;
   public static double[][] dp;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      np2 = (1 << n);
      
      matrix = new double[n][n];
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int j = 0; j < n; j++){
            matrix[k][j] = Double.parseDouble(st.nextToken());
         }
      }
      
      dp = new double[n][np2];               //dp[x][mask] = probability that player n wins if players 
      for(int k = 0; k < n; k++){
         Arrays.fill(dp[k],-1.0);
      }
      
      for(int k = 0; k < n; k++){
         dp[k][1 << k] = 1.0;
      }
      
      for(int mask = 0; mask < np2; mask++){
         for(int k = 0; k < n; k++){
            if(dp[k][mask] == 0.0) continue;
            
            //pick next player
            for(int j = 0; j < n; j++){
               if((mask & (1 << j)) != 0) continue;
               
            }
         }
      }
      
      
      
      
      out.close();
   }
   
   
      
}