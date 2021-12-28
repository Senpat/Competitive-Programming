//make sure to make new file!
import java.io.*;
import java.util.*;

public class B{
   
   public static char[][] board;
   public static int[][] nums;
   
   public static int[][] table;
   
   public static int[] dx;
   public static int[] dy;
   
   public static boolean works;
   
   public static HashSet<Integer> found;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      board = new char[6][6];
      
      int startx = -1;
      int starty = -1;
      
      for(int k = 0; k < 6; k++){
         String s = f.readLine();
         for(int j = 0; j < 6; j++){
            board[k][j] = s.charAt(j);
            if(board[k][j] == '#'){
               startx = k;
               starty = j;
            }
         }
      }
      
      table = new int[][] {{-1,-1,-1,-1},{2,3,4,5},{6,3,1,5},{2,6,4,1},{1,3,6,5},{2,1,4,6},{4,3,2,5}};
      nums = new int[6][6];
      
      dx = new int[]{0,1,0,-1};
      dy = new int[]{1,0,-1,0};
      
      nums[startx][starty] = 1;
      works = true;
      found = new HashSet<Integer>();
      found.add(1);
      ff(startx,starty);
      
      if(works){
         out.println("can fold");
      } else {
         out.println("cannot fold");
      }
      
      
      
      
      out.close();
   }
   
   public static int indexof(int[] array, int x){
      for(int k = 0; k < array.length; k++){
         if(array[k] == x) return k;
      }
      return -1;
   }
   
   public static boolean in(int x, int y){
      return x < 6 && x >= 0 && y < 6 && y >= 0;
   }
   
   public static void ff(int x, int y){
      int rotation = -1;
      for(int k = 0; k < 4; k++){
         if(in(x+dx[k],y+dy[k])){
            //get rotation
            if(nums[x+dx[k]][y+dy[k]] != 0){
               int currotation = indexof(table[nums[x][y]],nums[x+dx[k]][y+dy[k]]);
               if(rotation == -1){
                  rotation = (currotation-k + 4)%4;
               } else {
                  if(currotation != rotation){
                     works = false;
                     return;
                  }
               }
            }
         }
      }
      
      ArrayList<Integer> nextx = new ArrayList<Integer>();
      ArrayList<Integer> nexty = new ArrayList<Integer>();
      
      for(int k = 0; k < 4; k++){
         if(in(x+dx[k],y+dy[k])){
            if(nums[x+dx[k]][y+dy[k]] == 0 && board[x+dx[k]][y+dy[k]] == '#'){
               if(found.contains(table[nums[x][y]][(k+rotation)%4])){
                  works = false;
                  return;
               }
               found.add(table[nums[x][y]][(k+rotation)%4]);
               nums[x+dx[k]][y+dy[k]] = table[nums[x][y]][(k+rotation)%4];
               nextx.add(x+dx[k]);
               nexty.add(y+dy[k]);
            }
         }
      }
      
      for(int k = 0; k < nextx.size(); k++){
         ff(nextx.get(k),nexty.get(k));
      }
      
   }
      
}