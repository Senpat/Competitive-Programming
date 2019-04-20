
/*
TASK: valleys
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class valleys{

   public static long[][] board;
   public static int n;
   public static boolean[][] holes;
   public static HashSet<Integer> used;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("valleys.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("valleys.out")));
      
      
      n = Integer.parseInt(f.readLine());
      
      board = new long[n][n];
      holes = new boolean[n][n];
            
      PriorityQueue<Point> pq = new PriorityQueue<Point>(n*n,Collections.reverseOrder());
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int j = 0; j < n; j++){
            board[k][j] = Long.parseLong(st.nextToken());
            pq.add(new Point(board[k][j],k,j));
            holes[k][j] = false;
         }
      }
      
      long answer = (long)(n*n);
      
      while(!pq.isEmpty()){
         Point p = pq.poll();
         
         int x = p.x;
         int y = p.y;
         
         board[x][y] = -1;
         if(ishole(x,y)){
            holes[x][y] = true;
            updateholes(x,y);  
            
            used = new HashSet<Integer>();
            
            answer += add(x-1,y);
            answer += add(x+1,y);
            answer += add(x,y-1);
            answer += add(x,y+1);
            
            //System.out.println(p.value + " " + answer);
         }
      
      }
      
      System.out.println(answer);
      out.println(answer);
        
      out.close();
   }
   
   public static long add(int x, int y){
      if(!in(x,y) || board[x][y] == -1 || used.contains(hash1(x,y))) return 0;
      used.add(hash1(x,y));
      return 1L + add(x-1,y) + add(x+1,y) + add(x,y-1) + add(x,y+1);
   }
      
      
   public static int hash1(int x, int y){
      return x*(n+1)+y;
   }
   
   public static void updateholes(int x, int y){
      //if(x == 0 || y == 0 || x == n-1 || y == n-1){
      //   return;
      //}
      for(int dx = -1; dx <= 1; dx++){
         for(int dy = -1; dy <= 1; dy++){
            if(dx == 0 && dy == 0) 
               continue;
            if(!in(x+dx,y+dy)) continue;
            if(board[x+dx][y+dy] == -1 && !holes[x+dx][y+dy]){
               holes[x+dx][y+dy] = true;
               updateholes(x+dx,y+dy);
            }
            
         }
      }
   }
      
   
   public static boolean ishole(int x, int y){
      if(x == 0 || y == 0 || x == n-1 || y == n-1){
         return true;
      }
      boolean cur = false;
      for(int dx = -1; dx <= 1; dx++){
         for(int dy = -1; dy <= 1; dy++){
            if(dx == 0 && dy == 0) continue;
            cur = cur || holes[x+dx][y+dy];
         }
      }
      return cur;
   }
   
   
   public static boolean in(int x, int y){   
      return x >= 0 && y >= 0 && x < n && y < n;
   }
   
   public static class Point implements Comparable<Point>{
      long value;
      int x;
      int y;
      public Point(long a, int b, int c){
         value = a;
         x = b;
         y = c;
      }
      public int compareTo(Point p){
         if(value > p.value) 
            return 1;
         if(value < p.value) 
            return -1;
         return 0;
      }
   }
}