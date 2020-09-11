//make sure to make new file!
import java.io.*;
import java.util.*;

public class D662{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      char[][] board = new char[n][m];
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
         }
      }
      
      //upperleft
      
      int[][] upperleft = new int[n][m];
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(k == 0 || j == 0) upperleft[k][j] = 1;
            else {
               if(board[k-1][j] == board[k][j] && board[k][j-1] == board[k][j]){
                  upperleft[k][j] = Math.min(upperleft[k-1][j],upperleft[k][j-1]) + 1;
               } else {
                  upperleft[k][j] = 1;
               }
            }
         }
      }
      
      //upper right
      
      int[][] upperright = new int[n][m];
      
      for(int k = 0; k < n; k++){
         for(int j = m-1; j >= 0; j--){
            if(k == 0 || j == m-1) upperright[k][j] = 1;
            else {
               if(board[k-1][j] == board[k][j] && board[k][j+1] == board[k][j]){
                  upperright[k][j] = Math.min(upperright[k-1][j],upperright[k][j+1]) + 1;
               } else {
                  upperright[k][j] = 1;
               }
            }
         }
      }
      
      //down left
      
      int[][] downleft = new int[n][m];
      
      for(int k = n-1; k >= 0; k--){
         for(int j = 0; j < m; j++){
            if(k == n-1 || j == 0) downleft[k][j] = 1;
            else {
               if(board[k+1][j] == board[k][j] && board[k][j-1] == board[k][j]){
                  downleft[k][j] = Math.min(downleft[k+1][j],downleft[k][j-1]) + 1;
               } else {
                  downleft[k][j] = 1;
               }
            }
         }
      }
      
      //down right
      
      int[][] downright = new int[n][m];
      
      for(int k = n-1; k >= 0; k--){
         for(int j = m-1; j >= 0; j--){
            if(k == n-1 || j == m-1) downright[k][j] = 1;
            else {
               if(board[k+1][j] == board[k][j] && board[k][j+1] == board[k][j]){
                  downright[k][j] = Math.min(downright[k+1][j],downright[k][j+1]) + 1;
               } else {
                  downright[k][j] = 1;
               }
            }
         }
      }
      
      
      long answer = 0L;
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            answer += (long)(Math.min(Math.min(upperleft[k][j],upperright[k][j]),Math.min(downleft[k][j],downright[k][j])));
         }
      }
      
      out.println(answer);
      
      
      
      
      
      out.close();
   }
   
      
}