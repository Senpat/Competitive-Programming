/*
TASK: camelot
LANG: JAVA
*/

import java.io.*;
import java.util.*;
// first attempt, O((RC)^3), TLE but AC with C++
class camelotb{

   public static int n,m;
   
   public static int[] dx = new int[]{2,1,-1,-2,-2,-1,1,2};
   public static int[] dy = new int[]{1,2,2,1,-1,-2,-2,-1};
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("camelot.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("camelot.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      int kingx = -1;
      int kingy = -1;
      
      ArrayList<Point> knights = new ArrayList<Point>();
      
      while(true){
         String s = f.readLine();
         if(s == null){
            break;
         }
         
         st = new StringTokenizer(s);
         
         while(st.hasMoreTokens()){
            int c = ctoi(st.nextToken().charAt(0));
            int r = Integer.parseInt(st.nextToken());
         
            if(kingx == -1){
               kingx = r;
               kingy = c;
               continue;
            }
            
            knights.add(new Point(r,c));
         
         }
      }
      
      //precompute distances
      int[][][][] dist = new int[n+1][m+1][n+1][m+1];
      for(int k = 1; k <= n; k++){
         for(int j = 0; j <= m; j++){
            for(int h = 0; h <= n; h++){
               for(int g = 0; g <= m; g++){
                  dist[k][j][h][g] = Integer.MAX_VALUE;         
               }
            }
         }
      }
      
      for(int sx = 1; sx <= n; sx++){
         for(int sy = 1; sy <= m; sy++){
            dist[sx][sy][sx][sy] = 0;
            Queue<Point> q = new LinkedList<Point>();
            q.add(new Point(sx,sy));
            
            while(!q.isEmpty()){
               Point p = q.poll();
               
               for(int d = 0; d < 8; d++){
                  int newx = p.x+dx[d];
                  int newy = p.y+dy[d];
                  if(!in(newx,newy)) continue;
                  if(dist[sx][sy][newx][newy] != Integer.MAX_VALUE) continue;
                  
                  dist[sx][sy][newx][newy] = dist[sx][sy][p.x][p.y]+1;
                  q.add(new Point(newx,newy));
               }
            }   
         }
      }
      
      int answer = Integer.MAX_VALUE;
      //pick ending square
      for(int ex = 1; ex <= n; ex++){
         for(int ey = 1; ey <= m; ey++){
            //initial answer
            int sum = 0;
            for(Point knight : knights){
               sum += dist[ex][ey][knight.x][knight.y];
            }
            if(sum >= answer) continue;
            answer = Math.min(answer, sum+Math.max(Math.abs(ex-kingx),Math.abs(ey-kingy)));
            
            //pick which knight to meet king
            for(Point knight : knights){
               int cur = sum-dist[ex][ey][knight.x][knight.y];
               if(cur >= answer) continue;
               //pick where to meet knight
               for(int mx = 1; mx <= n; mx++){
                  for(int my = 1; my <= m; my++){
                     int curanswer = sum-dist[ex][ey][knight.x][knight.y];
                     //king move to meeting square
                     curanswer += Math.max(Math.abs(kingx-mx),Math.abs(kingy-my));
                     //knight move to meeting square, then move to ending square
                     curanswer += dist[knight.x][knight.y][mx][my] + dist[mx][my][ex][ey];
                     answer = Math.min(answer,curanswer);
                  }
               }
            }
         }
      }
        
      out.println(answer);
      
      out.close();
   }
   
   public static boolean in(int x, int y){
      return x >= 1 && x <= n && y >= 1 && y <= n;
   }
   
   public static int ctoi(char ch){
      return ch-'A'+1;
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