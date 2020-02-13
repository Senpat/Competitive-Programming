//make sure to make new file!
import java.io.*;
import java.util.*;

public class A600{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array1 = new int[n];
         for(int k = 0; k < n; k++){
            array1[k] = Integer.parseInt(st.nextToken());
         }
         
         st = new StringTokenizer(f.readLine());
         int[] array2 = new int[n];
         for(int k = 0; k < n; k++){
            array2[k] = Integer.parseInt(st.nextToken());
         }

         if(check(array1,array2)){
            out.println("YES");
         } else {
            out.println("NO");
         }

      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(int[] a, int[] b){
      boolean started = false;
      boolean stopped = false;
      int dif = 0;
      int n = a.length;
      for(int k = 0; k < n; k++){
         if(a[k] > b[k]) return false;
         
         if(stopped && a[k] < b[k]) return false;
         else if(started && !stopped){
            if(a[k] == b[k]) stopped = true;
            else{
               if(dif != b[k]-a[k]) return false;
            }
         } else if(!started && a[k] < b[k]){
            started = true;
            dif = b[k]-a[k];
         }
      }
      
      return true;
   }
   
      
}