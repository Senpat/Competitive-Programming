//Three Pieces
import java.io.*;
import java.util.*;

public class D1065{

   public static int[][] board;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      board = new int[n][n
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int j = 0; j < n; j++){
            board[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      
      out.close();
   }
   
}