//make sure to make new file!
import java.io.*;
import java.util.*;
//backtracking
public class K{
   
   public static int n,m;
   public static boolean[][] board;
   
   public static int answer;
   
   public static int[] dx = new int[]{-1,1,0,0};
   public static int[] dy = new int[]{0,0,-1,1};
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
         
         board = new boolean[n][m];
         
         answer = 0;
         for(int k = 0; k < x; k++){
            st = new StringTokenizer(f.readLine());
            
            int xi = Integer.parseInt(st.nextToken())-1;
            int yi = Integer.parseInt(st.nextToken())-1;
            
            board[xi][yi] = true;
            
            answer++;
         }
         
         dfs(answer);
         
         out.println(answer);
         
         
      }
      
      
      
      
      out.close();
   }
   
   public static void dfs(int dep){
      answer = Math.min(answer,dep);
      
      for(int x = 0; x < n; x++){
         for(int y = 0; y < m; y++){
            if(!board[x][y]) continue;
            for(int d = 0; d < 4; d++){
               int nx = x+dx[d];
               int ny = y+dy[d];
               int nx2 = x+dx[d]*2;
               int ny2 = y+dy[d]*2;
               
               if(in(nx,ny) && in(nx2,ny2) && board[nx][ny] && !board[nx2][ny2]){
                  board[x][y] = false;
                  board[nx][ny] = false;
                  board[nx2][ny2] = true;
                  dfs(dep-1);
                  board[x][y] = true;
                  board[nx][ny] = true;
                  board[nx2][ny2] = false;
               }
            }
         }
      }
   }
   
   public static boolean in(int x, int y){
      return x >= 0 && x < n && y >= 0 && y < m;
   }
   
      
}