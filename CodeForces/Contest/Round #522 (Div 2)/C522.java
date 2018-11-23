//make sure to make new file!
import java.io.*;
import java.util.*;

public class C522{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] array = new int[n];
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[][] dp = new int[n][6];
      
      for(int k = 1; k <= 5; k++){
         dp[0][k] = -1;
      }
      
      for(int k = 0; k < n-1; k++){
         for(int j = 1; j <= 5; j++){
            if(dp[k][j] == 0) continue;
            if(array[k+1] == array[k]){
               for(int h = 1; h <= 5; h++){
                  if(h != j){
                     dp[k+1][h] = j;
                  }
               }     
            } else if(array[k+1] > array[k]){
               for(int h = j+1; h <= 5; h++){
                  dp[k+1][h] = j;
               }
            } else {
               for(int h = 0; h < j; h++){
                  dp[k+1][h] = j;
               }
            }
         }
      }
      
      StringBuilder answer = new StringBuilder("");
      
      boolean b = false;
      for(int k = 1; k <= 5; k++){
         if(dp[n-1][k] != 0){
            answer.append(k);
            int last = k;
            for(int j = n-1; j > 0; j--){
               last = dp[j][last];
               answer.append( " " + last);
            }
            //answer.append(" " + last);
            out.println(answer.reverse());
            b = true;
            break;
         }
      }
      
      if(!b) out.println(-1);
      
      out.close();
   }
   
}