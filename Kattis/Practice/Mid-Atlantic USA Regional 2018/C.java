//make sure to make new file!
import java.io.*;
import java.util.*;

public class C{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] bip;
   public static boolean bibool;
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Point[] points = new Point[n];
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         points[k] = new Point(a,b);
      }
      
      
      
      int l = 0;
      int r = 2000;
      int ans = -1;
      
      while (l <= r){
         int mid = l + (r-l)/2;
         
         if(check(points,mid)){
            ans = mid;
            r = mid-1;
         } else {
            l = mid+1;
         }
      }
      
      
      out.println(ans);
      
      
      
      
      out.close();
   }
   
   public static boolean check(Point[] points, int i){
      int n = points.length;
      adj = new ArrayList<ArrayList<Integer>>(n);
      
      for(int k = 0; k < n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n; k++){
         for(int j = k+1; j < n; j++){
            if(dis(points[k],points[j]) > i){
               adj.get(k).add(j);
               adj.get(j).add(k);
            }
         }
      }
      
      bip = new int[n];
      
      for(int k = 0; k < n; k++){
         if(bip[k] == 0){
            bibool = true;
            checkbi(k,1);
            if(!bibool) return false;
         }
      }
      
      return true;
   }
   
   public static void checkbi(int v, int x){
      
      if(bip[v] == 0){
         bip[v] = x;
      } else {
         if(bip[v] != x){
            bibool = false;
            
         }
         return;
      }
      
      for(int nei : adj.get(v)){
         checkbi(nei,3-x);
      }
   }
   
      
   
   
   public static int dis(Point p1, Point p2){
      return Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y);
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