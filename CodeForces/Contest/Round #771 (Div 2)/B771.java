//make sure to make new file!
import java.io.*;
import java.util.*;

public class B771{
   
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
         
         int lasteven = -1;
         int lastodd = -1;
         
         boolean fail = false;
         
         for(int k = 0; k < n; k++){
            if(array[k] % 2 == 0){
               if(lasteven > array[k]){
                  fail = true;
                  break;
               }
               lasteven = array[k];
            } else {
               if(lastodd > array[k]){
                  fail = true;
                  break;
               }
               lastodd = array[k];
            }
         }
         
         if(fail){
            out.println("NO");
         } else {
            out.println("YES");
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}