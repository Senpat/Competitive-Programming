//make sure to make new file!
import java.io.*;
import java.util.*;

public class CSA{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      Integer[] array = new Integer[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      Arrays.sort(array);
      
      if(array[0] != 1){
      
      int sum = 0;
      int canadd = 0;
      for(int k = 0; k < n; k++){
         if((int)array[k] <= (int)array[0]+1){
            sum += array[k]-1;
         }
         
         if((int)array[k] != 1){
            canadd++;
         }
      }
      
      if(sum >= canadd){
         out.println("possible");
      } else {
         out.println("impossible");
      }
      
      } else {
      
         int num2 = 0;
         int numx = 0;
         for(int k = 0; k < n; k++){
            if(array[k] == 2) num2++;
            if(array[k] > 2) numx++;
         }
         
         if(numx > num2){
            out.println("impossible");
         } else {
            out.println("possible");
         }
      
      }
      
      
      
      
      
      
      out.close();
   }
   
      
}