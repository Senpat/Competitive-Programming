//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class F{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      
      ArrayList<Point> allpoints = new ArrayList<Point>();
      
      Point minpoint = new Point(-1,-1);
      
      int maxy = Integer.MIN_VALUE;
      int miny = Integer.MAX_VALUE;
      for(int k = 0; k < n; k++){
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         allpoints.add(new Point(x,y));
         
         if(k == 0){
            minpoint = new Point(x,y);
         } else {
            if(x < minpoint.x || (x == minpoint.x && y < minpoint.y)){
               minpoint  = new Point(x,y);
            }
         }
         
         maxy = Math.max(maxy,y);
         miny = Math.min(miny,y);
      }
      
      ArrayList<Point> order = new ArrayList<Point>();
      ArrayList<Point> other = new ArrayList<Point>();
      for(Point p : allpoints){
         if(p.x == minpoint.x) order.add(p);
         else other.add(p);
      }
      
      Collections.sort(order, (p1,p2) -> p1.y-p2.y);
      
      final int mx = minpoint.x;
      final int my = minpoint.y;
      final int maxyf = maxy;
      final int minyf = miny;
      //sort in clockwise order
      Collections.sort(other, new Comparator<Point>(){
         public int compare(Point p1, Point p2){
            //sort by their angle
            long x1 = (long)(p1.x-mx);
            long y1 = (long)(p1.y-my);
            long x2 = (long)(p2.x-mx);
            long y2 = (long)(p2.y-my);
            
            if(y1*x2 > y2*x1){
               return -1;
            } else if(y1*x2 == y2*x1){
               //when y1 == 0, it can be top edge or bottom edge
               if(y1 > 0 || (y1 == 0 && maxyf == my)){
                  //smaller x come first
                  return (int)(x1-x2);
               } else if(y1 < 0 || (y1 == 0 && minyf == my)){
                  //bigger x come first
                  return (int)(x2-x1);
               }
               //won't happen
               return 0;
            } else {
               return 1;
            }
         }
      });
      
      for(Point p : other){
         order.add(p);
      }
      
      //calculate answer twice, since first element might be a part of a line with the last element
      int answer1 = calcanswer(order);
      order.add(order.get(0));
      order.remove(0);
      int answer2 = calcanswer(order);
      
      int answer = Math.min(answer1,answer2); 
      
      out.println(answer);
      
      out.close();
   }
   
   public static int calcanswer(ArrayList<Point> order){
      int n = order.size();
      
      //precompute collinear points
      //line[x] = earliest index such that all points after that index are collinear with x
      int[] line = new int[n];
      line[0] = 0;
      line[1] = 0;
      for(int k = 2; k < n; k++){
         Point slope1 = getslope(order.get(k-2),order.get(k-1));
         Point slope2 = getslope(order.get(k-1),order.get(k));
         
         if(slope1.equals(slope2)){
            line[k] = line[k-1];
         } else {
            line[k] = k-1;
         }
      }
      
      //dp[x][y] = min straight lines to cover first x points and y points not covered
      int[][] dp = new int[n][2];
      for(int k = 0; k < n; k++){
         dp[k][0] = Integer.MAX_VALUE;
         dp[k][1] = Integer.MAX_VALUE;
      }
      dp[0][0] = 1;
      dp[0][1] = 0;
      
      for(int k = 1; k < n; k++){
         //use k
         if(line[k] == 0){
            dp[k][0] = 1;
            dp[k][1] = 1;
         } else {
            dp[k][0] = dp[line[k]-1][0]+1;
            dp[k][1] = dp[line[k]-1][1]+1;
         }
         
         //don't use k
         dp[k][0] = Math.min(dp[k][0],dp[k-1][1]+1);
         dp[k][1] = Math.min(dp[k][1],dp[k-1][0]);
      }
      
      int answer = Math.min(dp[n-1][0],dp[n-1][1]+1);
      return answer;
   }
   
   public static Point getslope(Point p1, Point p2){
      int dx = p1.x-p2.x;
      int dy = p1.y-p2.y;
      
      if(dx == 0){
         return new Point(0,dy/(Math.abs(dy)));
      }
      if(dy == 0){
         return new Point(dx/(Math.abs(dx)),0);
      }
      
      int gcd = gcd(Math.abs(dx),Math.abs(dy));
      return new Point(dx/gcd,dy/gcd);
   }
   
   public static int gcd(int a, int b){
      if(a > b){
         if(b == 0) return a;
         return gcd(a%b,b);
      } else if(a < b){
         if(a == 0) return b;
         return gcd(b%a,a);
      }
      return a;
   }
   
   
   public static class Point{
      int x;
      int y;
      public Point(int a, int b){
         x = a;
         y = b;
      }
      
      public boolean equals(Point p){
         return x == p.x && y == p.y;
      }
      
      public String toString(){
         return "" + x + " " + y;
      }
   }
        
}