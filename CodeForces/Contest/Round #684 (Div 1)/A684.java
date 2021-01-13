//make sure to make new file!
import java.io.*;
import java.util.*;

public class A684{

   public static ArrayList<Move> answer;
   public static int n;
   public static int m;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());
         
         int[][] board = new int[n][m];
         for(int k = 0; k < n; k++){
            char[] c = f.readLine().toCharArray();
            for(int j = 0; j < m; j++){
               board[k][j] = Character.getNumericValue(c[j]);
            }
         }
         
         answer = new ArrayList<Move>();
         
         for(int k = 0; k < n; k++){
            for(int j = 0; j < m; j++){
               if(board[k][j] == 1) flip(k,j);
            }
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
   
   public static void flip(int x, int y){
      if(x < n-1 && y < m-1) fliptl(x,y);
      else if(x < n-1) fliptr(x,y);
      else if(y < m-1) flipbl(x,y);
      else flipbr(x,y);
   }
   
   public static void fliptl(int x, int y){
      answer.add(new Move(x,y,x,y+1,x+1,y+1));
      answer.add(new Move(x,y,x+1,y,x+1,y+1));
      answer.add(new Move(x,y,x+1,y,x,y+1));
   }
   
   public static void fliptr(int x, int y){
      answer.add(new Move(x,y,x,y-1,x+1,y-1));
      answer.add(new Move(x,y,x+1,y,x+1,y-1));
      answer.add(new Move(x,y,x+1,y,x,y-1));
   }
   
   public static void flipbl(int x, int y){
      answer.add(new Move(x,y,x,y+1,x-1,y+1));
      answer.add(new Move(x,y,x-1,y,x-1,y+1));
      answer.add(new Move(x,y,x-1,y,x,y+1));
   }
   
   public static void flipbr(int x, int y){
      answer.add(new Move(x,y,x,y-1,x-1,y-1));
      answer.add(new Move(x,y,x-1,y,x-1,y-1));
      answer.add(new Move(x,y,x-1,y,x,y-1));
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