//Drawing Stars B
//semi-tutorial
import java.io.*;
import java.util.*;

public class E501Btest{
   
   public static int n,m;
   public static char[][] board;
   public static int[][] numboard;
   public static int[][] u,d,l,r;
   
   public static void main(String[] args)throws IOException{
      final long starttime = System.nanoTime();
      BufferedReader f = new BufferedReader(new FileReader("test.out"));
      
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
      System.out.println((double)(System.nanoTime()-starttime)/1000000000.0);
      u = new int[n][m];
      d = new int[n][m];
      l = new int[n][m];
      r = new int[n][m];
      
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
      
      System.out.println((double)(System.nanoTime()-starttime)/1000000000.0);
      // if(board[0][0] == '*' || board[0][m-1] == '*' || board[n-1][0] == '*' || board[n-1][m-1]=='*'){
//          System.out.println(-1);
//          System.exit(0);
//       }
      
      boolean[] used = new boolean[starcount];
      
      ArrayList<String> answer = new ArrayList<String>();
      ArrayList<Ans> ans = new ArrayList<Ans>();
      
      for(int k = 1; k < n-1; k++){
         for(int j = 1; j < m-1; j++){
            if(board[k][j] == '*' && board[k+1][j] == '*' && board[k-1][j] == '*' && board[k][j+1] == '*' && board[k][j-1] == '*'){
               int i = min4(u[k][j],d[k][j],r[k][j],l[k][j])-1;
               //System.out.println(k + " " + j + " " + i);
               ans.add(new Ans(k,j,i));
               answer.add("" + (k+1) + " " + (j+1) + " " + i);
            }
         }
      }
      
      System.out.println((double)(System.nanoTime()-starttime)/1000000000.0);
      for(Ans a : ans){
         int k = a.a;
         int j = a.b;
         int i = a.i;
         for(int x = 0; x <= i; x++){
            used[numboard[k+x][j]] = true;
            used[numboard[k][j+x]] = true;
            used[numboard[k-x][j]] = true;
            used[numboard[k][j-x]] = true;
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
      
      System.out.println((double)(System.nanoTime()-starttime)/1000000000.0);
      
   }
   
   public static class Ans{
      int a;
      int b;
      int i;
      public Ans(int q, int w, int e){
         a = q;
         b = w;
         i = e;
      }
   }
   
   public static int min4(int a, int b, int c, int d){
      return Math.min(a, Math.min(b,Math.min(c,d)));
   }
   
   public static boolean in(int a,int b){
      return a >= 0 && b>=0 && a < n && b < m;
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