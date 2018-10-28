//Labyrinth
//iterative bfs

/*
Mistakes Made:
Didn't use PQ to ensure least horizontal moves
forgot used[xi-1][yi-1] = true
compareto is this-other
faster to do stuff in neighbors (?)
*/
import java.io.*;
import java.util.*;

public class B1063b{
   
   public static int n,m,xi,yi,left,right;
   public static char[][] board;
   public static boolean[][] used;
   public static int[][] movesl,movesr;
   
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
      movesl = new int[n][m];
      movesr = new int[n][m];
      
      PriorityQueue<State> q = new PriorityQueue<State>();
      q.add(new State(xi-1,yi-1,0,0));
      
      int x,y,l,r;
      int answer = 0;
      used[xi-1][yi-1] = true;
      while(!q.isEmpty()){
         State cur = q.poll();
         x = cur.x;
         y = cur.y;
         l = cur.l;
         r = cur.r;
         
         answer++;
         //System.out.println(x + " " + y);
         if(in(x-1,y) && !used[x-1][y] && board[x-1][y] != '*'){
            q.add(new State(x-1,y,l,r));
            used[x-1][y] = true;
         }
         if(in(x+1,y) && !used[x+1][y] && board[x+1][y] != '*'){
            q.add(new State(x+1,y,l,r));
            used[x+1][y] = true;  
         }
         if(in(x,y-1) && !used[x][y-1] && board[x][y-1] != '*' && l < left){
            q.add(new State(x,y-1,l+1,r));
            used[x][y-1] = true;
         }
         if(in(x,y+1) && !used[x][y+1] && board[x][y+1] != '*' && r < right){
            q.add(new State(x,y+1,l,r+1));
            used[x][y+1] = true;
         }
         
         
      }
      
      
      
      
      out.println(answer);
      
      
      out.close();
   }
   
   public static class State implements Comparable<State>{
      int x;
      int y;
      int l;
      int r;
      
      public State(int a, int b, int c, int d){
         x = a;
         y = b;
         l = c;
         r = d;
      }
      
      public int compareTo(State s){
         return l+r-s.l-s.r;
      }
   }
   
   
   public static boolean in(int a, int b){
      return a >= 0 && b >= 0 && a < n && b < m;
   }
   
}