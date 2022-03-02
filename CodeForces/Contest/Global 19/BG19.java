//make sure to make new file!
import java.io.*;
import java.util.*;

public class BG19{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         long l = 1L;
         long r = (long)n;
         
         long answer = 0;
         for(int k = 0; k < n; k++){
            if(array[k] == 0){
               answer += l*r*2L;
            } else {
               answer += l*r;
            }
            l++;
            r--;
         }
         
         out.println(answer);
   
      }
      
      
      
      
      out.close();
   }
   
      
}