//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1625{

   public static char[] path;
   
   public static int answer;
   
   public static boolean[][] board;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = 7;
      int N = 48;
      path = f.readLine().toCharArray();
      
      board = new boolean[n][n];
      board[0][0] = true;
      dothing(0,0,0);
      
      out.println(answer);
      
      
      
      out.close();
   }
   
   public static void dothing(int x, int y, int i){
      if(i == 48){
         if(x == 6 && y == 0){
            answer++;
         }
         return;
      }
      
      if(x == 6 && y == 0) return;
      
      board[x][y] = true;
      if(path[i] == 'R' || path[i] == '?'){
         if(in(x,y+1) && !board[x][y+1]){
            if(!(!cango(x,y+2) && cango(x+1,y+1) && cango(x-1,y+1))){
               dothing(x,y+1,i+1);
            }
         }
      }
      
      if(path[i] == 'L' || path[i] == '?'){
         if(in(x,y-1) && !board[x][y-1]){
            if(!(!cango(x,y-2) && cango(x+1,y-1) && cango(x-1,y-1))){
               dothing(x,y-1,i+1);
            }
         }
      }
      
      if(path[i] == 'U' || path[i] == '?'){
         if(in(x-1,y) && !board[x-1][y]){
            if(!(!cango(x-2,y) && cango(x-1,y-1) && cango(x-1,y+1))){
               dothing(x-1,y,i+1);
            }
         }
      }
      
      if(path[i] == 'D' || path[i] == '?'){
         if(in(x+1,y) && !board[x+1][y]){
            if(!(!cango(x+2,y) && cango(x+1,y-1) && cango(x+1,y+1))){
               dothing(x+1,y,i+1);
            }
         }
      }
      board[x][y] = false;
      
   }
   
   public static boolean cango(int x, int y){
      return in(x,y) && !board[x][y];
   }
   
   public static int ind(int x, int y){
      return x * 7 + y;
   }
   
   public static boolean in(int x, int y){
      return x >= 0 && x < 7 && y >= 0 && y < 7;
   }
   
      
}