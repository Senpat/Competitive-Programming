//make sure to make new file!
import java.io.*;
import java.util.*;
//previous dp wrong logic
public class F229{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer sta = new StringTokenizer(f.readLine());
      StringTokenizer stb = new StringTokenizer(f.readLine());
      
      long[] a = new long[n];
      long[] b = new long[n];
      for(int k = 0; k < n; k++){
         a[k] = Long.parseLong(sta.nextToken());
         b[k] = Long.parseLong(stb.nextToken());
      }
      
      //node 0 is class 0, set all other nodes to class 0 or 1
      //too many class 0 in a row leads to discontinuous graph, but don't need to check (guaranteed to be unoptimal)
      
      //dp[x][y] -> first x nodes, xth node is class y
      //node 1 is class 0
      long[][] dp0 = new long[n][2];
      //node 1 is class 1
      long[][] dp1 = new long[n][2];  
      
      dp0[1][0] = a[0] + a[1] + b[0];
      dp0[1][1] = a[0];
      dp1[1][0] = a[1];
      dp1[1][1] = b[0];
      
      for(int k = 2; k < n; k++){
         dp0[k][0] = a[k] + Math.min(dp0[k-1][0] + b[k-1],dp0[k-1][1]);
         dp0[k][1] = Math.min(dp0[k-1][0],dp0[k-1][1] + b[k-1]);
         
         dp1[k][0] = a[k] + Math.min(dp1[k-1][0] + b[k-1],dp1[k-1][1]);
         dp1[k][1] = Math.min(dp1[k-1][0],dp1[k-1][1] + b[k-1]);
      }
      
      long a0 = Math.min(dp0[n-1][0] + b[n-1],dp0[n-1][1]);
      long a1 = Math.min(dp1[n-1][0],dp1[n-1][1] + b[n-1]);
      
      long answer = Math.min(a0,a1);
      out.println(answer);
      
      
      
      out.close();
   }
   
      
}