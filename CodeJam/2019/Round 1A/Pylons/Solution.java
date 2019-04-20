//Pylons
import java.io.*;
import java.util.*;

public class Solution{

   public static boolean[][] board;
   public static ArrayList<Point> answer;
   public static int r,c;
   public static boolean done;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      
      for(int t = 1; t <= q; t++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         r = Integer.parseInt(st.nextToken());
         c = Integer.parseInt(st.nextToken());
         
         board = new boolean[r][c];
         answer=  new ArrayList<Point>();
         done = false;
         
         //find starting location
         int startx = -1;
         int starty = -1;
         if(r >= 3 && c >= 2){
            startx = 2;
            starty = 1;
         } else if(r >= 2 && c >= 3){
            startx = 1;
            starty = 2;
         } else if(r == 1 && c == 1){
             out.println("Case #" + t + ": POSSIBLE");
             out.println("1 1");
             continue;
         } else {
            out.println("Case #" + t + ": IMPOSSIBLE");
            continue;
         }
         
         board[startx][starty] = true;
         answer.add(new Point(startx,starty));
         dfs(startx,starty,1);
         
         
         if(done){
            out.println("Case #" + t + ": POSSIBLE");
            for(Point p : answer){
               out.println((p.r+1) + " " + (p.c+1));
            }
            
         } else {
            out.println("Case #" + t + ": IMPOSSIBLE");
         }
      }
      
      
      
      
      out.close();
   }
   
   public static void dfs(int x, int y, int filled){
      if(done) return;
      if(filled == r*c){
         done = true;
         return;
      }
     
      for(int x1 = 0; x1 < r; x1++){
         for(int y1 = 0; y1 < c; y1++){
            if(board[x1][y1]) continue;
            if(x == x1 || y == y1 || x-y == x1-y1 || x+y == x1+y1) continue;
            
            board[x1][y1] = true;
            answer.add(new Point(x1,y1));
            dfs(x1,y1,filled+1);
            if(done) continue;
            board[x1][y1] = false;
            answer.remove(answer.size()-1);
         }
      }
   }
   
   
   
   public static class Point{
      int r;
      int c;
      public Point(int a, int b){
         r = a;
         c = b;
      }
      public String toString(){
         return r + " " + c;
      }
   }
      
}