//make sure to make new file!
import java.io.*;
import java.util.*;

public class BH19{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] array = new int[n];
      
      int sum = 0;
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(f.readLine());
         sum+=array[k];
      }
      
      
      for(int k = 0; k < (int)Math.pow(2,n); k++){
         String bin = Integer.toBinaryString(k);
         int cursum = 0;
         
         int bl = bin.length();
         for(int j = 0; j < n && j < bl; j++){
            if(bin.charAt(bl-1-j) == '1'){
               cursum += array[n-j-1];
            } else {
               cursum -= array[n-j-1];
            }
         }
         
         for(int j = 0; j < n-bl; j++){
            cursum-=array[j];
         }
         
         if(cursum % 360 == 0){
            out.println("YES");
            out.close();
            System.exit(0);
         }
      }
      
      out.println("NO");
      
      
      
      out.close();
   }
   
}