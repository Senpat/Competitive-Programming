//Kilani and the Game
import java.io.*;
import java.util.*;

public class D533{
   
   public static int[] dx = {-1,1,0,0};
   public static int[] dy = {0,0,1,-1};
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int p = Integer.parseInt(st.nextToken());
      
      int[] speeds = new int[p+1];
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 1; k <= p; k++){
         speeds[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] scores = new int[p+1];
      
      ArrayList<Queue<Point>> qs = new ArrayList<Queue<Point>>(p+1);
      
      for(int k = 0; k <= p; k++) qs.add(new LinkedList<Point>());
      
      char[][] board = new char[n][m];
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
            if(board[k][j] != '.' && board[k][j] != '#'){
               int i = Character.getNumericValue(board[k][j]);
               scores[i]++;
               qs.get(i).add(new Point(k,j,0,0));
            }
         }
      }
      
      boolean bool = true;
      
      int turn = 0;
      
      while(bool){
         bool = false;
         
         for(int k = 1; k <= p; k++){
            Queue<Point> q = qs.get(k);                   //see if this works
            
            while(!q.isEmpty()){
               if(q.peek().turn > turn) 
                  break;
               
               Point cur = q.poll();
               
               
                  
               
                  
               for(int d = 0; d < 4; d++){
                  int newx = cur.x + dx[d];
                  int newy = cur.y + dy[d];
                  if(in(newx,newy,n,m) && board[newx][newy] == '.'){
                     board[newx][newy] = (char)('0' + k);
                     scores[k]++;
                     bool = true;
                     q.add(new Point(newx, newy, cur.level + 1,(cur.level+1)/speeds[k]));
                  }
               }
               
            }
            
            //qs.set(k,q);
            
         }
                  
               
               
         
         
         turn++;
      }
               
      
      for(int k = 1; k <= p; k++){
         out.print(scores[k] + " ");
      }
      
      
      
      out.close();
   }
   
   public static boolean in(int x, int y, int n, int m){
      return x >= 0 && y >= 0 && x < n && y < m;
   }
   
   public static class Point{
      int x;
      int y;
      int level;
      int turn;
      public Point(int a, int b, int c,int d){
         x = a;
         y = b;
         level = c;
         turn = d;
      }
      public String toString(){
         return x + " " + y + " " + level + " " + turn;
      }
   }
   
}