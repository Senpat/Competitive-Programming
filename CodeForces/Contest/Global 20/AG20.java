//make sure to make new file!
import java.io.*;
import java.util.*;

public class AG20{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         int sum = 0;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            sum += array[k]-1;
         }
         
         if(sum%2 == 0){
            out.println("maomao90");
         } else {
            out.println("errorgorn");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}