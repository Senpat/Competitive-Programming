//make sure to make new file!
import java.io.*;
import java.util.*;

public class B864{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[][] board = new int[n][n];
         for(int k = 0; k < n; k++){
            st = new StringTokenizer(f.readLine());
            
            for(int j = 0; j < n; j++){
               board[k][j] = Integer.parseInt(st.nextToken());
            }
         }
         
         int count = 0;
         for(int k = 0; k < n; k++){
            for(int j = 0; j < n; j++){
               if(board[k][j] != board[n-k-1][n-j-1]){
                  count++;
               }  
            }
         }
         
         if(count/2 <= m && (n%2 == 1 || (count/2) % 2 == m % 2)){
            out.println("YES");
         } else {
            out.println("NO");
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}