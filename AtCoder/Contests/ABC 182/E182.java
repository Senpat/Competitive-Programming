//make sure to make new file!
import java.io.*;
import java.util.*;

public class E182{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int h = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[][] board = new int[h+1][w+1];                   //1 is bulb, 2 is block, 3 is illuminated square
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         board[a][b] = 1;
      }
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int c = Integer.parseInt(st.nextToken());
         int d = Integer.parseInt(st.nextToken());
         
         board[c][d] = 2;
      }
      
      int[][] nextu = new int[h+1][w+1];
      int[][] nextd = new int[h+1][w+1];
      int[][] nextr = new int[h+1][w+1];
      int[][] nextl = new int[h+1][w+1];
      
      for(int k = 1; k <= w; k++){
         int curnext = 0;
         for(int j = 1; j <= h; j++){
            if(board[j][k] != 0) curnext = board[j][k];
            else if(curnext != 0) nextu[j][k] = curnext;
         }
      }
      
      for(int k = 1; k <= w; k++){
         int curnext = 0;
         for(int j = h; j >= 1; j--){
            if(board[j][k] != 0) curnext = board[j][k];
            else if(curnext != 0) nextd[j][k] = curnext;
         }
      }
      
      for(int k = 1; k <= h; k++){
         int curnext = 0;
         for(int j = 1; j <= w; j++){
            if(board[k][j] != 0) curnext = board[k][j];
            else if(curnext != 0) nextl[k][j] = curnext;
         }
      }
      
      for(int k = 1; k <= h; k++){
         int curnext = 0;
         for(int j = w; j >= 1; j--){
            if(board[k][j] != 0) curnext = board[k][j];
            else if(curnext != 0) nextr[k][j] = curnext;
         }
      }
   
      int answer = 0;
      for(int k = 1; k <= h; k++){
         for(int j = 1; j <= w; j++){
            if(board[k][j] == 1 || nextu[k][j] == 1 || nextd[k][j] == 1 || nextl[k][j] == 1 || nextr[k][j] == 1) answer++;
         }
      }
      
      out.println(answer);
      
      out.close();
   }
   
      
}