//make sure to make new file!
import java.io.*;
import java.util.*;

public class CG19{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         boolean all1 = true;
         long sum = 0L;
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            if(k != 0 && k != n-1 && array[k] != 1){
               all1 = false;
            }
            if(k != 0 && k != n-1){
               sum += (array[k]+1)/2L;
            }
            
         }
         
         if(n == 3 && array[1] % 2 == 1){
            out.println(-1);
         } else if(all1){
            out.println(-1);
         } else {
            out.println(sum);
         }
         
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}