//make sure to make new file!
import java.io.*;
import java.util.*;

public class J{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         String[] array = new String[n];
         for(int k = 0; k < n; k++){
            array[k] = f.readLine();
         }
         
         int max = 0;
         for(int x1 = 0; x1 < n; x1++){
            for(int x2 = x1+1; x2 < n; x2++){
               int n1 = array[x1].length();
               int n2 = array[x2].length();
               
               int[][] dp = new int[n1+1][n2+1];
               for(int k = 0; k < n1; k++){
                  for(int j = 0; j < n2; j++){
                     if(array[x1].charAt(k) == array[x2].charAt(j)){
                        dp[k+1][j+1] = Math.max(dp[k+1][j+1],dp[k][j]+1);
                        max = Math.max(max,dp[k+1][j+1]);
                     }
                  }
               }
            }
         }
         
         out.println(max);

      }
      
      
      
      
      out.close();
   }
   
      
}