//make sure to make new file!
import java.io.*;
import java.util.*;

public class magicalmystery{
   
   public static int[][] board;
   public static int[] rowsum;
   public static int[] colsum;
   public static HashSet<Integer> given;
   
   public static boolean solved;
   
   public static int[] dx;
   public static int[] dy;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      dx = new int[]{2,1,-1,-2,-2,-1,1,2};
      dy = new int[]{1,2,2,1,-1,-2,-2,-1};
      
      board = new int[8][8];
      given = new HashSet<Integer>();
      
      int startx = -1;
      int starty = -1;
      
      for(int k = 0; k < 8; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int j = 0; j < 8; j++){
            board[k][j] = Integer.parseInt(st.nextToken());
            given.add(board[k][j]);
            if(board[k][j] == 1){
               startx = k;
               starty = j;
            }
         }
      }
      
      rowsum = new int[8];
      colsum = new int[8];
      
      solved = false;
      dothing(startx,starty,1);
      
      StringBuilder sb = new StringBuilder();
      for(int k = 0; k < 8; k++){
         for(int j = 0; j < 8; j++){
            sb.append(board[k][j] + " ");
         }
         sb.append("\n");
      }
      
      out.println(sb.toString());
      
      
      
      
      
      out.close();
   }
   
   public static void dothing(int x, int y, int i){
      if(solved) return;
      //add to rowsum and colsum
      rowsum[x] += 65-i;
      colsum[y] += 65-i;
      board[x][y] = i;
      
      if(i == 64){
         boolean fail = false;
         for(int k = 0; k < 8; k++){
            if(rowsum[k] != 260 || colsum[k] != 260){
               fail = true;
               break;
            }
            
         }
         if(!fail) solved = true;     
      } else {
         if(given.contains(i+1)){
            //find it
            for(int k = 0; k < 8; k++){
               if(in(x+dx[k],y+dy[k]) && board[x+dx[k]][y+dy[k]] == i+1 && rowsum[x+dx[k]] + 65-(i+1) <= 260 && colsum[y+dy[k]] + 65-(i+1) <= 260){
                  dothing(x+dx[k],y+dy[k],i+1);
               }
            }
         } else {
            for(int k = 0; k < 8; k++){
               if(in(x+dx[k],y+dy[k]) && board[x+dx[k]][y+dy[k]] == -1 && rowsum[x+dx[k]] + 65-(i+1) <= 260 && colsum[y+dy[k]] + 65-(i+1) <= 260){
                  dothing(x+dx[k],y+dy[k],i+1);
               }
            }
         }
      
      
      }
      
      if(!solved){
         //undo
         rowsum[x] -= 65-i;
         colsum[y] -= 65-i;
         if(!given.contains(i)) board[x][y] = -1;  
         
      }
   }
         
   
   public static boolean in(int x, int y){
      return x >= 0 && x < 8 && y >= 0 && y < 8;
   }
      
}