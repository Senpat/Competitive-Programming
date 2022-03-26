//make sure to make new file!
import java.io.*;
import java.util.*;

public class D124b{

   public static int mul2 = 18;
   public static int mask = (1 << 18)-1;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      int n = Integer.parseInt(f.readLine());
      
      Point[] points = new Point[n];
      Point[] array = new Point[n];
      HashMap<Point,Integer> hmap = new HashMap<Point,Integer>();
         
      for(int k = 0; k < n; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         array[k] = new Point(a,b,k);
         points[k] = array[k];
         hmap.put(array[k],k);
      }
      
      
      
      Point[][] closest = new Point[n][4];
      
      ArrayList<Comparator<Point>> comparators = new ArrayList<Comparator<Point>>(Arrays.asList(
         new Comparator<Point>(){
            public int compare(Point p1, Point p2){
               int x1 = p1.sort[0];
               int x2 = p2.sort[0];
               
               return x1-x2;
            }
         },
             new Comparator<Point>(){
                public int compare(Point p1, Point p2){
                   int x1 = p1.sort[1];
                   int x2 = p2.sort[1];
                
                   return x1-x2;
                }
             },
             new Comparator<Point>(){
                public int compare(Point p1, Point p2){
                   int x1 = p1.sort[2];
                   int x2 = p2.sort[2];
                
                   return x1-x2;
                }
             },
             new Comparator<Point>(){
                public int compare(Point p1, Point p2){
                   int x1 = p1.sort[3];
                   int x2 = p2.sort[3];
                
                   return x1-x2;
                }
             }));
      
      
      int[] dx = new int[]{-1,1,-1,1};
      int[] dy = new int[]{-1,-1,1,1};
      
      
      for(int side = 0; side < 4; side++){
         Arrays.sort(array, comparators.get(side));
         for(int k = 0; k < n; k++){
            Point p = array[k];
            Point px = new Point(p.x+dx[side],p.y,-1);
            Point py = new Point(p.x,p.y+dy[side],-1);
            if(!hmap.containsKey(px)){
               closest[p.index][side] = px;
            } else if(!hmap.containsKey(py)){
               closest[p.index][side] = py;
            } else {
               int xi = hmap.get(px);
               int yi = hmap.get(py);
               
               if(p.dist(closest[xi][side]) < p.dist(closest[yi][side])){
                  closest[p.index][side] = closest[xi][side];
               } else {
                  closest[p.index][side] = closest[yi][side];
               }
            }
         }
      
      }
      
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < n; k++){
         int mini = 0;
         for(int j = 1; j < 4; j++){
            if(points[k].dist(closest[k][mini]) > points[k].dist(closest[k][j])){
               mini = j;
            }
         }
         
         sj.add(closest[k][mini].toString());
      }
      out.println(sj.toString());
      
      
         
         
            
      
      
      
      out.close();
   }
   
   
   public static class Point{
      int x;
      int y;
      int index;
      int[] sort;
      public Point(int a, int b, int c){
         x = a;
         y = b;
         index = c;
         
         sort = new int[]{x+y,200000-x+y,x+200000-y,200000-x+200000-y};
      }
      public int hashCode(){
         return toString().hashCode();
      }
      public boolean equals(Object o){
         Point p = (Point)o;
         return x == p.x && y == p.y;
      }
      public int dist(Point p){
         return Math.abs(x-p.x) + Math.abs(y-p.y);
      }
      public String toString(){
         return "" + x + " " + y;
      }
   }   
   
   
   
   public static long hash(long x, long y){
      return (x << mul2) + y;
   }
   
   public static long getx(long x){
      return x >> mul2;
   }
   
   public static long gety(long x){
      return x & mask;
   }
      
}