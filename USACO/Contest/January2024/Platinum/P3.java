//make sure to make new file!
import java.io.*;
import java.util.*;
//N <= 3000 subtask
public class P3{
   
   public static long MOD = 1000000007L;
   public static long[] pow2;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 200005;
      pow2 = new long[N];
      pow2[0] = 1L;
      for(int k = 1; k < N; k++){
         pow2[k] = (2L * pow2[k-1] + MOD)%MOD;
      }
      
      int n = Integer.parseInt(f.readLine());
      long nl = (long)n;
      
      Point[] points = new Point[n];
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         
         points[k] = new Point(x,y);
      }
      
      long answer = 0L;
      
      //calc x boundaries and y boundaries
      //1 point at every x/y coordinate
      for(int k = 1; k < n; k++){
         long right = ((pow2[n-k]-1)%MOD + MOD)%MOD;
         long cur = (pow2[k-1] * right + MOD)%MOD;
         answer = (answer + cur + MOD)%MOD;
      }
      answer = (2L * answer + MOD)%MOD;         //y same as x
      
      long sub1 = calcsub(points);
      answer = ((answer - sub1)%MOD + MOD)%MOD;
      
      //flip x coordinates
      for(int k = 0; k < n; k++){
         points[k].x = n+1 - points[k].x;
      }
      
      long sub2 = calcsub(points);
      answer = ((answer - sub2)%MOD + MOD)%MOD;
      //out.println(sub1 + " " + sub2);
      //flip blue and red team
      answer = (2L * answer + MOD)%MOD;
      
      out.println(answer);
      
      
      out.close();
   }
   
   
   //how many ways to split teams such that one is in upper left and another is on bottom right
   public static long calcsub(Point[] points){
      int n = points.length;
      
      boolean[][] contains = new boolean[n+1][n+1];
      int[][] psumx = new int[n+1][n+1];
      int[][] psumy = new int[n+1][n+1];
      
      for(int k = 0; k < n; k++){
         int x = points[k].x;
         int y = points[k].y;
         
         contains[x][y] = true;
         psumx[x][y] = 1;
         psumy[x][y] = 1;
      }
      
      for(int k = 1; k <= n; k++){
         for(int j = 1; j <= n; j++){
            psumx[k][j] += psumx[k][j-1];
            psumy[k][j] += psumy[k-1][j];
         }
      }
      
      long ret = 0L;
      for(int k = 1; k < n; k++){
         int above = 0;
         int below = n-k;
         
         //point with x == k has been reached
         boolean reachk = false;
         for(int j = 1; j < n; j++){
            //split teams such that upper left x <= k and y <= j, bottom right x > k and y > j
            above += psumy[k][j];
            below -= 1-psumy[k][j];
            if(contains[k][j]) reachk = true;
            
            if(contains[k][j]){
               long cur = (pow2[above-1] * (pow2[below]-1) + MOD)%MOD;
               ret = (ret + cur + MOD)%MOD;
            } else if(psumy[k][j] > 0 && reachk){
               //System.out.println("-2 " + k + " " + j);
               long cur = (pow2[above-2] * (pow2[below]-1) + MOD)%MOD;
               ret = (ret + cur + MOD)%MOD;
            }
         }
      }
      
      return ret;
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