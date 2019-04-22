//make sure to make new file!
import java.io.*;
import java.util.*;

public class B553{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[][] board = new int[n][m];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < m; j++){
            board[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      int xor = board[0][0];
      for(int k = 1; k < n; k++){
         xor ^= board[k][0];
      }
      
      if(xor > 0){
         out.println("TAK");
         for(int k = 0; k < n; k++) out.print("1 ");
         out.close();
         System.exit(0);
      }
      
      for(int k = 0; k < n; k++){
         for(int j = 1; j < m; j++){
            if(board[k][j] != board[k][0]){
               out.println("TAK");
               for(int i = 0; i < k; i++) out.print("1 ");
               out.print(j+1);
               for(int i = k+1; i < n; i++) out.print(" 1");
               out.close();
               System.exit(0);
            }
         }
      }
      
      out.println("NIE");
      
      
      
      
      
      
      out.close();
   }
   
      
}