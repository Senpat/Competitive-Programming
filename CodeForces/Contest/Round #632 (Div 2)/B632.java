//make sure to make new file!
import java.io.*;
import java.util.*;

public class B632{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
      
         int[] array1 = new int[n];
         int[] array2 = new int[n];
         
         for(int k = 0; k < n; k++){
            array1[k] = Integer.parseInt(st1.nextToken());
            array2[k] = Integer.parseInt(st2.nextToken());
         }
         
         if(check(array1,array2)){
            out.println("YES");
         } else {
            out.println("NO");
         }
            
      

      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(int[] array1, int[] array2){
      int n = array1.length;
      
      if(array1[0] != array2[0]) return false;
      
      boolean neg = false;
      boolean pos = false;
      
      if(array2[0] > 0) pos = true;
      if(array2[0] < 0) neg = true;
      if(array1[0] > 0) pos = true;
      if(array1[0] < 0) neg = true;
      
      for(int k =1 ; k < n; k++){
         if(array2[k] > array1[k] && !pos) return false;
         if(array2[k] < array1[k] && !neg) return false;
         
         if(array2[k] > 0) pos = true;
         if(array2[k] < 0) neg = true;
         if(array1[k] > 0) pos = true;
         if(array1[k] < 0) neg = true;
      }
      
      return true;
   }
      
      
}