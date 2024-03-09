//make sure to make new file!
import java.io.*;
import java.util.*;

public class B169{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long s = Long.parseLong(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      int[] jump = new int[n];
      int l = 0;
      int r = 0;
      long sum = array[0];
      
      while(r < n){
         if(sum > s){
            jump[l] = r;
            sum -= array[l];
            l++;
         } else {
            r++;
            if(r < n){
               sum += array[r];
            }
         }
      }
      
      for(int k = l; k < n; k++){
         jump[k] = n;
      }
      
      long[] dp = new long[n+1];
      dp[n] = 0L;
      for(int k = n-1; k >= 0; k--){
         dp[k] = dp[jump[k]] + (long)(n-k);
      }
      
      long answer = 0L;
      for(int k = 0; k < n; k++){
         answer += dp[k];
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}