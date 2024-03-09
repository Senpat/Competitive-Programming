//make sure to make new file!
import java.io.*;
import java.util.*;

public class I{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int N = n*m;
         
         Point[] points = new Point[N];
         for(int k = 0; k < n; k++){
            st = new StringTokenizer(f.readLine());
            for(int j = 0; j < m; j++){
               int i = Integer.parseInt(st.nextToken());
               points[i] = new Point(k,j,i);
            }
         }
         
         Arrays.sort(points);
         
         
         int l = 0;
         int r = N;
         int ans = -1;
         while(l <= r){
            int mid = l + (r-l)/2;
            
            //check if mex could be mid
            ArrayList<Point> alist = new ArrayList<Point>();
            for(int k = 0; k < N; k++){
               if(points[k].i < mid){
                  alist.add(points[k]);
               }
            }
            
            //check for contradictions
            boolean fail = false;
            for(int k = 1; k < alist.size(); k++){
               if(alist.get(k-1).x > alist.get(k).x || alist.get(k-1).y > alist.get(k).y){
                  fail = true;
                  break;
               }
            }
            
            if(fail){
               r = mid-1;
            } else {
               ans = mid;
               l = mid+1;
            }
            
            
            
         }
         
         out.println(ans);
      

      }
      
      
      
      
      out.close();
   }
   
   public static class Point implements Comparable<Point>{
      int x;
      int y;
      int i;
      
      public Point(int a, int b, int c){
         x = a;
         y = b;
         i = c;
      }
      
      public int compareTo(Point p){
         return x+y - (p.x+p.y);
      }
   }
   
      
}