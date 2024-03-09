//make sure to make new file!
import java.io.*;
import java.util.*;

public class C912{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         long[] sufsum = new long[n];
         sufsum[n-1] = array[n-1];
         for(int k = n-2; k >= 0; k--){
            sufsum[k] = array[k] + sufsum[k+1];
         }
         
         long answer = 0L;
         for(int k = n-1; k > 0; k--){
            if(sufsum[k] > 0) answer += sufsum[k];
         }
         answer += sufsum[0];
         
         out.println(answer);
      
      }
      
      
      
      
      out.close();
   }
   
      
}