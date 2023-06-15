//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1624{

   public static int N = 8;
   
   public static char[][] board;
   public static int[] queens;
   
   public static int answer = 0;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      board = new char[N][N];
      for(int k = 0; k < N; k++){
         String s = f.readLine();
         for(int j = 0; j < N; j++){
            board[k][j] = s.charAt(j);
         }
      }
      
      queens = new int[N];
      
      answer = 0;
      dothing(0);
      
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
   public static void dothing(int x){
      if(x == N){
         answer++;
         return;
      } else {
         for(int k = 0; k < N; k++){
            if(board[x][k] == '*') continue;
            boolean fail = false;
            for(int j = 0; j < x; j++){
               if(queens[j] == k){
                  fail = true;
                  break;           //vertical
               }
               if(Math.abs(queens[j]-k) == x-j){
                  fail = true;
                  break;            //diagonal
               }
            }
            
            if(fail) continue;
            
            queens[x] = k;
            dothing(x+1);
         }
      }
   }
      
}