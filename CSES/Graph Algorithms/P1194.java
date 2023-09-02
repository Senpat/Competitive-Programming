//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1194{

   public static int n,m;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      int ax = -1;
      int ay = -1;
      char[][] board = new char[n][m];
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
            if(board[k][j] == 'A'){
               ax = k;
               ay = j;
            }
         }
      }
      
      boolean[][] seen = new boolean[n][m];
      int[][] mdist = new int[n][m];            //distance to nearest monster
      for(int k = 0; k < n; k++){
         Arrays.fill(mdist[k],Integer.MAX_VALUE);
      }
      
      Queue<Point> q = new LinkedList<Point>();
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(board[k][j] == 'M'){
               mdist[k][j] = 0;
               q.add(new Point(k,j));
            }
         }
      }
      
      int[] dx = new int[]{-1,1,0,0};
      int[] dy = new int[]{0,0,-1,1};
      char[] dir = new char[]{'U','D','L','R'};
      
      while(!q.isEmpty()){
         Point p = q.poll();
         int x = p.x;
         int y = p.y;
         
         for(int d = 0; d < 4; d++){
            if(in(x+dx[d],y+dy[d]) && mdist[x+dx[d]][y+dy[d]] == Integer.MAX_VALUE && board[x+dx[d]][y+dy[d]] != '#'){
               mdist[x+dx[d]][y+dy[d]] = mdist[x][y]+1;
               q.add(new Point(x+dx[d],y+dy[d]));
            }
         }
      }
      
      char[][] path = new char[n][m];
      for(int k = 0; k < n; k++){
         Arrays.fill(path[k],'?');
      }
      
      q = new LinkedList<Point>();
      int[][] adist = new int[n][m];
      for(int k = 0; k < n; k++){
         Arrays.fill(adist[k],-1);
      }
      
      adist[ax][ay] = 0;
      q.add(new Point(ax,ay));
      
      int ex = -1;
      int ey = -1;
      
      while(!q.isEmpty()){
         Point p = q.poll();
         int x = p.x;
         int y = p.y;
         
         if(edge(x,y)){
            ex = x;
            ey = y;
            break;
         }
         
         for(int d = 0; d < 4; d++){
            int newx = x+dx[d];
            int newy = y+dy[d];
            if(in(newx,newy) && board[newx][newy] != '#' && adist[newx][newy] == -1 && mdist[newx][newy] > adist[x][y]+1){
               adist[newx][newy] = adist[x][y]+1;
               path[newx][newy] = dir[d];
               q.add(new Point(newx,newy));
            }
         }
      }
      
      if(ex == -1){
         out.println("NO");
      } else {
         ArrayList<Character> answer = new ArrayList<Character>();
         int x = ex;
         int y = ey;
         while(!(x == ax && y == ay)){
            answer.add(path[x][y]);
            if(path[x][y] == 'U'){
               x++;
            } else if(path[x][y] == 'D'){
               x--;
            } else if(path[x][y] == 'L'){
               y++;
            } else if(path[x][y] == 'R'){
               y--;
            }
         }
         
         out.println("YES");
         out.println(answer.size());
         StringJoiner sj = new StringJoiner("");
         for(int k = answer.size()-1; k >= 0; k--){
            sj.add("" + answer.get(k));
         }
         out.println(sj.toString());
      }
      
      
      
      
      out.close();
   }
   
   public static class Point{
      int x;
      int y;
      public Point(int a, int b){
         x = a;
         y = b;
      }
   }
   
   public static boolean in(int x, int y){
      return x >= 0 && x < n && y >= 0 && y < m;
   }
   
   public static boolean edge(int x, int y){
      return x == 0 || x == n-1 || y == 0 || y == m-1;
   }
      
}