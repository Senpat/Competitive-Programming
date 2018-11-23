//make sure to make new file!
import java.io.*;
import java.util.*;

public class H{
   
   public static int[][] board;
   public static boolean[][] seen;
   public static int count;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      
      board = new int[n][n];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < n; j++){
            board[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      seen = new boolean[n][n];
      count = 0;
      
      dfs(x,y);
      
      out.println(count);
      
      
      out.close();
   }
   
   public static void dfs(int x, int y){
      //System.out.println(x + " " + y);
      if(!seen[x][y]){
         seen[x][y] = true;
         count++;
         //String binary = Integer.toBinaryString(
         if(board[x][y] >= 8) board[x][y] -= 8;
         else dfs(x,y-1);
         if(board[x][y] >= 4) board[x][y] -= 4;
         else dfs(x+1,y);
         if(board[x][y] >= 2) board[x][y] -= 2;
         else dfs(x,y+1);
         if(board[x][y] >= 1) board[x][y] -= 1;
         else dfs(x-1,y);
      }
   }
         
   
}