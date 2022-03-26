//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[][] board = new int[n][m];
      
      int num0 = 0;
      int num1 = 0;
      int num2 = 0;
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < m; j++){
            board[k][j] = Integer.parseInt(st.nextToken());
            if(board[k][j] == 0) num0++;
            if(board[k][j] == 1) num1++;
            if(board[k][j] == 2) num2++;
         }
      }
      
      if(num0 >= 2){
         out.println(0);
      } else if(num0 == 1){
         if(num1 > 0){
            if(n == 1){
               int a = board[0][0];
               if(a == 0) a = Integer.MAX_VALUE;
               int b = board[0][m-1];
               if(b == 0) b = Integer.MAX_VALUE;
               out.println(Math.min(a,b));
            } else if(m == 1){
               int a = board[0][0];
               if(a == 0) a = Integer.MAX_VALUE;
               int b = board[n-1][0];
               if(b == 0) b = Integer.MAX_VALUE;
               out.println(Math.min(a,b));
            } else{
               out.println(1);
            }
         } else {
            out.println(2);
         }
      } else if(num2 % 2 == 0){
         out.println(0);
      } else {
         //0 0s, odd 2s
         out.println((1L << (num2/2)));
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}