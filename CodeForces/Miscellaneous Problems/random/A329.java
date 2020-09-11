//make sure to make new file!
import java.io.*;
import java.util.*;

public class A329{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      char[][] board = new char[n][n];
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         
         for(int j = 0; j < n; j++){
            board[k][j] = s.charAt(j);
         }
      }
      
      StringJoiner sb = new StringJoiner("\n");
      
      for(int k = 0; k < n; k++){
         Point i = find(board,k);
         
         if(i.x == -1){
            out.println(-1);
            out.close();
            return;
         }
         
         sb.add("" + (i.x+1) + " " + (i.y+1));
      }
      
      out.println(sb);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static Point find (char[][] board, int row){
      for(int k = 0; k < board.length; k++){
         if(board[row][k] == '.') return new Point(row,k);
      }
      for(int k = 0; k < board.length; k++){
         if(board[k][row] == '.') return new Point(k,row);
      }
      
      return new Point(-1,-1);
   }
   
   public static class Point{
      int x;
      int y;
      public Point(int a, int b){
         x = a;
         y = b;
      }
   }
      
}