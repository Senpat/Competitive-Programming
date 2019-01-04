//make sure to make new file!
import java.io.*;
import java.util.*;

public class BGB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Point[] loc = new Point[n];
      
      for(int k = 0; k < n; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         loc[k] = new Point(a,b);
      }
      
      Point[] clues = new Point[n];
      HashSet<String> hs = new HashSet<String>();
      
      for(int k = 0; k < n; k++){
         
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         
         clues[k] = new Point(a,b);
         Point p = new Point(a,b);
         hs.add(p.toString());
      }
      
      //try for every clue
      for(int k = 0; k < n; k++){
         int fx = loc[0].x + clues[k].x;
         int fy = loc[0].y + clues[k].y;
         
         hs.remove(clues[k]);
         
         boolean found = true;
         for(int j = 1; j < n; j++){
            int dx = fx - loc[j].x;
            int dy = fy - loc[j].y;
            Point cur = new Point(dx,dy);
            if(hs.contains(cur.toString())){
               hs.remove(cur.toString());
            } else {
               found = false;
               break;
            }
         }
         
         if(found){
            out.println(fx + " " + fy);
         } else {
            for(int j = 0; j < n; j++){
               hs.add(clues[j].toString());
            }
         }
      }
      
      
      
      out.close();
   }
   
   public static class Point{
      int x;
      int y;
      public Point(int a, int b){
         x = a;
         y = b;
      }
      public String toString(){
         return x + " " + y;
      }
   }
   
}