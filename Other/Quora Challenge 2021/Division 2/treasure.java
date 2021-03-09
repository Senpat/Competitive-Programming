//make sure to make new file!
import java.io.*;
import java.util.*;

public class treasure{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[][] board = new int[n][n];
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         
         for(int j = 0; j < n; j++){
            board[k][j] = Character.getNumericValue(s.charAt(j));
         }
      }
      
      int[][] max = new int[n][n];
      long[][] maxcount = new long[n][n];
      maxcount[0][0] = 1L;
      
      if(board[0][0] == 1) max[0][0] = 1;
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            if(k < n-1){
               //check k+1
               int newmax = max[k][j];
               if(board[k+1][j] == 1) newmax++;
               
               if(newmax > max[k+1][j]){
                  max[k+1][j] = newmax;
                  maxcount[k+1][j] = maxcount[k][j];
               } else if(newmax == max[k+1][j]){
                  maxcount[k+1][j] = (maxcount[k+1][j] + maxcount[k][j] + MOD)%MOD;
               }
            }
            
            if(j < n-1){
               //check j+1
               int newmax = max[k][j];
               if(board[k][j+1] == 1) newmax++;
               
               if(newmax > max[k][j+1]){
                  max[k][j+1] = newmax;
                  maxcount[k][j+1] = maxcount[k][j];
               } else if(newmax == max[k][j+1]){
                  maxcount[k][j+1] = (maxcount[k][j+1] + maxcount[k][j] + MOD)%MOD;
               }
            }
         }
      }
      
      out.println(max[n-1][n-1] + " " + maxcount[n-1][n-1]);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}