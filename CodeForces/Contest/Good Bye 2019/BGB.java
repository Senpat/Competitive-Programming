//make sure to make new file!
import java.io.*;
import java.util.*;

public class BGB{
   
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
         
         boolean found = false;
         for(int k = 0; k < n-1; k++){
            if(Math.abs(array[k]-array[k+1]) >= 2){
               found = true;
               out.println("YES");
               out.println((k+1) + " " + (k+2));
            
               break;
            }
         }
         
         if(!found){
            out.println("NO");
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}