//make sure to make new file!
import java.io.*;
import java.util.*;

public class F{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      double p = Double.parseDouble(st.nextToken())/100.0;
      double q = Double.parseDouble(st.nextToken())/100.0;
      
      double[] dp = new double[a+1];
      for(int k = 0; k < b; k++){
         dp[k] = 0.0;
      }
      
      for(int k = b; k <= a; k++){
         double o1 = dp[k-b] + 1.0 + p;
         double o2 = q*(dp[k-b+1] + 1.0) + (1.0-q) * (dp[k-b]+1.0);
         if(b == 1){
            o2 = (q + (1-q)*(dp[k-1]+1.0))/(1.0-q);
         }
         
         dp[k] = Math.max(o1,o2);
      }
      
      double answer = dp[a];
      out.println(answer);
      
      
      
      out.close();
   }
   
      
}