//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1747{
   
   public static int n;
   public static int[] bits;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n+1];
      PriorityQueue<Point> pq = new PriorityQueue<Point>();
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         pq.add(new Point(k,array[k]));
      }
      
      bits = new int[n+1];
      
      long answer = 0L;
      
      while(!pq.isEmpty()){
         Point p = pq.poll();
         
         int left = (p.i-1) - psum(p.i);
         int right = (n-p.i) - (psum(n) - psum(p.i));
         
         answer += (long)Math.min(left,right);
         
         update(p.i,1);
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Point implements Comparable<Point>{
      int i;
      int x;
      public Point(int a, int b){
         i = a;
         x = b;
      }
      public int compareTo(Point p){
         return x-p.x;
      }
   }
   
   public static void update(int i, int x){
      for(; i <= n; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static int psum(int i){
      int sum = 0;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   
   }

   
      
}