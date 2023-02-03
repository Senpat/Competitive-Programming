/*
TASK: maze1
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class maze1{

   public static int n;
   public static int m;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("maze1.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      m = Integer.parseInt(st.nextToken());
      n = Integer.parseInt(st.nextToken());
      
      char[][] board = new char[2*n+1][2*m+1];
      
      for(int k = 0; k < 2*n+1; k++){
         String s = f.readLine();
         for(int j = 0; j < 2*m+1; j++){
            board[k][j] = s.charAt(j);  
         }
      }
      
      int[][] dist = new int[n][m];
      for(int k = 0; k < n; k++) Arrays.fill(dist[k],Integer.MAX_VALUE);
      
      Queue<State> q = new LinkedList<State>();
      
      for(int k = 0; k < m; k++){
         if(board[0][k*2+1] == ' '){
            dist[0][k] = 1;
            q.add(new State(0,k));
         }
         if(board[2*n][k*2+1] == ' '){
            dist[n-1][k] = 1;
            q.add(new State(n-1,k));
         }
      }
      
      for(int j = 0; j < n; j++){
         if(board[j*2+1][0] == ' '){
            dist[j][0] = 1;
            q.add(new State(j,0));
         }
         if(board[j*2+1][2*m] == ' '){
            dist[j][m-1] = 1;
            q.add(new State(j,m-1));
         }
      }
      
      int[] dx = new int[]{1,-1,0,0};
      int[] dy = new int[]{0,0,1,-1};
      
      while(!q.isEmpty()){
         State s = q.poll();
         
         for(int dir = 0; dir < 4; dir++){
            int nx = s.x+dx[dir];
            int ny = s.y+dy[dir];
            if(!in(nx,ny) || dist[nx][ny] != Integer.MAX_VALUE) continue;
            
            if(board[2*s.x+1+dx[dir]][2*s.y+1+dy[dir]] != ' ') continue;
            
            dist[nx][ny] = dist[s.x][s.y]+1;
            q.add(new State(nx,ny));
         }
      }
      
      int max = 0;
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            max = Math.max(dist[k][j],max);
         }
      }
      
      out.println(max);
        
      out.close();
   }
   
   public static boolean in(int x, int y){
      return x >= 0 && x < n && y >= 0 && y < m;
   }
   
   public static class State{
      int x;
      int y;
      public State(int a, int b){
         x = a;
         y = b;
      }
   }
      
}