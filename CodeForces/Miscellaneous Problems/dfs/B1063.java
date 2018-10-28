//Labyrinth
//stack overflow - should use bfs
import java.io.*;
import java.util.*;

public class B1063{
   
   public static int n,m,xi,yi,left,right;
   public static char[][] board;
   public static boolean[][] used;
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      xi = Integer.parseInt(st.nextToken());
      yi = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      left = Integer.parseInt(st.nextToken());
      right = Integer.parseInt(st.nextToken());
      
      board = new char[n][m];
      
      String s;
      for(int k = 0; k < n; k++){
         s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
         }
      }
      
      used = new boolean[n][m];
      
      bfs(xi-1,yi-1,left,right);
      
      int answer = 0;
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(used[k][j]) answer++;
         }
      }
      
      out.println(answer);
      
      
      out.close();
   }
   
   public static void bfs(int x, int y, int l, int r){
      if(in(x,y) && !used[x][y] && board[x][y] != '*'){
         //System.out.println(x + " " + y);
         used[x][y] = true;
         bfs(x-1,y,l,r);
         bfs(x+1,y,l,r);
         if(l > 0){
            bfs(x,y-1,l-1,r);
         }
         if(r > 0){
            bfs(x,y+1,l,r-1);
         }
      
      }
   }
   
   
   
   public static boolean in(int a, int b){
      return a >= 0 && b >= 0 && a < n && b < m;
   }
   
}