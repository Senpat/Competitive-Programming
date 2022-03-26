//make sure to make new file!
import java.io.*;
import java.util.*;

public class B777{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[][] board = new int[n][m];
         
         for(int k = 0; k < n; k++){
            String s = f.readLine();
            for(int j = 0; j < m; j++){
               board[k][j] = Character.getNumericValue(s.charAt(j));
            }
         }
         
         boolean fail = false;
         for(int k = 0; k < n-1; k++){
            for(int j = 0; j < m-1; j++){
               int count = board[k][j] + board[k+1][j] + board[k][j+1] + board[k+1][j+1];
               if(count == 3){
                  fail = true;
               }
            }
         }
      
         if(fail){
            out.println("NO");
         } else {
            out.println("YES");
         }
      }
      
      
      
      
      out.close();
   }
   
      
}