//make sure to make new file!
import java.io.*;
import java.util.*;

public class C542{
   
   public static int[][] board;
   public static int[][] regions;
   public static int n;
   public static ArrayList<Point> points;
   public static int min;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int r1 = Integer.parseInt(st.nextToken())-1;
      int c1 = Integer.parseInt(st.nextToken())-1;
      
      st = new StringTokenizer(f.readLine());
      
      int r2 = Integer.parseInt(st.nextToken())-1;
      int c2 = Integer.parseInt(st.nextToken())-1;
      
      board = new int[n][n];
      regions = new int[n][n];
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < n; j++){
            board[k][j] = s.charAt(j)-'0';
         }
      }
      
      points = new ArrayList<Point>();
      
      //1 is starting, 2 is ending region
      ff(r1,c1,1);
      
      if(regions[r2][c2] == 1){
         out.println(0);
         out.close();
         System.exit(0);
      }
      
      min = Integer.MAX_VALUE;
      ff2(r2,c2);
      
      out.println(min);
      
      out.close();
   }
   
   public static void ff2(int x, int y){
      for(Point p : points){
         min = Math.min(min, (x-p.x)*(x-p.x) + (y-p.y)*(y-p.y));
      }
      
      regions[x][y] = 2;
      if(in(x+1,y) && board[x+1][y] == 0 && regions[x+1][y] == 0) ff2(x+1,y);
      if(in(x,y+1) && board[x][y+1] == 0 && regions[x][y+1] == 0) ff2(x,y+1);
      if(in(x-1,y) && board[x-1][y] == 0 && regions[x-1][y] == 0) ff2(x-1,y);
      if(in(x,y-1) && board[x][y-1] == 0 && regions[x][y-1] == 0) ff2(x,y-1);
   }
      
   
   
   public static void ff(int x, int y, int d){
      
      regions[x][y] = d;
      points.add(new Point(x,y));
      
      if(in(x+1,y) && board[x+1][y] == 0 && regions[x+1][y] == 0) ff(x+1,y,d);
      if(in(x,y+1) && board[x][y+1] == 0 && regions[x][y+1] == 0) ff(x,y+1,d);
      if(in(x-1,y) && board[x-1][y] == 0 && regions[x-1][y] == 0) ff(x-1,y,d);
      if(in(x,y-1) && board[x][y-1] == 0 && regions[x][y-1] == 0) ff(x,y-1,d);
   }
      
   
   public static boolean in(int x, int y){
      return x < n && y < n && x >= 0 && y >= 0;
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