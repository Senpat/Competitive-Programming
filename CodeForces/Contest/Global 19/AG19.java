//make sure to make new file!
import java.io.*;
import java.util.*;

public class AG19{
   
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
         
         boolean sorted = true;
         for(int k = 1; k < n; k++){
            if(array[k] < array[k-1]) sorted = false;
         }
         
         if(sorted){
            out.println("NO");
         } else {
            out.println("YES");
         }
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}