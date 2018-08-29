//Writing Code
import java.io.*;
import java.util.*;

public class A543{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int mod = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
 
      st = new StringTokenizer(f.readLine());     
      for(int k = 0; k < n; k++) array[k] = Integer.parseInt(st.nextToken());
      
      int[][][] dp = new int[m][n][b+1];
      
      for(int k = 0; k < n; k++){                        //start with every programmer
         dp[0][k][array[k]] = 1;
      }
      
      for(int k = 0; k < m; k++){
         for(int j = 0; j < n; j++){
            for(int h = 0; h < b+1; h++){
               if(dp[k][j][h] > 0){ 
                  for(int x = j; x < n; x++){                     
                     if(in(dp,k+1,x,h+array[x])) dp[k+1][x][h+array[x]] = (dp[k+1][x][h+array[x]] + dp[k][j][h])%mod;          //add all possible programmers
                  }  
               }
            }
         }
      }
      
      int answer = 0;
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j <= b; j++){
            answer = (answer + dp[m-1][k][j])%mod;
         }
      }
                  
      out.println(answer);
      
      out.close();
   }
   
   public static boolean in(int[][][] d, int a, int b, int c){
      return a < d.length && b < d[0].length && c < d[0][0].length;
   }
   
}