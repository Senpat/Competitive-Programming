//make sure to make new file!
import java.io.*;
import java.util.*;

public class N{
   
   public static int n = 50;
   public static char[][] board;
   public static void main(String[] args)throws IOException{
      //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader f = new BufferedReader(new FileReader("t.in"));
      PrintWriter out = new PrintWriter(System.out);
      
      
      board = new char[n][n];
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         if(s.length() == 0) continue;
         for(int j = 0; j < n; j++){
            
            board[k][j] = s.charAt(j);
            
         }
      }
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            if(check(k,j)){
               out.println("1");
               out.close();
               System.exit(0);
            }
         }
      }
   
      
      out.println("0");
      
      
      out.close();
   }
   
   public static boolean check(int x, int y){
      if(x <= n-5 && y <= n-5){
         //diagonal
         int acount = 0;
         int dcount = 0;
         for(int k = 0; k < 5; k++){
            if(board[x+k][y+k] == 'A') acount++;
            if(board[x+k][y+k] == '.') dcount++;
         }
         
         if(acount == 4 && dcount == 1) return true;
         if(acount == 5) return true;
      }
      if(x <= n-5){
         int acount = 0;
         int dcount = 0;
         for(int k = 0; k < 5; k++){
            if(board[x+k][y] == 'A') acount++;
            if(board[x+k][y] == '.') dcount++;
         }
         if(acount == 4 && dcount == 1) return true;
         if(acount == 5) return true;
      }
      if(y <= n-5){
         int acount = 0;
         int dcount = 0;
         for(int k = 0; k < 5; k++){
            if(board[x][y+k] == 'A') acount++;
            if(board[x][y+k] == '.') dcount++;
         }
         
         if(acount == 4 && dcount == 1) return true;
         if(acount == 5) return true;
      }
      if(y >= 4 && x <= n-5){
         int acount = 0;
         int dcount = 0;
         for(int k = 0; k < 5; k++){
            if(board[x+k][y-k] == 'A') acount++;
            if(board[x+k][y-k] == '.') dcount++;
         }
         
         if(acount == 4 && dcount == 1) return true;
         if(acount == 5) return true;
      }
      return false;
   }
            
      
}