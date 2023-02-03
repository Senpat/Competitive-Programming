//make sure to make new file!
import java.io.*;
import java.util.*;

public class BPR1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
         
         int[] array = new int[n];
         StringTokenizer st = new StringTokenizer(f.readLine());
         boolean notcycle = false;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            
            if(k >= 2){
               if(array[k-2] != array[k]) notcycle = true;
            }
         }
         
         if(n > 2){
            if(array[0] != array[n-2]) notcycle = true;
            if(array[1] != array[n-1]) notcycle = true;
         }
         
         if(notcycle){
            out.println(n);
         } else {
            out.println(n/2+1);
         }
         
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}