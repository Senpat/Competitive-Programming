//make sure to make new file!
import java.io.*;
import java.util.*;

public class B98{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());

         long[] array = new long[n];
         
         long sum = 0L;
         long max = 0L;
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            sum += array[k];
            max = Math.max(max,array[k]);
         }
         
         //get next multiple of n-1
         long mod = sum % (n-1);
         long curval = (sum+(n-2))/(n-1);
         
         long answer = -1L;
         if(max <= curval){
            answer = curval*(n-1)-sum;
         } else {
            answer = max*(n-1)-sum;
         }
         
         out.println(answer);
       
      

      }
      
      
      
      
      out.close();
   }
   
      
}