//make sure to make new file!
import java.io.*;
import java.util.*;

public class A579{
   
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
         
         if(checkcw(array) || checkccw(array)){
            out.println("YES");
         } else {
            out.println("NO");
         }

      }
      
      
      
      
      out.close();
   }
   
   public static boolean checkcw(int[] array){
      
      for(int k = 1; k < array.length; k++){
         if(array[k] == 1){
            if(array[k-1] != array.length) return false;
         } else {
            if(array[k-1] != array[k]-1) return false;
         }
      }
      return true;
   }
   
   
   public static boolean checkccw(int[] array){
      
      for(int k = 1; k < array.length; k++){
         if(array[k] == array.length){
            if(array[k-1] != 1) return false;
         } else {
            if(array[k-1] != array[k]+1) return false;
         }
      }
      return true;
   }
      
}