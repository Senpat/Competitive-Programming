//make sure to make new file!
import java.io.*;
import java.util.*;

public class D870{
   
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
         
         long[] left = new long[n];
         long[] right = new long[n];
         
         long max = array[0];
         for(int k = 1; k < n; k++){
            left[k] = max-1;
            max = Math.max(max-1,array[k]);
         }
         
         max = array[n-1];
         for(int k = n-2; k >= 0; k--){
            right[k] = max-1;
            max = Math.max(max-1,array[k]);
         }
         
         long answer = 0;
         for(int k = 1; k < n-1; k++){
            answer = Math.max(answer,left[k] + array[k] + right[k]);
         }
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}