//make sure to make new file!
import java.io.*;
import java.util.*;

public class D103{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         char[] array = f.readLine().toCharArray();
         
         int[] right = new int[n+1];
         int[] left = new int[n+1];
         
         right[n] = 0;
         if(array[n-1] == 'R') right[n-1] = 1;
         else right[n-1] = 0;
         
         for(int k = n-2; k >= 0; k--){
            if(array[k] == 'R' && array[k+1] == 'L'){
               right[k] = right[k+2]+2;
            } else if(array[k] == 'R'){
               right[k] = 1;
            } else {
               right[k] = 0;
            }
         }
         
         left[0] = 0;
         if(array[0] == 'L') left[1] = 1;
         else left[1] = 0;
         
         for(int k = 2; k <= n; k++){
            if(array[k-1] == 'L' && array[k-2] == 'R'){
               left[k] = left[k-2]+2;
            } else if(array[k-1] == 'L'){
               left[k] = 1;
            } else {
               left[k] = 0;
            }
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k <= n; k++){
            sj.add("" + (left[k]+right[k]+1));
         }
         out.println(sj.toString());
      

      }
      
      
      
      
      out.close();
   }
   
      
}