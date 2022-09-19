//make sure to make new file!
import java.io.*;
import java.util.*;

public class A800{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         
         int lastn0 = -1;                    //last non-zero
         long sum = 0L;
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            sum += array[k];
            if(array[k] != 0) lastn0 = k;
         }
         
         if(sum != 0){
            out.println("No");
            continue;
         }
         
         if(lastn0 == -1){
            out.println("Yes");
            continue;
         }
         
         boolean fail = false;
         long psum = 0L;
         for(int k = 0; k < lastn0; k++){
            psum += array[k];
            if(psum <= 0){
               fail = true;
               break;
            }
         }
         
         if(fail){
            out.println("No");
         } else {
            out.println("Yes");
         }
         
         
         
         
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}