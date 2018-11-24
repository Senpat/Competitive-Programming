/*
TASK: visitfj
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class visitfj{

   public static final int[] dx = {0,1,2,3,2,1,0,-1,-2,-3,-2,-1,0,1,0,-1};
   public static final int[] dy = {3,2,1,0,-1,-2,-3,-2,-1,0,1,2,1,0,-1,0};
   
   public static int[][] board;
   public static State[][] d;
   
   public static HashSet<Integer> seen;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("visitfj.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("visitfj.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      board = new int[n][n];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < n; j++){
            board[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      d = new State[n][n];
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            d[k][j] = new State(k*n+j,Integer.MAX_VALUE);
         }
      }
      
      d[0][0].dis = 0;
      
      seen = new HashSet<Integer>();
      //seen.add(0);
      PriorityQueue<State> pq = new PriorityQueue<State>();
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            pq.add(d[k][j]);
         }
      }
      
      while(!pq.isEmpty()){
         State cur = pq.poll();
         int x = cur.loc/n;
         int y = cur.loc%n;
         //"manhattan distance"
         if(n-1-x + n-1-y >= 3){
            seen.add(cur.loc);
            for(int k = 0; k < dx.length; k++){
               if(in(x+dx[k],y+dy[k],n)){
                  if(!seen.contains((x+dx[k])*n + y+dy[k])){
                     seen.add((x+dx[k])*n + y+dy[k]);
                     if(d[x+dx[k]][y+dy[k]].dis > d[x][y].dis + board[x+dx[k]][y+dy[k]] + 3*m){
                        pq.remove(d[x+dx[k]][y+dy[k]]);
                        d[x+dx[k]][y+dy[k]].dis = d[x][y].dis + board[x+dx[k]][y+dy[k]] + 3*m;
                        pq.add(d[x+dx[k]][y+dy[k]]);
                     }
                  }
               }
            }
         }
      }
      
      int min = Integer.MAX_VALUE;
      
      for(int k = n-1; k >= n-3; k--){
         for(int j = n-1; j >= n-3; j--){
            if(n-1-k + n-1-j < 3 && in(k,j,n)){
               min = Math.min(min,d[k][j].dis + (n-1-k + n-1-j)*m);
            }
         }
      }
      
      //System.out.println(min);
      out.println(min);
         
      
      
      out.close();
   }
   
   public static boolean in(int x, int y, int n){
      return x >= 0 && y >= 0 && x < n && y < n;
   }
   
   public static class State implements Comparable<State>{
      int loc;
      int dis;
      
      public State(int a, int b){
         loc = a;
         dis = b;
      }
      
      public int compareTo(State s){
         return dis-s.dis;
      }
   }
      
   
   
}