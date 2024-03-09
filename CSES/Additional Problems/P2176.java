//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2176{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      if(n == 1){
         if(m <= 1){
            out.println("1");
         } else {
            out.println("0");
         }
         out.close();
         return;
      }
      
      long[][] dpodd = new long[n][n+1];
      dpodd[0][0] = 1L;
      dpodd[0][1] = 1L;
      
      for(int k = 1; k < n; k++){
         long num = (long)(k + (k+1)%2);
         //how many previously existing bishops
         for(int j = 0; j < n; j++){
            if(dpodd[k-1][j] == 0L) continue;
            //don't add bishop
            dpodd[k][j] = (dpodd[k][j] + dpodd[k-1][j] + MOD)%MOD;
            //add bishop in diagonal
            dpodd[k][j+1] = (dpodd[k][j+1] + dpodd[k-1][j] * (num - j) + MOD)%MOD;
         }
      }
      
      long[][] dpeven = new long[n-1][n+1];
      dpeven[0][0] = 1L;
      dpeven[0][1] = 2L;
      for(int k = 1; k < n-1; k++){
         long num = (long)(k+1);
         if(k%2 == 0) num++;
         for(int j = 0; j < n; j++){
            if(dpeven[k-1][j] == 0L) continue;
            dpeven[k][j] = (dpeven[k][j] + dpeven[k-1][j] + MOD)%MOD;
            dpeven[k][j+1] = (dpeven[k][j+1] + dpeven[k-1][j] * (num-j) + MOD)%MOD;
         }
      }
      
      long answer = 0L;
      for(int k = 0; k <= m; k++){
         if(k > n || m-k > n) continue;
         answer = (answer + dpodd[n-1][k] * dpeven[n-2][m-k] + MOD)%MOD;
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}