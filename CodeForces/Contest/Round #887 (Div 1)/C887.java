//make sure to make new file!
import java.io.*;
import java.util.*;
//in contest attempt
public class C887{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long m = Long.parseLong(st.nextToken());
         
         long[] array = new long[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            if(array[k] == m) array[k] = 0L;
         }
         
         //make all elements 0
         
         ArrayList<Long> a = new ArrayList<Long>();
         a.add(array[0]);
         for(int k = 1; k < n; k++){
            if(array[k] != array[k-1]){
               a.add(array[k]);
            }
         }
         
         int an = a.size();
         long answer = a.get(0);
         for(int k = 1; k < an; k++){
            answer += Math.max(0,a.get(k)-a.get(k-1));
         }
         
         //get max subtraction
         //0 -> don't add m, 1 -> add m
         long[][] dp = new long[an][2];
         for(int k = 0; k < an; k++){
            dp[k][0] = Long.MIN_VALUE;
            dp[k][1] = Long.MIN_VALUE;
         }
         dp[0][0] = 0L;
         dp[0][1] = -m;
         
         for(int k = 1; k < an; k++){
            long p0,p1;
            //don't add
            p0 = dp[k-1][0];
            p1 = dp[k-1][1] + Math.max(0L,a.get(k)-a.get(k-1));
            dp[k][0] = Math.max(p0,p1);
            
            //add
            p0 = dp[k-1][0] - (m - Math.max(0L,a.get(k-1) - a.get(k)));
            p1 = dp[k-1][1];
            dp[k][1] = Math.max(p0,p1);
         }
         
         answer -= Math.max(dp[an-1][0],dp[an-1][1]);
         out.println(answer);
         
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}