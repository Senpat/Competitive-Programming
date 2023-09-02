//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1192{
   
   public static boolean[][] seen;
   public static char[][] board;
   
   public static int n,m;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      board = new char[n][m];
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
         }
      }
      
      seen = new boolean[n][m];
      
      int answer = 0;
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(seen[k][j] || board[k][j] == '#') continue;
            answer++;
            ff(k,j);
         }
      }
      
      out.println(answer);
      
      
      
      
      
      out.close();
   }
   
   
   public static void ff(int x, int y){
      seen[x][y] = true;
      
      if(in(x+1,y) && !seen[x+1][y] && board[x+1][y] == '.'){
         ff(x+1,y);
      }
      if(in(x,y+1) && !seen[x][y+1] && board[x][y+1] == '.'){
         ff(x,y+1);
      }
      if(in(x-1,y) && !seen[x-1][y] && board[x-1][y] == '.'){
         ff(x-1,y);
      }
      if(in(x,y-1) && !seen[x][y-1] && board[x][y-1] == '.'){
         ff(x,y-1);
      }
   }
   
   public static boolean in(int x, int y){
      return x < n && x >= 0 && y < m && y >= 0;
   }   
   
}