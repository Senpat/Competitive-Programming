//make sure to make new file!
import java.io.*;
import java.util.*;

public class   Solution{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int p = Integer.parseInt(st.nextToken());
         int n = Integer.parseInt(st.nextToken())+1;
         
         int[][] board = new int[n][n];
         
         for(int k = 0; k < p; k++){
            st = new StringTokenizer(f.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            char d = st.nextToken().charAt(0);
            
            if(d == 'N'){
               for(int x = 0; x < n; x++){
                  for(int y = sy+1; y < n; y++){
                     board[x][y]++;
                  }
               }
            } else if(d == 'S'){
               for(int x = 0; x < n; x++){
                  for(int y = 0; y < sy; y++){
                     board[x][y]++;
                  }
               }
            } else if(d == 'E'){
               for(int x = sx+1; x < n; x++){
                  for(int y = 0; y < n; y++){
                     board[x][y]++;
                  }
               }
            } else if(d == 'W'){
               for(int x = 0; x < sx; x++){
                  for(int y = 0; y < n; y++){
                     board[x][y]++;
                  }
               }
            }
         }
         
         int max = 0;
         int ax = -1;
         int ay = -1;
         for(int k = 0; k < n; k++){
            for(int j = 0; j < n; j++){
               if(board[k][j] > max){
                  max = board[k][j];
                  ax = k;
                  ay = j;
               }
            }
         }
         
         out.println("Case #" + q + ": " + ax + " " + ay);
         
         
         
      }
      
      
      
      
      out.close();
   }
   
      
}