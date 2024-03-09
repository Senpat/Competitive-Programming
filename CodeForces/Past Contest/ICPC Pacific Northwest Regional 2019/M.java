//make sure to make new file!
import java.io.*;
import java.util.*;

public class M{
   
   public static int N = 2002;
   public static char[][] board;
   
   public static boolean[][] seen;
   
   public static int[] dx = new int[]{1,-1,0,0};
   public static int[] dy = new int[]{0,0,1,-1};
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      char[][] input = new char[n][m];
      boolean zerof = false;
      boolean foundslash = false;
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            input[k][j] = s.charAt(j);
            
            if(input[k][j] == '/'){
               foundslash = true;
               zerof = ((k+j)%2 == 0);
            } else if(input[k][j] == '\\') {
               foundslash = true;
               zerof = ((k+j)%2 == 1);
            }
         }
      }
      
      if(!foundslash){
         out.println(0);
         out.close();
         return;
      }
      
      board = new char[N][N];
      for(int k = 0; k < N; k++) Arrays.fill(board[k],'.');
      
      //cols
      int bx = 1000;
      int by = 0;
      for(int s = 0; s < m; s++){
         int x = 0;
         int y = s;
         
         char between = '.';
         if((zerof && (x+y)%2 == 1) || (!zerof && (x+y)%2 == 0)) between = '#';
         
         int d = 0; 
         while(x < n && y < m){
            board[bx][by+2*d] = (input[x][y] == '.') ? '.' : '#';
            if(d > 0){
               //not first one
               board[bx][by+2*d-1] = between;
            }
            
            d++;
            x++;
            y++;
         }
         
         
         bx--;
         by++;
      }
      
      //rows
      bx = 1001;
      by = 1;
      for(int s = 1; s < n; s++){
         int x = s;
         int y = 0;
         
         char between = '.';
         if((zerof && (x+y)%2 == 1) || (!zerof && (x+y)%2 == 0)) between = '#';
         
         int d = 0; 
         while(x < n && y < m){
            board[bx][by+2*d] = (input[x][y] == '.') ? '.' : '#';;
            if(d > 0){
               //not first one
               board[bx][by+2*d-1] = between;
            }
            
            d++;
            x++;
            y++;
         }
         
         
         bx++;
         by++;
      }
      
      
      seen = new boolean[N][N];
      
      //fill outside as seen
      for(int k = 0; k < N; k++){
         if(!seen[k][0] && board[k][0] == '.'){
            ff(k,0);
         }
         if(!seen[k][N-1] && board[k][N-1] == '.'){
            ff(k,N-1);
         }
         if(!seen[0][k] && board[0][k] == '.'){
            ff(0,k);
         }
         if(!seen[N-1][k] && board[N-1][k] == '.'){
            ff(N-1,k);
         }
      }
      
      int comp = 0;
      for(int k = 0; k < N; k++){
         for(int j = 0; j < N; j++){
            if(!seen[k][j] && board[k][j] == '.'){
               comp++;
               ff(k,j);
            }
         }
      }
      
      out.println(comp);
      
      
      out.close();
   }
   
   public static void ff(int x, int y){
      
      Stack<Point> stack = new Stack<Point>();
      stack.push(new Point(x,y));
      seen[x][y] = true;
      
      while(!stack.isEmpty()){
         Point p = stack.pop();
         for(int d = 0; d < 4; d++){
            int nx = p.x + dx[d];
            int ny = p.y + dy[d];
            if(in(nx,ny) && !seen[nx][ny] && board[nx][ny] == '.'){
               seen[nx][ny] = true;
               stack.push(new Point(nx,ny));
            }
         }
      }
   }
   
   public static boolean in(int x, int y){
      return x < N && x >= 0 && y < N && y >= 0;
   }
   
   public static class Point{
      int x;
      int y;
      public Point(int a, int b){
         x = a;
         y = b;
      }
   }
      
}