//Soldier and Number Game
//slow
import java.io.*;
import java.util.*;

public class D546{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[][] queries = new int[n][2];
      
      int max = 0;
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         queries[k][0] = Integer.parseInt(st.nextToken());
         queries[k][1] = Integer.parseInt(st.nextToken());
         max = Math.max(max, queries[k][0]);
      }
      
      int[] dp = new int[max+1];
      
      dp[1] = 1;
      
      for(int k = 2; k <= max; k++){
         if(prime(k)) dp[k] = 1;
         else {
            int count = 0;
            int cur = k;
            while(bigd(cur)>1){
               int b = bigd(cur);
               cur/=bigd(cur);
               count+=dp[b];
            }
            dp[k] = count+1;
         }
      }
      
      int[] pref = new int[dp.length];
      for(int k = 1; k < dp.length; k++){
         pref[k] = pref[k-1] + dp[k];
      }
      
      for(int k = 0; k < n; k++){
         out.println(pref[queries[k][0]] - pref[queries[k][1]]);
      }
      
      
      out.close();
   
   }
   
   
   public static boolean prime(int i){
      for(int k = 2; k <= Math.sqrt(i); k++){
         if(i%k==0) 
            return false;
      }
      return true;
   }
   
   public static int bigd(int i){
      for(int k = (int)Math.sqrt(i); k >= 1; k--){
         if(i%k==0) 
            return k;
      }
      return 1; //shouldn't happen
   }
   
}