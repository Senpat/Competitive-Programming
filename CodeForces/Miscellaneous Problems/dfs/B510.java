//Fox and Two Dots
import java.io.*;
import java.util.*;

public class B510{

   public static char[][] board;
   public static boolean[][] seen;
   public static int n,m;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      board = new char[n][m];
      seen = new boolean[n][m];
      
      for(int k = 0; k < n; k++){
         String cur = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = cur.charAt(j);
         }
      }
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(!seen[k][j]){
               bfs(k,j,-1,-1);
            }
         }
      }
      
      out.println("No");
      
      
      
      out.close();
   }
   
   public static void bfs(int x, int y, int px, int py){
      if(seen[x][y]) 
         return;
      seen[x][y] = true;
      
      if(in(x-1,y)) check(x,y,x-1,y,px,py);
      if(in(x+1,y)) check(x,y,x+1,y,px,py);
      if(in(x,y-1)) check(x,y,x,y-1,px,py);
      if(in(x,y+1)) check(x,y,x,y+1,px,py);
      
   }
   
   public static void check(int x, int y, int nx, int ny, int px, int py){
      if(board[nx][ny] == board[x][y]){
         if(seen[nx][ny] && nx != px && ny != py){
            out.println("Yes");
            out.close();
            System.exit(0);
         } else if(!seen[nx][ny]){
            bfs(nx,ny,x,y);
         }
      }
   }
   
   public static boolean in(int x, int y){
      return x >= 0 && y >= 0 && x < n && y < m;
   }
}