/*
TASK: ttwo
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class ttwo{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("ttwo.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
      
      int N = 10;
      
      char[][] board = new char[N][N];
      
      //0 -> north, 1 -> east, 2 -> south, 3 -> west
      int[] dx = new int[]{-1,0,1,0};
      int[] dy = new int[]{0,1,0,-1};
      
      int cx = -1;
      int cy = -1;
      int fx = -1;
      int fy = -1;
      
      for(int k = 0; k < N; k++){
         String s = f.readLine();
         for(int j = 0; j < N; j++){
            board[k][j] = s.charAt(j);
            if(board[k][j] == 'C'){
               cx = k;
               cy = j;
            }
            if(board[k][j] == 'F'){
               fx = k;
               fy = j;
            }
         }
      }
      
      
      boolean[][][][][][] seen = new boolean[N][N][N][N][4][4];
      
      int time = 0;
      
      int cdir = 0;
      int fdir = 0;
      
      while(true){
         if(cx == fx && cy == fy){
            break;
         }
         
         if(seen[cx][cy][fx][fy][cdir][fdir]){
            time = -1;
            break;
         }
         
         seen[cx][cy][fx][fy][cdir][fdir] = true;
         
         if(!in(cx+dx[cdir],cy+dy[cdir]) || board[cx+dx[cdir]][cy+dy[cdir]] == '*'){
            cdir = (cdir + 1)%4;
         } else {
            cx += dx[cdir];
            cy += dy[cdir];
         }
         
         if(!in(fx+dx[fdir],fy+dy[fdir]) || board[fx+dx[fdir]][fy+dy[fdir]] == '*'){
            fdir = (fdir + 1)%4;
         } else {
            fx += dx[fdir];
            fy += dy[fdir];
         }
         
         
         
         time++;
      }
      
      if(time == -1){
         out.println(0);
      } else {
         out.println(time);
      }
        
      out.close();
   }
   
   public static boolean in(int x, int y){
      return x >= 0 && x < 10 && y >= 0 && y < 10;
   }
      
}