//make sure to make new file!
import java.io.*;
import java.util.*;

public class J{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      double nd = (double)n;
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] freq = new int[3];
      for(int k = 0; k < n; k++){
         int i = Integer.parseInt(st.nextToken());
         freq[i-1]++;
      }
      
      double[][][] dp = new double[n+1][n+1][n+1];    //dp[a][b][c] = answer if there are a 1s, b 2s, 3 cs (n-a-b-c 0s)
      dp[0][0][0] = 0.0;
      
      for(int c = 0; c <= n; c++){
         for(int b = 0; b <= n; b++){
            for(int a = 0; a <= n; a++){
               if(a+b+c == 0 || a+b+c > n) continue;
               
               double ad = (double)a;
               double bd = (double)b;
               double cd = (double)c;
               
               double sum = ad+bd+cd;
               
               //expected # of rolls to get to a non-0
               dp[a][b][c] = nd/sum;
               
               if(a-1 >= 0){
                  dp[a][b][c] += ad/sum * dp[a-1][b][c];
               }
               if(b-1 >= 0 && a+1 <= n){
                  dp[a][b][c] += bd/sum * dp[a+1][b-1][c];
               }
               if(c-1 >= 0 && b+1 <= n){
                  dp[a][b][c] += cd/sum * dp[a][b+1][c-1];
               }
            }
         }
      }
      
      
      out.println(dp[freq[0]][freq[1]][freq[2]]);
      
      
      
      
      
      out.close();
   }
   
      
}