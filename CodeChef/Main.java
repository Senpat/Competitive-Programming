//MINDSUM
import java.io.*;
import java.util.*;

public class Main{
   
   public static HashSet<Integer> hs;
   public static int min,minsteps;
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      for(int k = 0; k < n; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         hs = new HashSet<Integer>();
         min = Integer.MAX_VALUE;
         minsteps = 20;
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         bfs(a,b,0);
         
         out.println(min + " " + minsteps);
         
      }
      
      
      out.close();
   }
   
   public static void bfs(int n, int d, int step){
      if(step <= 15){
         if(n<min){
            min = n;
            minsteps = step;
         } else if(min == n){
            minsteps = Math.min(step,minsteps);
         }
         bfs(dsum(n),d,step+1);
         bfs(n+d,d,step+1);
      }
         
   }
   
   public static int dsum(int n){
      String s = Integer.toString(n);
      int count = 0;
      for(int k = 0; k < s.length(); k++){
         count += s.charAt(k)-'0';
      }
      return count;
      
      
   }
   
}