//make sure to make new file!
import java.io.*;
import java.util.*;

public class flipped{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      double[][] matrix = new double[n][m];
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < m; j++){
            matrix[k][j] = Double.parseDouble(st.nextToken());
         }
      }
      
      for(int k = 1; k <= b; k++){
         out.println(k);
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}