//make sure to make new file!
import java.io.*;
import java.util.*;

public class A764{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         int max = 0;
         int min = Integer.MAX_VALUE;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            max = Math.max(max,array[k]);
            min = Math.min(min,array[k]);
         }
         
         int answer = max-min;
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}