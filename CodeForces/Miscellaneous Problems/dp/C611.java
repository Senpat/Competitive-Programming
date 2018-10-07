//New Year and Domino
//semi-t
import java.io.*;
import java.util.*;

public class C611{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      char[][] board = new char[n][m];
      
      for(int k = 0; k < n; k++){
         board[k] = f.readLine().toCharArray();
      }
      
      int[][] hori = new int[n][m];
      int[][] vert = new int[n][m];
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(j!=m-1){                   //calculate hori
               if(board[k][j] == '.' && board[k][j+1] == '.') hori[k][j] = 1;
            }
            if(k!=n-1){
               if(board[k][j] == '.' && board[k+1][j] == '.') vert[k][j] = 1;
            }
         }
      }
      //calculate prefixes
      int[][] phori = new int[n+1][m+1];
      int[][] pvert = new int[n+1][m+1];
      
      phori[1][1] = hori[0][0];
      pvert[1][1] = vert[0][0];
      
      /*for(int k = 1; k < n; k++){
         phori[k][0] = phori[k-1][0] + hori[k][0];
         pvert[k][0] = pvert[k-1][0] + vert[k][0];
      }
      for(int j = 1; j < m; j++){
         phori[0][j] = phori[0][j-1] + hori[0][j];
         pvert[0][j] = pvert[0][j-1] + vert[0][j];
      }*/
      
      for(int k = 1; k < n+1; k++){
         for(int j = 1; j < m+1; j++){
            phori[k][j] = phori[k-1][j] + phori[k][j-1] - phori[k-1][j-1] + hori[k-1][j-1];
            pvert[k][j] = pvert[k-1][j] + pvert[k][j-1] - pvert[k-1][j-1] + vert[k-1][j-1];
         }
      }
      
      //answer queries
      int q = Integer.parseInt(f.readLine());
      for(int i = 0; i < q; i++){
         st = new StringTokenizer(f.readLine());
         int x1 = Integer.parseInt(st.nextToken());
         int y1 = Integer.parseInt(st.nextToken());
         int x2 = Integer.parseInt(st.nextToken());
         int y2 = Integer.parseInt(st.nextToken());
         
         
         int answer = 0;
         answer += phori[x2][y2-1] - phori[x2][y1-1] - phori[x1-1][y2-1] + phori[x1-1][y1-1];
         answer += pvert[x2-1][y2] - pvert[x2-1][y1-1] - pvert[x1-1][y2] + pvert[x1-1][y1-1];
         out.println(answer);
      }
      
      
      
      out.close();
   
   }
   
}