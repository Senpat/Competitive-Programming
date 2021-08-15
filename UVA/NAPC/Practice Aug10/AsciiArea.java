//ASCII Area
import java.io.*;
import java.util.*;

public class AsciiArea{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      char[][] board = new char[n][m];
      
      int numslash = 0;
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
            if(board[k][j] != '.') numslash++;
         }
      }
      
      int numdot = 0;
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(board[k][j] != '.') continue;
            //left
            int i = 0;
            for(int h = 0; h < k; h++){
               if(board[h][j] != '.') i++;
            }
            if(i % 2 == 0) continue;
            //right
            i = 0;
            for(int h = k+1; h < n; h++){
               if(board[h][j] != '.') i++;
            }
            if(i % 2 == 0) continue;
            //up
            i = 0;
            for(int h = 0; h < j; h++){
               if(board[k][h] != '.') i++;
            }
            if(i % 2 == 0) continue;
            //down
            i = 0;
            for(int h = j+1; h < m; h++){
               if(board[k][h] != '.') i++;
            }
            if(i % 2 == 0) continue;
            numdot++;
         }
      }
      
      int answer = numdot + numslash/2;
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
      
}