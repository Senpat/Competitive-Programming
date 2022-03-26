//make sure to make new file!
import java.io.*;
import java.util.*;

public class D771{

   public static int n;
   public static int m;
   public static int[][] board;
   
   public static ArrayList<Op> answer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      board = new int[n+1][m+1];
      
      for(int k = 1; k <= n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 1; j <= m; j++){
            board[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      answer = new ArrayList<Op>();
      ArrayList<Point> start = new ArrayList<Point>();
      //find starting points
      for(int k = 1; k <= n-1; k++){
         for(int j = 1; j <= m-1; j++){
            if(check(k,j) != -1){
               start.add(new Point(k,j));
               answer.add(new Op(k,j,board[k][j]));
            }
         }
      }
               
      for(Point p : start){
         int x = p.x;
         int y = p.y;
         empty(x,y);
      }
      
      for(Point p : start){
         dfs(p.x,p.y);
      }
      
      boolean fail = false;
      for(int k = 1; k <= n; k++){
         for(int j = 1; j <= m; j++){
            if(board[k][j] != -1) fail = true;
         }
      }
      
      if(fail){
         out.println(-1);
      } else {
         StringJoiner sj = new StringJoiner("\n");
         sj.add("" + answer.size());
         for(int k = answer.size()-1; k >= 0; k--){
            sj.add(answer.get(k).toString());
         }
         out.println(sj.toString());
      }
      
      
      
      
      
      out.close();
   }
   
   public static void dfs(int x, int y){
      
      for(int nx = x-1; nx <= x+1; nx++){
         for(int ny = y-1; ny <= y+1; ny++){
            if(nx == x && ny == y) 
               continue;
            int ch = check(nx,ny);
            if(ch != -1){
               answer.add(new Op(nx,ny,ch));
               empty(nx,ny);
               dfs(nx,ny);
            }
         }
      }
   }
   
   
   public static void empty(int x, int y){
      board[x][y] = -1;
      board[x+1][y] = -1;
      board[x][y+1] = -1;
      board[x+1][y+1] = -1;
   }
      
   
   public static int check(int x, int y){
      if(!in(x,y) || !in(x+1,y+1)) 
         return -1;
      int hasx = -1;
      int hasy = -1;
      
      for(int k = x; k <= x+1; k++){
         for(int j = y; j <= y+1; j++){
            if(board[k][j] != -1){
               if(hasx == -1){
                  hasx = k;
                  hasy = j;
               } else {
                  if(board[k][j] != board[hasx][hasy]) 
                     return -1;
               }
            }
         }
      }
      
      if(hasx == -1) 
         return -1;
      return board[hasx][hasy];
   }
   
   public static boolean in(int x, int y){
      return x >= 1 && x <= n && y >= 1 && y <= m;
   }
   
   public static class Point{
      int x;
      int y;
      public Point(int a, int b){
         x = a;
         y = b;
      }
   }
   
   public static class Op{
      int x;
      int y;
      int c;
      public Op(int a, int b, int d){
         x = a;
         y = b;
         c = d;
      }
      
      public String toString(){
         return "" + x + " " + y + " " + c;
      }
   }
   
  
}