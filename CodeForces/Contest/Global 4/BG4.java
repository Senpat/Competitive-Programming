//make sure to make new file!
import java.io.*;
import java.util.*;

public class BG4{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] c = f.readLine().toCharArray();
      int n = c.length;
      long[] array = new long[n];
      
      int curlength = 0;
      
      for(int k = 0; k < n; k++){
         if(c[k] == 'v'){
            curlength++;
         } else {
            if(k > 0){
               array[k-1] = (long)Math.max(0,curlength-1);
               curlength = 0;
            }
         }
      }
      
      array[n-1] = (long)Math.max(0,curlength-1);
      
      long[] psums = new long[n];
      psums[0] = array[0];
      for(int k = 1; k < n; k++){
         psums[k] = psums[k-1] + array[k];
      }
      
      long answer = 0L;
      
      for(int k = 0; k < n; k++){
         if(c[k] == 'o'){
            //num left * num right
            answer += psums[k] * (psums[n-1]-psums[k]);
         }
      }
      
      out.println(answer);
      

      
      
      
      
      out.close();
   }
   
      
}