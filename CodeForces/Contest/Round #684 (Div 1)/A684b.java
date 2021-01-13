//make sure to make new file!
import java.io.*;
import java.util.*;

public class A684b{

   public static ArrayList<Move> answer;
   public static int n;
   public static int m;
   
   public static int[][] board;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());
         
         board = new int[n][m];
         for(int k = 0; k < n; k++){
            char[] c = f.readLine().toCharArray();
            for(int j = 0; j < m; j++){
               board[k][j] = Character.getNumericValue(c[j]);
            }
         }
         
         answer = new ArrayList<Move>();
         
         if(m%2 == 1){
            //make right column all 0
            for(int k = 0; k < n; k++){
               if(board[k][m-1] == 1){
                  if(k < n-1){
                     addmove(new Move(k,m-1,k,m-2,k+1,m-2));
                  } else {
                     addmove(new Move(k,m-1,k,m-2,k-1,m-2));
                  }
               }
            }
            m--;
         }
         
         for(int j = 0; j < m; j += 2){
            for(int k = 0; k < n-2; k++){
               if(board[k][j] == 1){
                  addmove(new Move(k,j,k+1,j,k+1,j+1));
               }
               if(board[k][j+1] == 1){
                  addmove(new Move(k,j+1,k+1,j+1,k+1,j));
               }
            }
            //last 2v2
            int num1 = 0;
            for(int a = n-2; a < n; a++){
               for(int b = j; b < j+2; b++){
                  if(board[a][b] == 1) num1++;
               }
            }
            
            if(num1 == 1) flip1(n-2,j);
            else if(num1 == 2) flip2(n-2,j);
            else if(num1 == 3) flip3(n-2,j);
            else if(num1 == 4) flip4(n-2,j);
         }
         
         
         
         
         out.println(answer.size());
         StringJoiner sj = new StringJoiner("\n");
         for(Move move : answer){
            sj.add(move.toString());
         }
         out.println(sj);
      
      }
      
      
      
      
      out.close();
   }
   
   public static void addmove(Move mv){
      board[mv.x1-1][mv.y1-1] ^= 1;
      board[mv.x2-1][mv.y2-1] ^= 1;
      board[mv.x3-1][mv.y3-1] ^= 1;
      answer.add(mv);
   }
   
   public static void flip1(int x, int y){
      if(board[x][y] == 1) fliptl(x,y);
      else if(board[x][y+1] == 1) fliptr(x,y+1);
      else if(board[x+1][y] == 1) flipbl(x+1,y);
      else flipbr(x+1,y+1);
   }
   
   public static void fliptl(int x, int y){
      addmove(new Move(x,y,x,y+1,x+1,y+1));
      addmove(new Move(x,y,x+1,y,x+1,y+1));
      addmove(new Move(x,y,x+1,y,x,y+1));
   }
   
   public static void fliptr(int x, int y){
      addmove(new Move(x,y,x,y-1,x+1,y-1));
      addmove(new Move(x,y,x+1,y,x+1,y-1));
      addmove(new Move(x,y,x+1,y,x,y-1));
   }
   
   public static void flipbl(int x, int y){
      addmove(new Move(x,y,x,y+1,x-1,y+1));
      addmove(new Move(x,y,x-1,y,x-1,y+1));
      addmove(new Move(x,y,x-1,y,x,y+1));
   }
   
   public static void flipbr(int x, int y){
      addmove(new Move(x,y,x,y-1,x-1,y-1));
      addmove(new Move(x,y,x-1,y,x-1,y-1));
      addmove(new Move(x,y,x-1,y,x,y-1));
   }
   
   public static void flip2(int x, int y){
      if(board[x][y] != board[x+1][y] && board[x][y] != board[x][y+1]){
         //diagonal
         if(board[x][y] == 1){
            addmove(new Move(x,y,x+1,y,x,y+1));
         } else {
            addmove(new Move(x,y+1,x,y,x+1,y+1));
         }
      } else {
         //2 in a row
         //pick a 0
         int x01 = -1;
         int y01 = -1;
         int x02 = -1;
         int y02 = -1;
         int nonex = -1;
         int noney = -1;
         for(int a = x; a <= x+1; a++){
            for(int b = y; b <= y+1; b++){
               if(board[a][b] == 0){
                  if(x01 == -1){
                     x01 = a;
                     y01 = b;
                  } else {
                     x02 = a;
                     y02 = b;
                  }
               } else {
                  nonex = a;
                  noney = b;
               }
            }
         }
         
         addmove(new Move(x01,y01,x02,y02,nonex,noney));
      }
      
      flip3(x,y);
   }
   
   public static void flip3(int x, int y){
      ArrayList<Integer> xs = new ArrayList<Integer>();
      ArrayList<Integer> ys = new ArrayList<Integer>();
      
      for(int a = x; a <= x+1; a++){
         for(int b = y; b <= y+1; b++){
            if(board[a][b] == 1){
               xs.add(a);
               ys.add(b);
            }
         }
      }
      
      addmove(new Move(xs.get(0),ys.get(0),xs.get(1),ys.get(1),xs.get(2),ys.get(2)));
   }
   
   public static void flip4(int x, int y){
      addmove(new Move(x,y,x+1,y,x,y+1));
      flip1(x,y);
   }
   
   public static class Move{
      int x1;
      int y1;
      int x2;
      int y2;
      int x3;
      int y3;
      
      public Move(int a, int b, int c, int d, int e, int f){
         x1 = a+1;
         y1 = b+1;
         x2 = c+1;
         y2 = d+1;
         x3 = e+1;
         y3 = f+1;
      }
      
      public String toString(){
         return x1 + " " + y1 + " " + x2 + " " + y2 + " " + x3 + " " + y3;
      }
   }
   
      
}