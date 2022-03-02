//make sure to make new file!
import java.io.*;
import java.util.*;

public class B{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(new FileWriter("B.out"));
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         char[][] board = new char[n][n];
         for(int k = 0; k < n; k++){
            String s = f.readLine();
            for(int j = 0; j < n; j++){
               board[k][j] = s.charAt(j);
            }
         }
         
         
         
         int max = n+1;
         int num = 0;
         
         for(int k = 0; k < n; k++){
            //try row
            int numx = 0;
            int numo = 0;
            for(int j = 0; j < n; j++){
               if(board[k][j] == 'X'){
                  numx++;
               } else if(board[k][j] == 'O'){
                  numo++;
                  break;
               }
            }
            
            if(numo == 0){
               int newmax = n-numx;
               if(newmax < max){
                  max = newmax;
                  num = 1;
               } else if(newmax == max){
                  num++;
               }
            }
            
            numx = 0;
            numo = 0;
            
            for(int j = 0; j < n; j++){
               if(board[j][k] == 'X'){
                  numx++;
               } else if(board[j][k] == 'O'){
                  numo++;
                  break;
               }
            }
            
            if(numo == 0){
               int newmax = n-numx;
               if(newmax < max){
                  max = newmax;
                  num = 1;
               } else if(newmax == max){
                  num++;
               }
            }
         }
         
         if(max == 1){
            for(int k = 0; k < n; k++){
               for(int j = 0; j < n; j++){
                  if(overcount(k,j,board)) num--;
               }
            }
         }
         if(max == 0) num = 1;
         
         if(max == n+1){
            out.println("Case #" + q + ": Impossible");
         } else {
            out.println("Case #" + q + ": " + max + " " + num);
         }

      }
      
      
      
      
      out.close();
   }
   
   public static boolean overcount(int x, int y, char[][] board){
      if(board[x][y] != '.') return false;
      
      for(int k = 0; k < board.length; k++){
         if(k == y) continue;
         if(board[x][k] != 'X') return false;
      }
      
      for(int k = 0; k < board.length; k++){
         if(k == x) continue;
         if(board[k][y] != 'X') return false;
      }
      
      return true;
   }
}