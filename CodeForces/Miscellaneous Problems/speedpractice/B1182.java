//make sure to make new file!
import java.io.*;
import java.util.*;
//not done
public class B1182{

   public static char[][] board;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      

      board = new char[n][m];
      
      int as = 0;
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
            if(board[k][j] == '*') as++;
         }
      }
      
      
      
      out.close();
   }
   
   //counts number of asterisks in the cross
   public static int countas(int x, int y){
      int count = 1;
      
      //left
      int i = x-1;
      while(i >= 0 && board[i][y] == '*'){
         i--;
         count++;
      }
      //right
      i = x+1;
      while(i < board.length && board[i][y] == '*'){
         i++;
         count++;
      }
      i = y-1;
      while(i >= 0 && board[x][i] == '*'){
         i--;
         count++;
      }
      i = y+1;
      while(i <board[0].length && board[x][i] == '*'){
         i++;
         count++;
      }
      return count;
   }
      
}