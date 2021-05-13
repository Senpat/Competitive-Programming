//make sure to make new file!
import java.io.*;
import java.util.*;

public class C{

   public static int n;
   public static int m;
   public static int[][] board;
   public static boolean[][] seen;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      board = new int[n][m];
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = Character.getNumericValue(s.charAt(j));
         }
      }
      
      seen = new boolean[n][m];
      
      int answer = 0;
      //loop on edges
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(k == 0 || k == n-1 || j == 0 || j == m-1){
               if(!seen[k][j]){
                  seen[k][j] = true;
                  ff(k,j,board[k][j]);
                  answer++;
               }
            }
         }
      }
      
      int region0 = 0;
      int region1 = 0;
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(seen[k][j]) continue;
            if(board[k][j] == 0) region0++;
            else region1++;
            
            seen[k][j] = true;;
            ff(k,j,board[k][j]);
         }
      }
      
      answer += Math.min(region0,region1);
      out.println(answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void ff(int x, int y, int i){
      
      if(in(x+1,y) && board[x+1][y] == i && !seen[x+1][y]){
         seen[x+1][y] = true;
         ff(x+1,y,i);
      }  
      if(in(x-1,y) && board[x-1][y] == i && !seen[x-1][y]){
         seen[x-1][y] = true;
         ff(x-1,y,i);
      }
      if(in(x,y+1) && board[x][y+1] == i && !seen[x][y+1]){
         seen[x][y+1] = true;
         ff(x,y+1,i);
      }
      if(in(x,y-1) && board[x][y-1] == i && !seen[x][y-1]){
         seen[x][y-1] = true;
         ff(x,y-1,i);
      }
   }
   
   public static boolean in(int x, int y){
      return x >= 0 && x < n && y >= 0 && y < m;
   }
      
}