//Igor in Museum
import java.io.*;
import java.util.*;

public class D598{

   public static char[][] board;
   public static int[][] dsu;
   public static ArrayList<Integer> dsuans;
   public static boolean[][] seen;
   public static int curans,n,m;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      board = new char[n][m];
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
         }
      }
      
      dsu = new int[n][m];
      dsuans = new ArrayList<Integer>();              //get(dsu num) = size
      
      seen = new boolean[n][m];
      int id = 0;
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(!seen[k][j] && board[k][j] == '.'){
               curans = 0;
               dfs(k,j,id);
               dsuans.add(curans);
               id++;
            }
         }
      }
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         out.println(dsuans.get(dsu[a-1][b-1]));
      }
      
      out.close();
   }
   
   public static void dfs(int x, int y,int id){
      if(!seen[x][y]){
         seen[x][y] = true;
         dsu[x][y] = id;
         if(x > 0 && !seen[x-1][y]){
            if(board[x-1][y] == '*'){
               curans++;
            } else {
               dfs(x-1,y,id);
            }
         }
         if(x < n-1 && !seen[x+1][y]){
            if(board[x+1][y] == '*'){
               curans++;
            } else {
               dfs(x+1,y,id);
            }
         } 
         if(y > 0 && !seen[x][y-1]){
            if(board[x][y-1] == '*'){
               curans++;
            } else {
               dfs(x,y-1,id);
            }
         }  
         if(y < m-1 && !seen[x][y+1]){
            if(board[x][y+1] == '*'){
               curans++;
            } else {
               dfs(x,y+1,id);
            }
         }  
      } 
   }
   
   public static boolean in(int x, int y){
      return x >= 0 && y >= 0 && x < n && y < m;
   }
   
   
}