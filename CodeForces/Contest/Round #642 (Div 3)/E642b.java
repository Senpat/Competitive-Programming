//make sure to make new file!
import java.io.*;
import java.util.*;

public class E642b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         char[] carray = f.readLine().toCharArray();
         int[] array = new int[n];
         int total1 = 0;
         for(int k = 0; k < n; k++){
            array[k] = Character.getNumericValue(carray[k]);
            if(array[k] == 1) total1++;
         }
         
         int[] psum = new int[n+1];
         psum[0] = 0;
         
         for(int k = 1; k <= n; k++){
            psum[k] = psum[k-1]+array[k-1];
         }
         
         int min = total1;
         int[] dp = new int[n];
         //most optimal way to get prefix of length n with a[n] = 1
         
         for(int k = 0; k < m; k++){
            dp[k] = psum[k] + (1-array[k]);
            
         }
         
         for(int k = m; k < n; k++){
            //get before
            int before = dp[k-m] + (1-array[k]) + psum[k]-psum[k-m+1];
            //just make current 1
            int at = 1-array[k] + psum[k];
            
            dp[k] = Math.min(before,at);
         }
         
         for(int k = 0; k < n; k++){
            min = Math.min(min,dp[k] + psum[n]-psum[k+1]);
         }
         
         
         
         
         out.println(min);
         

      }
      
      
      
      
      out.close();
   }
   
      
}