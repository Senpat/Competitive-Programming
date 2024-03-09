//make sure to make new file!
import java.io.*;
import java.util.*;

public class ATC{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[2*n];
         for(int k = 0; k < 2*n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         Arrays.sort(array);
         
         long answer = 0L;
         for(int k = 0; k < 2*n; k+=2){
            answer += array[k];
         }
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}