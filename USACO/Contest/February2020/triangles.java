/*
TASK: triangles
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class triangles2{
  
   public static int n;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("triangles.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("triangles.out")));
      
      
      n = Integer.parseInt(f.readLine());
      
      boolean[][] board = new boolean[n][n];
      ArrayList<Pair> cows = new ArrayList<Pair>();
      
      ArrayList<ArrayList<ArrayList<Pair>>> diags = new ArrayList<ArrayList<ArrayList<Pair>>>(n);
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         diags.add(new ArrayList<ArrayList<Pair>>(n));
         for(int j = 0; j < n; j++){
            if(s.charAt(j) == '*'){
               board[k][j] = true;
               cows.add(new Pair(k,j));
            }
            diags.get(k).add(new ArrayList<Pair>());
         }
      }
      
      //fill diags
      for(Pair p : cows){
         
         int ix = p.x;
         int iy = p.y;
         
         //up left (0,0) is top left
         ix--;
         iy--;
         while(in(ix,iy)){
            if(board[ix][iy]){
               diags.get(ix).get(iy).add(p);
            }
            ix--;
            iy--;
         }
         
         //up right
         ix = p.x+1;
         iy = p.y-1;
         while(in(ix,iy)){
            if(board[ix][iy]){
               diags.get(ix).get(iy).add(p);
            }
            ix++;
            iy--;
         }
         
         //down left
         ix = p.x-1;
         iy = p.y+1;
         while(in(ix,iy)){
            if(board[ix][iy]){
               diags.get(ix).get(iy).add(p);
            }
            ix--;
            iy++;
         }
         
         //down right
         ix = p.x+1;
         iy = p.y+1;
         while(in(ix,iy)){
            if(board[ix][iy]){
               diags.get(ix).get(iy).add(p);
            }
            ix++;
            iy++;
         }
      }
      

      //HashSet<Triple> answers = new HashSet<Triple>();
      long answer = 0L;
      
      long flatlines = 0L;          //means two diagonals, occurs when x or y are same
      for(Pair p1 : cows){
         for(Pair p2 : diags.get(p1.x).get(p1.y)){
            int x1 = p1.x;
            int y1 = p1.y;
            int x2 = p2.x;
            int y2 = p2.y;
            if(p2.y > p1.y) continue;
            
            int d = dist(p1,p2);
            
            //draw square around p1 of size p2
            
            for(int dx = 0; dx < d; dx++){
               int dy = d-dx;
               
               if(in(p1.x+dx,p1.y+dy) && board[p1.x+dx][p1.y+dy] && (p1.x+dx != p2.x || p1.y+dy != p2.y) && dist(p1.x+dx,p1.y+dy,p2) == d){
                  answer++;
                  if(p1.x+dx == p1.x || p1.x+dx == p2.x || p1.y+dy == p1.y || p1.y+dy == p2.y){
                     flatlines++;
                  }
               }
               if(in(p1.x-dx,p1.y-dy) && board[p1.x-dx][p1.y-dy] && (p1.x-dx != p2.x || p1.y-dy != p2.y) && dist(p1.x-dx,p1.y-dy,p2) == d){
                  answer++;
                  if(p1.x-dx == p1.x || p1.x-dx == p2.x || p1.y-dy == p1.y || p1.y-dy == p2.y){
                     flatlines++;
                  }
                  
               }
               if(in(p1.x+dy,p1.y-dx) && board[p1.x+dy][p1.y-dx] && (p1.x+dy != p2.x || p1.y-dx != p2.y) && dist(p1.x+dy,p1.y-dx,p2) == d){
                  answer++;
                  if(p1.x+dy == p1.x || p1.x+dy== p2.x || p1.y-dx == p1.y || p1.y-dx == p2.y){
                     flatlines++;
                  }
               }
               if(in(p1.x-dy,p1.y+dx) && board[p1.x-dy][p1.y+dx] && (p1.x-dy != p2.x || p1.y+dx != p2.y) && dist(p1.x-dy,p1.y+dx,p2) == d){
                  answer++;
                  if(p1.x-dy == p1.x || p1.x-dy == p2.x || p1.y+dx == p1.y || p1.y+dx == p2.y){
                     flatlines++;
                  }
               }
            }
         }
      }
           
      
      answer -= flatlines/2;
      
      out.println(answer);
      System.out.println(answer);
        
        
      out.close();
   }
   
   public static int dist(Pair p1, Pair p2){
      return Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y);
   }
   public static int dist(int x, int y, Pair p2){
      return Math.abs(x-p2.x) + Math.abs(y-p2.y);
   }
   public static boolean in(int x, int y){
      return x >= 0 && x < n && y >= 0 && y < n;
   }
   
   public static int cantorpairing(int a, int b){
      return (a+b)*(a+b+1)>>1 + a;
   }
      
   public static class Pair{
      int x;
      int y;
      public Pair(int a, int b){
         x = a;
         y = b;
      }
      
      public String toString(){
         return x + " " + y;
      }
   }
      
}