//make sure to make new file!
import java.io.*;
import java.util.*;

public class Edebug{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[][] board = new int[n][m];
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = Character.getNumericValue(s.charAt(j));
         }
      }
      
      int[][] odd = new int[n][m];
      int[][] even = new int[n][m];
      
      //col
      for(int k = 0; k < n; k++){
         for(int j = k+1; j < n; j++){
            int d = j-k+1;
            for(int h = 0; h+d-1 < n; h++){
               
            }
         }
      }
      
      
      
      
      
      
      out.close();
   }
   
      
}