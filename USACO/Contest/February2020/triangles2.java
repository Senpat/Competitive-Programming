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
            if(p2.y > p1.y) 
               continue;
            
            int d = dist(p1,p2)/2;
            
            int x;
            int y;
            if(p2.x > p1.x){
               
               for(int k = 0; k <= d; k++){
                  x = p2.x+k;
                  y = p1.y+d-k;
                  
                  if(in(x,y) && board[x][y]){
                     answer++;
                     if(k==0 || k == d){
                        flatlines++;
                     }
                  }
                  
                  x = p1.x-d+k;
                  y = p2.y-k;
                  
                  if(in(x,y) && board[x][y]){
                     answer++;
                     if(k==0 || k == d){
                        flatlines++;
                     }
                  }
               }
               
            } else {
               for(int k = 0; k <= d; k++){
                  x = p2.x-d+k;
                  y = p1.y+k;
                  
                  if(in(x,y) && board[x][y]){
                     answer++;
                     if(k==0 || k == d){
                        flatlines++;
                     }
                  }
                  
                  x = p1.x+k;
                  y = p2.y-d+k;
                  
                  if(in(x,y) && board[x][y]){
                     answer++;
                     if(k==0 || k == d){
                        flatlines++;
                     }
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