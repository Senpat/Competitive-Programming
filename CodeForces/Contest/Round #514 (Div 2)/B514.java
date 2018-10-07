//make sure to make new file!
import java.io.*;
import java.util.*;

public class B514{

   public static char[][] board,newboard;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      board = new char[n][m];
      
      newboard = new char[n][m];
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
            newboard[k][j] = '.';
         }
      }
      
      
      
      for(int x = 1; x < n-1; x++){
         for(int y = 1; y < m-1; y++){
            //out.print(surround(x,y));
            if(surround(x,y)){
               newboard[x+1][y] = '#';
               newboard[x+1][y+1] = '#';
               newboard[x][y+1] = '#';
               newboard[x-1][y+1] = '#';
               newboard[x-1][y] = '#';
               newboard[x-1][y-1] = '#';
               newboard[x][y-1] = '#';
               newboard[x+1][y-1] = '#';
            }
         }
      }
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(newboard[k][j]!=board[k][j]){
               out.println("NO");
               out.close();
               System.exit(0);
            }
         }
      }
      
      out.print("YES");
      
      out.close();
   }
   
   public static boolean surround(int x, int y){
      return board[x+1][y] == '#' && board[x+1][y+1] == '#' && board[x][y+1] == '#' && board[x-1][y+1] == '#' && board[x-1][y] == '#' && board[x-1][y-1] == '#' && board[x][y-1] == '#' && board[x+1][y-1] == '#';
   }
}