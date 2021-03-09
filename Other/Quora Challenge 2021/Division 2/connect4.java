//make sure to make new file!
import java.io.*;
import java.util.*;

public class connect4{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      int[][] board = new int[7][8];
      int[] numplayed = new int[8];
      Arrays.fill(numplayed,1);
      int token = 1;
      boolean found = false;
      for(int k = 1; k <= 42; k++){
         int i = Integer.parseInt(f.readLine());
         
         if(!found){
            board[numplayed[i]][i] = token;
            if(checkwin(board)){
               found = true;
               if(k%2 == 1) out.println("RED " + k);
               else out.println("YELLOW " + k);
            }
            numplayed[i]++;
            
            
            token = 3-token;
         }
      } 
      
      if(!found){
         out.println("DRAW");
      }
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean checkwin(int[][] board){
      //check horizontal
      for(int k = 1; k <= 6; k++){
         for(int j = 1; j <= 4; j++){
            if(board[k][j] != 0 && board[k][j] == board[k][j+1] && board[k][j] == board[k][j+2] && board[k][j] == board[k][j+3]) return true;
         }
      }
      
      //check vertical
      for(int k = 1; k <= 3; k++){
         for(int j = 1; j <= 7; j++){
            if(board[k][j] != 0 && board[k][j] == board[k+1][j] && board[k][j] == board[k+2][j] && board[k+3][j] == board[k][j]) return true;
         }
      }
      
      //check diagonal 1
      for(int k = 1; k <= 3; k++){
         for(int j = 1; j <= 4; j++){
            if(board[k][j] != 0 && board[k][j] == board[k+1][j+1] && board[k][j] == board[k+2][j+2] && board[k][j] == board[k+3][j+3]) return true;
         }
      }
      
      //check diagonal 2
      for(int k = 4; k <= 6; k++){
         for(int j = 1; j <= 4; j++){
            if(board[k][j] != 0 && board[k][j] == board[k-1][j+1] && board[k][j] == board[k-2][j+2] && board[k][j] == board[k-3][j+3]) return true;
         }
      }
      
      return false;
   }
   
      
}