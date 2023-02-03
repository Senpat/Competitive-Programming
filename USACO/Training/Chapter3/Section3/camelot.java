/*
TASK: camelot
LANG: JAVA
*/

import java.io.*;
import java.util.*;
//still TLE
class camelot{
   
   public static int INF = 1000000;

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
         if(s == null) 
            break;
         
         st = new StringTokenizer(s);
         
         while(st.hasMoreTokens()){
            int c = ctoi(st.nextToken().charAt(0));
            int r = Integer.parseInt(st.nextToken());
            
            if(kingx == -1){
               kingx = r;
               kingy = c;
            } else {
               knights.add(new Point(r,c));
            }
         }
      }
      
      //dist[sx][sy][ex][ey][x] fastest way for a knight to go from (sx,sy) to (ex,ey)
      //x = 0 -> doesn't pick up king, x = 1 -> picks up king
      int[][][][][] dist = new int[n+1][m+1][n+1][m+1][2];
      for(int k = 1; k <= n; k++){
         for(int j = 1; j <= m; j++){
            for(int h = 1; h <= n; h++){
               for(int g = 1; g <= m; g++){
                  dist[k][j][h][g][0] = INF;
                  dist[k][j][h][g][1] = INF;
               }
            }
         }
      }
      
      for(int sx = 1; sx <= n; sx++){
         for(int sy = 1; sy <= m; sy++){
            PriorityQueue<Point> pq = new PriorityQueue<Point>();
            dist[sx][sy][sx][sy][0] = 0;
            pq.add(new Point(sx,sy,0,0));
            if(kingx == sx && kingy == sy){
               dist[sx][sy][sx][sy][1] = 0;
               pq.add(new Point(sx,sy,1,0));
            }
            
            while(!pq.isEmpty()){
               Point p = pq.poll();
               
               if(dist[sx][sy][p.x][p.y][p.z] != p.w) continue;
               
               for(int d = 0; d < 8; d++){
                  int newx = p.x+dx[d];
                  int newy = p.y+dy[d];
                  if(!in(newx,newy)) 
                     continue;
                  
                  int newd = dist[sx][sy][p.x][p.y][p.z]+1;
                  if(dist[sx][sy][newx][newy][p.z] > newd) {
                     dist[sx][sy][newx][newy][p.z] = newd;
                     pq.add(new Point(newx,newy,p.z,newd));
                  }
                  
                  if(p.z == 0){
                     newd = dist[sx][sy][p.x][p.y][p.z]+1+Math.max(Math.abs(kingx-newx),Math.abs(kingy-newy));
                     if(dist[sx][sy][newx][newy][1] > newd) {
                        dist[sx][sy][newx][newy][1] = newd;
                        pq.add(new Point(newx,newy,1,newd));
                     }
                  }   
               }
            }
            
         }
      }
      
      int answer = INF;
      
      for(int sx = 1; sx <= n; sx++){
         for(int sy = 1; sy <= m; sy++){
            int sum = 0;
            int minadd = INF;
            for(Point knight : knights){
               sum += dist[sx][sy][knight.x][knight.y][0];  
               minadd = Math.min(minadd,dist[sx][sy][knight.x][knight.y][1]-dist[sx][sy][knight.x][knight.y][0]);
            }
            answer = Math.min(answer,sum + Math.max(Math.abs(kingx-sx),Math.abs(kingy-sy)));
            answer = Math.min(answer,sum + minadd);
         }
      }
      
      out.println(answer);
        
      out.close();
   }
   
   public static boolean in(int x, int y){
      return x >= 1 && x <= n && y >= 1 && y <= m;
   }
   
   public static int ctoi(char ch){
      return ch-'A'+1;
   }
   
   public static class Point implements Comparable<Point>{
      int x;
      int y;
      int z;
      int w;
      public Point(int a, int b){
         x = a;
         y = b;  
      }
      public Point(int a, int b, int c,int d){
         x = a;
         y = b;
         z = c;
         w = d;
      }
      
      public int compareTo(Point p){
         return w-p.w;
      }
      
   }
   
}