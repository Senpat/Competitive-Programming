//make sure to make new file!
import java.io.*;
import java.util.*;

public class COVIDLQ{
   
   public static void main(String[] args)throws java.lang.Exception{
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
         
         if(check(array)){
            out.println("YES");
         } else {
            out.println("NO");
         }
         
      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(int[] array){
      
      for(int k = 0; k < array.length; k++){
         if(array[k] == 1){
            for(int j = 1; j < 6; j++){
               if(k+j < array.length && array[k+j] == 1) return false;
            }
         }
      }
      
      return true;
   }
}