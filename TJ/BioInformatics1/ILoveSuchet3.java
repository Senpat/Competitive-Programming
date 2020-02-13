//make sure to make new file!
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
public class ILoveSuchet3{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      int n = Integer.parseInt(f.readLine());
      int m = Integer.parseInt(f.readLine());
      
      int[][] matrix = new int[n][n*2+1];
      
      matrix[0][1] = 2;
      matrix[0][2] = 1;
      matrix[0][0] = 1;
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j <= 2*n; j++){
            if(matrix[k][j] == 0)
               continue;
            matrix[k+1][j]+=matrix[k][j];
            matrix[k+1][j+2]+=matrix[k][j];
            matrix[k+1][j+1]+=2*matrix[k][j];
         }
      }
      
      double sum = 0.0;
      for(int k = 0; k <= 2*n; k++){
         sum += (double)matrix[n-1][k];
      }
      
      double answer = (double)matrix[n-1][m]/sum;
      out.println(String.format("%.10f", answer));
      
      
      
      
      
      out.close();
   }
   
      
}