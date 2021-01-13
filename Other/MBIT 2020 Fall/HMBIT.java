//make sure to make new file!
import java.io.*;
import java.util.*;

public class HMBIT{

   public static int n;
   public static int m;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      int r = Integer.parseInt(st.nextToken());
      
      char[][] board = new char[n][m];
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
         }
      }
      
      Point[] rams = new Point[r];
      for(int k = 0; k < r; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken())-1;
         int b = Integer.parseInt(st.nextToken())-1;
         
         rams[k] = new Point(a,b);
      }
      
      int[] distedge = new int[r];           //dist from edge for each ram
      
      int[][] dist = new int[n][m];          //dist from edge for every cell
      PriorityQueue<State> pq = new PriorityQueue<State>();
      for(int k = 0; k < n; k++){
         Arrays.fill(dist[k],Integer.MAX_VALUE);
         
         for(int j = 0; j < m; j++){
            if(k == 0 || k == n-1 || j == 0 || j == m-1){
               if(board[k][j] == '#'){
                  pq.add(new State(1,k,j));
                  dist[k][j] = 1;
               } else {
                  pq.add(new State(0,k,j));
                  dist[k][j] = 0;
               }
            }
          }
       }
       
       while(!pq.isEmpty()){
         State s = pq.poll();
         //out.println(s.d);
         if(dist[s.x][s.y] < s.d) continue;
         
         if(in(s.x-1,s.y)){
            int newdist = s.d;
            if(board[s.x-1][s.y] == '#') newdist++;
            if(dist[s.x-1][s.y] > newdist){
               dist[s.x-1][s.y] = newdist;
               pq.add(new State(newdist,s.x-1,s.y));
            }
         }
         if(in(s.x+1,s.y)){
            int newdist = s.d;
            if(board[s.x+1][s.y] == '#') newdist++;
            if(dist[s.x+1][s.y] > newdist){
               pq.add(new State(newdist,s.x+1,s.y));
               dist[s.x+1][s.y] = newdist;
            }
         }
         if(in(s.x,s.y-1)){
            int newdist = s.d;
            if(board[s.x][s.y-1] == '#') newdist++;
            if(dist[s.x][s.y-1] > newdist){
               pq.add(new State(newdist,s.x,s.y-1));
               dist[s.x][s.y-1] = newdist;
            }
         }
         if(in(s.x,s.y+1)){
            int newdist = s.d;
            if(board[s.x][s.y+1] == '#') newdist++;
            if(dist[s.x][s.y+1] > newdist){
               pq.add(new State(newdist,s.x,s.y+1));
               dist[s.x][s.y+1] = newdist;
            }
         }
      }
      
      int min1 = Integer.MAX_VALUE;
      
      for(int k = 0; k < r; k++){
         for(int j = k+1; j < r; j++){
            min1 = Math.min(min1,dist[rams[k].x][rams[k].y] + dist[rams[j].x][rams[j].y]);
         }
      }
      
      out.println(min1);
         
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean in(int x, int y){
      return x >= 0 && x < n && y >= 0 && y < m;
   }
   
   public static class State implements Comparable<State>{
      int d;
      int x;
      int y;
      public State(int a, int b, int c){
         d = a;
         x = b;
         y = c;
      }
      
      public int compareTo(State s){
         return d-s.d;
      }
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