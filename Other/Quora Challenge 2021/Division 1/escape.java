//make sure to make new file!
import java.io.*;
import java.util.*;

public class escape{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int g = Integer.parseInt(st.nextToken());
      
      int sx = -1;
      int sy = -1;
      int ex = -1;
      int ey = -1;
      char[][] board = new char[n][m];
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
            if(board[k][j] == 'S'){
               sx = k;
               sy = j;
            } else if(board[k][j] == 'E'){
               ex = k;
               ey = j;
            }
         }
      }
      
      int[][] guardnum = new int[n][m];
      for(int k = 0; k < n; k++) Arrays.fill(guardnum[k],Integer.MAX_VALUE);
      
      PriorityQueue<GState> pq = new PriorityQueue<GState>();
      
      for(int k = 0; k < g; k++){
         st = new StringTokenizer(f.readLine());
      
         int gx = Integer.parseInt(st.nextToken())-1;
         int gy = Integer.parseInt(st.nextToken())-1;
         int d = Integer.parseInt(st.nextToken());
         
         guardnum[gx][gy] = d;
         pq.add(new GState(gx,gy,d));
      }
      
      while(!pq.isEmpty()){
         GState gs = pq.poll();
         
         if(guardnum[gs.x][gs.y] != Integer.MAX_VALUE && guardnum[gs.x][gs.y] > gs.dleft) continue;
         if(guardnum[gs.x][gs.y] == 0) continue;
         
         //up
         if(board[gs.x-1][gs.y] != '#' && (guardnum[gs.x-1][gs.y] == Integer.MAX_VALUE || guardnum[gs.x-1][gs.y] < gs.dleft-1)){
            guardnum[gs.x-1][gs.y] = gs.dleft-1;
            pq.add(new GState(gs.x-1,gs.y,gs.dleft-1));
         }
         //down
         if(board[gs.x+1][gs.y] != '#' && (guardnum[gs.x+1][gs.y] == Integer.MAX_VALUE || guardnum[gs.x+1][gs.y] < gs.dleft-1)){
            guardnum[gs.x+1][gs.y] = gs.dleft-1;
            pq.add(new GState(gs.x+1,gs.y,gs.dleft-1));
         }
         //left
         if(board[gs.x][gs.y-1] != '#' && (guardnum[gs.x][gs.y-1] == Integer.MAX_VALUE || guardnum[gs.x][gs.y-1] < gs.dleft-1)){
            guardnum[gs.x][gs.y-1] = gs.dleft-1;
            pq.add(new GState(gs.x,gs.y-1,gs.dleft-1));
         }
         //right
         if(board[gs.x][gs.y+1] != '#' && (guardnum[gs.x][gs.y+1] == Integer.MAX_VALUE || guardnum[gs.x][gs.y+1] < gs.dleft-1)){
            guardnum[gs.x][gs.y+1] = gs.dleft-1;
            pq.add(new GState(gs.x,gs.y+1,gs.dleft-1));
         }
      }
      
      if(guardnum[sx][sy] != Integer.MAX_VALUE || guardnum[ex][ey] != Integer.MAX_VALUE){
         out.println("IMPOSSIBLE");
         out.close();
         return;
      }
      
      //bfs
      Queue<State> q = new LinkedList<State>();
      boolean[][] seen = new boolean[n][m];
      seen[sx][sy] = true;
      q.add(new State(sx,sy,0));
      
      while(!q.isEmpty()){
         State s = q.poll();
         
         if(s.x == ex && s.y == ey){
            out.println(s.d);
            out.close();
            return;
         }
         
         //up
         if(board[s.x-1][s.y] != '#' && guardnum[s.x-1][s.y] == Integer.MAX_VALUE && !seen[s.x-1][s.y]){
            seen[s.x-1][s.y] = true;
            q.add(new State(s.x-1,s.y,s.d+1));
         }
         //down
         if(board[s.x+1][s.y] != '#' && guardnum[s.x+1][s.y] == Integer.MAX_VALUE && !seen[s.x+1][s.y]){
            seen[s.x+1][s.y] = true;
            q.add(new State(s.x+1,s.y,s.d+1));
         }
         //left
         if(board[s.x][s.y-1] != '#' && guardnum[s.x][s.y-1] == Integer.MAX_VALUE && !seen[s.x][s.y-1]){
            seen[s.x][s.y-1] = true;
            q.add(new State(s.x,s.y-1,s.d+1));
         }
         //right
         if(board[s.x][s.y+1] != '#' && guardnum[s.x][s.y+1] == Integer.MAX_VALUE && !seen[s.x][s.y+1]){
            seen[s.x][s.y+1] = true;
            q.add(new State(s.x,s.y+1,s.d+1));
         }
      }
      
      out.println("IMPOSSIBLE");
         
         
         
      
      
      
      
      
      
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
   
   public static class GState implements Comparable<GState>{
      int x;
      int y;
      int dleft;
      public GState(int a, int b, int c){
         x = a;
         y = b;
         dleft = c;
      }
      
      public int compareTo(GState g){
         return g.dleft-dleft;                     //decreasing order
      }
   }
   
      
}