//make sure to make new file!
import java.io.*;
import java.util.*;

public class CG7{
   
   public static long MOD = 998244353;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      long answer = 0L;
      for(long k = n; k > n-m; k--){
         answer += k;
      }
      
      long answer2 = 1L;
      int start = -1;
      for(int k = 0; k < n; k++){
         if(array[k] > n-m){
            if(start == -1){
               start = k;
            } else {
               answer2 = (answer2*(k-start)+MOD)%MOD;
               start = k;
            }
         }
      }
      
      out.println(answer + " " + answer2);
      
      
      

      
      
      
      
      
      out.close();
   }
   
      
}