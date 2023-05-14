//make sure to make new file!
import java.io.*;
import java.util.*;

public class A862{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         int xor = 0;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            xor ^= array[k];
         }
         
         if(n%2 == 0){
            if(xor == 0) out.println(123);
            else out.println(-1);
         } else {
            out.println(xor);
         }
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}