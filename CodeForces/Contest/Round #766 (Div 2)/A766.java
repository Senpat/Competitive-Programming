//make sure to make new file!
import java.io.*;
import java.util.*;

public class A766{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken())-1;
         int y = Integer.parseInt(st.nextToken())-1;
         
         char[][] board = new char[n][m];
         
         boolean hasw = false;
         for(int k = 0; k < n; k++){
            String s = f.readLine();
            for(int j = 0; j < m; j++){
               board[k][j] = s.charAt(j);
               if(board[k][j] == 'B') hasw = true;
            }
         }
         
         if(!hasw){
            out.println(-1);
            continue;
         }
         
         //check 1
         boolean hasw1 = false;
         for(int k = 0; k < n; k++){
            if(board[k][y] == 'B') hasw1 = true;
         }
         for(int k = 0; k < m; k++){
            if(board[x][k] == 'B') hasw1 = true;
         }
         
         if(board[x][y] == 'B') out.println(0);
         else if(hasw1) out.println(1);
         else out.println(2); 
      }
      
      
      
      
      out.close();
   }
   
      
}