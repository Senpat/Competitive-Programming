//make sure to make new file!
import java.io.*;
import java.util.*;

public class D317{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      long[] x = new long[n];
      long[] y = new long[n];
      int[] z = new int[n];
      
      ArrayList<Long> a = new ArrayList<Long>();
      ArrayList<Integer> b = new ArrayList<Integer>();
      
      int won = 0;
      
      int Z = 0;
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         x[k] = Long.parseLong(st.nextToken());
         y[k] = Long.parseLong(st.nextToken());
         z[k] = Integer.parseInt(st.nextToken());
         Z += z[k];
         
         if(x[k] > y[k]) won += z[k];
         else{
            //how many you need to switch to win
            a.add((x[k]+y[k]+1L)/2L - x[k]);
            b.add(z[k]);
         }
      }
      
      if(won >= (Z+1)/2){
         out.println(0);
         out.close();
         return;
      }
      
      int need = (Z+1)/2 - won;
      
      int n2 = a.size();
      
      long[][] dp = new long[n2][need+1];
      for(int k = 0; k < n2; k++){
         Arrays.fill(dp[k],Long.MAX_VALUE);
      }
      
      //a.size() > 1
      dp[0][0] = 0;
      dp[0][Math.min(need,b.get(0))] = a.get(0);
      
      for(int k = 0; k < n2-1; k++){
         for(int j = 0; j <= need; j++){
            if(dp[k][j] == Long.MAX_VALUE) continue;
            dp[k+1][j] = Math.min(dp[k+1][j],dp[k][j]);
            dp[k+1][Math.min(need,j+b.get(k+1))] = Math.min(dp[k+1][Math.min(need,j+b.get(k+1))], dp[k][j] + a.get(k+1));
         }
      }
      
      out.println(dp[n2-1][need]);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}