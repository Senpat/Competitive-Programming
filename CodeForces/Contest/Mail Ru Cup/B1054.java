//make sure to make new file!
import java.io.*;
import java.util.*;

public class B1054{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++) array[k] = Integer.parseInt(st.nextToken());
      
      if(array[0] != 0){
         out.println(1);
         out.close();
         System.exit(0);
      }
      
      int max = 0;
      for(int k = 1; k < n; k++){
         if(array[k] <= max+1 && array[k] >= 0){
            max = Math.max(max,array[k]);
         } else {
            out.println(k+1);
            out.close();
            System.exit(0);
         }
      }
      
      out.println("-1");
      
      out.close();
   }
   
}