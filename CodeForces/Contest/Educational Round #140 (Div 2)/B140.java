//make sure to make new file!
import java.io.*;
import java.util.*;

public class B140{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         long i = Long.parseLong(st.nextToken());        
         Long[] array = new Long[n-1];
         for(int k = 0; k < n-1; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         Arrays.sort(array);
         
         for(int k = 0; k < n-1; k++){
            if(array[k] <= i) continue;
            i += (array[k]-i+1)/2L;
         }
         
         out.println(i);
         
         

      }
      
      
      
      
      out.close();
   }
   
      
}