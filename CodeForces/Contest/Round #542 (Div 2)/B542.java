//make sure to make new file!
import java.io.*;
import java.util.*;

public class B542{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[][] matrix = new int[n+1][2];
      for(int k = 0; k <= n; k++){
         matrix[k][0] = -1;
         matrix[k][1] = -1;
      }
      
      
      int[] array = new int[2*n];
      
      for(int k = 0; k < 2*n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         if(matrix[array[k]][0] == -1){
            matrix[array[k]][0] = k;
         } else {
            matrix[array[k]][1] = k;
         }
      }
      
      
      long count = 0L;
      
      count += matrix[1][0] + matrix[1][1];
      
      for(int k = 2; k <= n; k++){
         count += Math.min( Math.abs(matrix[k-1][0] - matrix[k][0]) + Math.abs(matrix[k-1][1] - matrix[k][1]) , Math.abs( matrix[k-1][0] - matrix[k][1]) + Math.abs(matrix[k-1][1] - matrix[k][0]) );
      }
      
      out.println(count);
      

      
      
      
      
      out.close();
   }
   
      
}