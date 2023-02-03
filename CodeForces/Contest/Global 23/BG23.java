//make sure to make new file!
import java.io.*;
import java.util.*;

public class BG23{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         int t0 = 0;
         int t1 = 0;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(array[k] == 0) t0++;
            else t1++;
         }
         
         int right0 = 0;
         int left1 = t1;
         
         int min = t1;
         for(int k = n-1; k >= 0; k--){
            if(array[k] == 0){
               right0++;
            } else {
               left1--;
            }
            
            min = Math.min(min,Math.max(right0,left1));
         }
         
         
         
         out.println(min);
      

      }
      
      
      
      
      out.close();
   }
   
      
}