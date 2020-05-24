//make sure to make new file!
import java.io.*;
import java.util.*;

public class C637{
   
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
         
         if(check(array)){
            out.println("Yes");
         } else {
            out.println("No");
         }
      

      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(int[] array){
      int n = array.length;
      for(int k = 0; k < n-1; k++){
         if(array[k+1] > array[k]+1) return false;
      }
      return true;
   }
   
      
}