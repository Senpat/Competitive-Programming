//make sure to make new file!
import java.io.*;
import java.util.*;

public class E317{
   
   public static char[][] board;
   
   public static int n;
   public static int m;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      board = new char[n][m];
      int sx = -1;
      int sy = -1;
      int gx = -1;
      int gy = -1;
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
            
            if(board[k][j] == 'S'){
               sx = k;
               sy = j;
            }
            
            if(board[k][j] == 'G'){
               gx = k;
               gy = j;
            }
         }
      }
      
      //fill left and right line of sight
      boolean insight;
      for(int j = 0; j < m; j++){
         insight = false;
         //down
         for(int k = 0; k < n; k++){
            insight = process(k,j,insight,'v');
         }
         //up
         insight = false;
         for(int k = n-1; k >= 0; k--){
            insight = process(k,j,insight,'^');
         }
      }
      
      for(int k = 0; k < n; k++){
         insight = false;
         //left
         for(int j = 0; j < m; j++){
            insight = process(k,j,insight,'>');
         }
         insight = false;
         //right
         for(int j = m-1; j >= 0; j--){
            insight = process(k,j,insight,'<');
         }
      }
      
      Queue<State> q = new LinkedList<State>();
      
      int[][] dist = new int[n][m];
      for(int k = 0; k < n; k++) Arrays.fill(dist[k],-1);
      
      dist[sx][sy] = 0;
      q.add(new State(sx,sy,0));
      
      int[] dx = new int[]{-1,1,0,0};
      int[] dy = new int[]{0,0,1,-1};
      while(!q.isEmpty()){
         State s = q.poll();
         int x = s.x;
         int y = s.y;
         
         for(int k = 0; k < 4; k++){
            if(in(x+dx[k],y+dy[k]) && checkpassable(board[x+dx[k]][y+dy[k]]) && dist[x+dx[k]][y+dy[k]] == -1){
               dist[x+dx[k]][y+dy[k]] = s.d+1;
               q.add(new State(x+dx[k],y+dy[k],s.d+1));
            }
         }
      }
      
      out.println(dist[gx][gy]);  
         
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class State{
      int x;
      int y;
      int d;
      public State(int a, int b, int c){
         x = a;
         y = b;
         d = c;
      }
   }
   
   public static boolean process(int k, int j, boolean insight, char dir){
      if(board[k][j] == '.' && insight){
         board[k][j] = '!';
      } else if(board[k][j] == dir){
         insight = true;
      } else if(board[k][j] != '.' && board[k][j] != '!'){
         //no need to check for S and G
         insight = false;
      }
      return insight;
   }
   
   public static boolean checkpassable(char c){
      return c == '.' || c == 'S' || c == 'G';
   }
   
   public static boolean in(int x, int y){
      return x >= 0 && x < n && y >= 0 && y < m;
   }
      
}