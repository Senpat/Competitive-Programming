//make sure to make new file!
import java.io.*;
import java.util.*;

public class D337{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      
      char[][] board1 = new char[n][m];
      char[][] board2 = new char[m][n];
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board1[k][j] = s.charAt(j);
            board2[j][k] = s.charAt(j);
         }
      }
      
      int answer = Math.min(calc(board1,x),calc(board2,x));
      
      if(answer == Integer.MAX_VALUE){
         out.println("-1");
      } else {
         out.println(answer);
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int calc(char[][] board, int x){
      int n = board.length;
      int m = board[0].length;
      
      if(x > m) return Integer.MAX_VALUE;
      
      int answer = Integer.MAX_VALUE;
      for(int row = 0; row < n; row++){
         //get first m
         int osum = 0;
         int xsum = 0;
         
         for(int k = 0; k < x; k++){
            if(board[row][k] == 'o') osum++;
            else if(board[row][k] == 'x') xsum++;
         }
         
         if(xsum == 0) answer = Math.min(answer,x-osum);
         
         for(int k = x; k < m; k++){
            if(board[row][k] == 'o') osum++;
            else if(board[row][k] == 'x') xsum++;
            
            if(board[row][k-x] == 'o') osum--;
            else if(board[row][k-x] == 'x') xsum--;
            
            if(xsum == 0) answer = Math.min(answer,x-osum);
         }
      }
      
      return answer;
      
   }
      
}