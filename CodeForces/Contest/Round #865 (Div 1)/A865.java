//make sure to make new file!
import java.io.*;
import java.util.*;

public class A865{
   
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
         
         if(n%2 == 1){
            out.println("YES");
            continue;
         }
         
         boolean fail = false;
         int i = n-1;
         while(i >= 1){
            //make array[i] and array[i-1] infinity
            int diff = array[i-1]-array[i];
            array[i] = Integer.MAX_VALUE;
            array[i-1] = Integer.MAX_VALUE;
            if(diff > 0){
               if(i-2 >= 0){
                  array[i-2] -= diff;
               } else {
                  fail = true;
               }
            }
            i -= 2;
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