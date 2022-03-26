//make sure to make new file!
import java.io.*;
import java.util.*;

public class G{

   public static char[][] board;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      board = new char[n][m];
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
         }
      }
      
      double[][] dp = new double[n+1][m+1];
      for(int k = 0; k <= n; k++) Arrays.fill(dp[k],200.0);
      
      dp[0][0] = 0.0;
      
      //go from (x1,y1) to (x2,y2)
      
      for(int x1 = 0; x1 <= n; x1++){
         for(int y1 = 0; y1 <= m; y1++){
            
            for(int x2 = x1; x2 <= n; x2++){
               for(int y2 = y1; y2 <= m; y2++){
                  
                  if(canmove(x1,y1,x2,y2)){
                     //System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
                     dp[x2][y2] = Math.min(dp[x2][y2],dp[x1][y1] + dist(x1,y1,x2,y2));
                  }
               }
            }
         }
      }
      
      double answer = dp[n][m];
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
      
   }
   
   public static boolean canmove(int x1, int y1, int x2, int y2){
      int dx = x2-x1;
      int dy = y2-y1;
      
      int rowstart = 0;
      
      int numer = 0;
      int denom = dx;
      
      for(int k = 0; k < dx; k++){
         //calculate how much that row needs
         numer += dy;
         
         int div = numer/denom;
         
         if(numer%denom == 0) div--;
         
         for(int j = rowstart; j <= div; j++){
            if(board[k+x1][j+y1] == '#') return false;  
            //System.out.println("Checking: " + (k+x1) + " " + (j+y1));
         }
            
         rowstart = numer/denom;
       
      }
      
      return true;
      
   }
   
   public static double dist(int x1, int y1, int x2, int y2){
      return Math.sqrt((double)((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)));
   }
      
}