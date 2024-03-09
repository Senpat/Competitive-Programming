//make sure to make new file!
import java.io.*;
import java.util.*;

public class B910{
   
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
         
         long answer = 0L;
         long prev = array[n-1];
         for(int k = n-2; k >= 0; k--){
            if(prev == 1){
               answer += array[k]-1;
            } else if(array[k] <= prev){
               prev = array[k];
            } else {
               //split array k as evenly as possible to be below prev
               long div = (array[k]-1)/prev;
               answer += div;
               prev = array[k]/(div+1);
            }
         }
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}