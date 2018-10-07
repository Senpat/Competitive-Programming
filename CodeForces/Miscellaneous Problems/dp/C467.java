//New Year and Domino
import java.io.*;
import java.util.*;

public class C467{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      char[][] board = new char[n][m];
      
      for(int k = 0; k < n; k++){
         board[n] = f.readLine().toCharArray();
      }
      
      int[][] hori = new int[n][m];
      
      for(int k = 0; k < n; k++) hori[n][0] = 0;
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m-1; j++){
            if(board[k][j] == '.' && board[k][j+1] == '.'){
               hori[k][j+1] = 1 + hori[k][j];
            } else {
               hori[k][j+1] = hori[k][j];
            }
         }
      }
      
      int[][] vert = new int[n][m];
      
      Arrays.fill(vert[0],0);
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j < m; j++){
            if(board[k][j] == '.' && board[k+1][j] == '.'){
               vert[k+1][j] = 1+vert[k][j];
            } else {
               vert[k+1][j] = vert[k][j];
            }
         }
      }
      
      int q = Integer.parseInt(f.readLine());
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
         int x1 = Integer.parseInt(st.nextToken());
         int y1 = Integer.parseInt(st.nextToken());
         int x2 = Integer.parseInt(st.nextToken());
         int y2 = Integer.parseInt(st.nextToken());
         int count = 0;
         
         for(int i = x1; i <= x2; i++){
            count+=hori[i][y2]-hori[i][y1-1];
            count+=vert[i][y2]-vert[i][y1-1];
         }
         out.println(count);
      }
      
      out.close();
   }
   
}