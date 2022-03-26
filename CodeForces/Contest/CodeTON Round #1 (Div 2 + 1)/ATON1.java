//make sure to make new file!
import java.io.*;
import java.util.*;

public class ATON1{
   
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
         
         int maxi = 0;
         int mini = 0;
         
         for(int k = 0; k < n; k++){
            if(array[k] > array[maxi]) maxi = k;
            if(array[k] < array[mini]) mini = k;
         }
         
         out.println((maxi+1) + " " + (mini+1));
      

      }
      
      
      
      
      out.close();
   }
   
      
}