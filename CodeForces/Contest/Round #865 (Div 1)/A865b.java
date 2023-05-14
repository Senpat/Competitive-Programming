//make sure to make new file!
import java.io.*;
import java.util.*;

public class A865b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long[] array = new long[n];
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         if(n%2 == 1){
            out.println("YES");
            continue;
         }
         
         for(int k = n-1; k >= 2; k-=2){
            //array[k] and array[k-1] go to infinity, adjust array[k-2] if needed
            long diff = array[k] - array[k-1];
            array[k-2] += diff;
         }
         
         if(array[0] > array[1]){
            out.println("NO");
         } else {
            out.println("YES");
         }

      }
      
      
      
      
      out.close();
   }
   
      
}