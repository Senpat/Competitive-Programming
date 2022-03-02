//make sure to make new file!
import java.io.*;
import java.util.*;

public class B733{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[][] board = new int[n][m];
         
         //fill first and last row
         for(int k = 0; k < m; k+=2){
            board[0][k] = 1;
            board[n-1][k] = 1;
         }
         
         for(int k = 2; k < n-2; k+=2){
            board[k][0] = 1;
            board[k][m-1] = 1;
         }
         
         StringJoiner sj = new StringJoiner("\n");
         for(int k = 0; k < n; k++){
            StringJoiner sjrow = new StringJoiner("");
            for(int j = 0; j < m; j++){
               sjrow.add("" + board[k][j]);
            }
            sj.add(sjrow.toString());
         }
         
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}