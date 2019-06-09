//make sure to make new file!
import java.io.*;
import java.util.*;

public class J{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      String s = f.readLine();
      
      PriorityQueue<Square> pq = new PriorityQueue<Square>();

      boolean[][] seen = new boolean[9][9];
      
      int x = s.charAt(0)-96;
      int y = Character.getNumericValue(s.charAt(1));
      
      for(int dx = -1; dx <= 1; dx+=2){
         for(int dy = -1; dy <= 1; dy+=2){
            if(in(x+n*dx,y+m*dy)){
               if(!seen[x+n*dx][y+m*dy]){
                  seen[x+n*dx][y+m*dy] = true;
                  pq.add(new Square(x+n*dx,y+m*dy));
               }
            }
            if(in(x+m*dx,y+n*dy)){
               if(!seen[x+m*dx][y+n*dy]){
                  seen[x+m*dx][y+n*dy] = true;
                  pq.add(new Square(x+m*dx,y+n*dy));
               }
            }
         }
      }
      
      out.println(pq.size());
      while(!pq.isEmpty()){
         out.print(pq.poll() + " ");
      }
      
      
      
      out.close();
   }
   
   public static boolean in(int x, int y){
      return x >= 1 && y >= 1 && x <= 8 && y <= 8;
   }
   
   public static class Square implements Comparable<Square>{
      int x;
      int y;
      
      public Square(int a, int b){
         x = a;
         y = b;
      }
      
      public int compareTo(Square s){
         if(x == s.x) return y-s.y;
         return x-s.x;
      }
      
      public String toString(){
         return "" + (char)(x+96) + y;
      }
   }
   
      
}