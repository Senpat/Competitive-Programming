//Drawing Stars
import java.io.*;
import java.util.*;

public class E501{
   
   public static int n,m;
   public static char[][] board;
   public static int[][] numboard;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      board = new char[n][m];
      int starcount = 0;
      numboard = new int[n][m];
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
            if(board[k][j] == '.'){
               numboard[k][j] = -1;
            } 
            else {
               numboard[k][j] = starcount;
               starcount++;
            }
         }
      }
      
      if(board[0][0] == '*' || board[0][m-1] == '*' || board[n-1][0] == '*' || board[n-1][m-1]=='*'){
         System.out.println(-1);
         System.exit(0);
      }
      
      boolean[] used = new boolean[starcount];
      
      ArrayList<String> answer = new ArrayList<String>();
      for(int k = 1; k < n-1; k++){
         for(int j = 1; j < m-1; j++){
            if(board[k][j] == '*' && board[k+1][j] == '*' && board[k-1][j] == '*' && board[k][j+1] == '*' && board[k][j-1] == '*'){
               int i = 1;
               while(checkstar(k,j,i)){
                  i++;
               }
               for(int x = 0; x < i; x++){
                  used[numboard[k+x][j]] = true;
                  used[numboard[k][j+x]] = true;
                  used[numboard[k-x][j]] = true;
                  used[numboard[k][j-x]] = true;
               }
               answer.add("" + (k+1) + " " + (j+1) + " " + (i-1));
            }
         }
      }
      
      if(checkbool(used)){
         System.out.println(answer.size());
         for(String s : answer){
            System.out.println(s);
         }
      } 
      else {
         System.out.println(-1);
      }
      
      
   }
   
   public static boolean checkbool(boolean[] array){
      for(int k = 0; k < array.length; k++) 
         if(!array[k]) 
            return false;
      return true;
   }
   
   public static boolean checkstar(int a, int b, int i){  
      if(a >= i && b>=i && a<n-i && b<m-i){
         return board[a+i][b] == '*' && board[a-i][b] == '*' && board[a][b+i] == '*' && board[a][b-i] == '*';
      }
      return false;
   }
   
}