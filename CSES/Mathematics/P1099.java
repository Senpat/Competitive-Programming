//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1099{
   
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
            if(k%2 == 1) xor ^= array[k];
         }
         
         //basically nim with only odd stairs
         //all tokens on even stairs are a losing state
         //if player moves an even stair (to odd stair), just move those again to an even stair
         //if player moves an odd stair, move another odd stair based on nim
         
         if(xor == 0){
            out.println("second");
         } else {
            out.println("first");
         }
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}