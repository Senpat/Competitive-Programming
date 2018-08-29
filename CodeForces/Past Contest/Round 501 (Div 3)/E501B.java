//Drawing Stars B
//semi-tutorial
import java.io.*;
import java.util.*;

public class E501B{
   
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
     
      
      char[][] board = new char[n][m];
      boolean[][] check = new boolean[n][m];
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
         }
      }
      int[][] u = new int[n][m];
      int[][] d = new int[n][m];
      int[][] l = new int[n][m];
      int[][] r = new int[n][m];
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(k==0) u[k][j] = board[k][j] == '.' ? 0 : 1;
            else u[k][j] = board[k][j] == '.' ? 0 : u[k-1][j]+1;
            if(j==0) l[k][j] = board[k][j] == '.' ? 0 : 1;
            else l[k][j] = board[k][j] == '.' ? 0 : l[k][j-1]+1;
         }
      }
      for(int k = n-1; k >= 0; k--){
         for(int j = m-1; j >= 0; j--){
            if(j==m-1) r[k][j] = board[k][j] == '.' ? 0 : 1;
            else r[k][j] = board[k][j] == '.' ? 0 : r[k][j+1]+1;
            if(k==n-1) d[k][j] = board[k][j] == '.' ? 0 : 1;
            else d[k][j] = board[k][j] == '.' ? 0 : d[k+1][j]+1;
         }
      }
      
      if(board[0][0] == '*' || board[0][m-1] == '*' || board[n-1][0] == '*' || board[n-1][m-1]=='*'){
         System.out.println(-1);
         System.exit(0);
      }
      
      ArrayList<String> answer = new ArrayList<String>();
      
      for(int k = 1; k < n-1; k++){
         for(int j = 1; j < m-1; j++){
            if(board[k][j] == '*' && board[k+1][j] == '*' && board[k-1][j] == '*' && board[k][j+1] == '*' && board[k][j-1] == '*'){
               int i = min4(u[k][j],d[k][j],r[k][j],l[k][j])-1;
               //System.out.println(k + " " + j + " " + i);
               for(int x = 0; x <= i; x++){
                  check[k+x][j] = true;
                  check[k-x][j] = true;
                  check[k][j+x] = true;
                  check[k][j-x] = true;
               }
               answer.add("" + (k+1) + " " + (j+1) + " " + i);
            }
         }
      }
      
      if(ver(board,check)){
         System.out.println(answer.size());
         for(String s : answer){
            out.println(s);
         }
      } else {
         System.out.println(-1);
      }
      
      out.close();
      
      
   }
   public static int min4(int a, int b, int c, int d){
      return Math.min(a, Math.min(b,Math.min(c,d)));
   }
   
   public static boolean ver(char[][] c, boolean[][] b){
      for(int k = 0; k < c.length; k++){
         for(int j = 0; j < c[0].length; j++){
            if(c[k][j] == '*' && !b[k][j]){
               return false;
            }
         }
      }
      return true;
   }
}