//make sure to make new file!
import java.io.*;
import java.util.*;

public class B666{
   
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
         
         Arrays.sort(array);
         
         if(n == 1){
            out.println("T");
         } else if(n == 2){
            if(array[0] == array[1]) out.println("HL");
            else out.println("T");
         } else {
         
            
            int sum = 0;
            int max = array[n-1];
            for(int k = 0; k < n-1; k++){
               sum += array[k];
            }
            
            if(sum < max){
               out.println("T");
            } else if ((sum-max)%2 == 1){
               out.println("T");
            } else {
               out.println("HL");
            }
            
         }

      }
      
      
      
      
      out.close();
   }
   
      
}