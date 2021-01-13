//make sure to make new file!
import java.io.*;
import java.util.*;

public class A681{
   
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
         
         boolean fail = false;
         int subtract = 0;
         for(int k = 1; k < n; k++){
            if(array[k] > array[k-1]){
               subtract += array[k]-array[k-1];
            }
            if(array[k]-subtract < 0) fail = true;
         }
         
         if(!fail){
            out.println("YES");
         } else {
            out.println("NO");
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}