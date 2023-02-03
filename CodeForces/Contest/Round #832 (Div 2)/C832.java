//make sure to make new file!
import java.io.*;
import java.util.*;

public class C832{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int min = Integer.MAX_VALUE;
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            min = Math.min(min,array[k]);
         }
         
         if(array[0] == min){
            out.println("Bob");
         } else {
            out.println("Alice");
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}