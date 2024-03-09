//make sure to make new file!
import java.io.*;
import java.util.*;

public class E334{
   
   public static long MOD = 998244353L;
   
   public static int n,m;

   public static char[][] board;
   
   public static int[][] comp;
   public static boolean[][] seen;
   
   public static int compint;
   
   public static int[] dx = new int[]{1,-1,0,0};
   public static int[] dy = new int[]{0,0,1,-1};
   
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
      
      comp = new int[n][m];
      seen = new boolean[n][m];
      
      compint = 1;
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(seen[k][j] || board[k][j] == '.') continue;
            
            Stack<Pair> stack = new Stack<Pair>();
            stack.push(new Pair(k,j));
            seen[k][j] = true;
            comp[k][j] = compint;
            
            while(!stack.isEmpty()){
               Pair p = stack.pop();
               
               
               for(int d = 0; d < 4; d++){
                  int nx = p.x + dx[d];
                  int ny = p.y + dy[d];
                  if(!in(nx,ny) || seen[nx][ny] || board[nx][ny] == '.') continue;
                  stack.push(new Pair(nx,ny));
                  seen[nx][ny] = true;
                  comp[nx][ny] = compint;
               }
            }
            
            
            compint++;
         }
      }
      
      compint--;
      
      long total = 0L;
      long sum = 0L;
      long compintl = (long)compint;
      
      boolean[] curcomps = new boolean[compint+1];
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(board[k][j] != '.') continue;
            
            long curnum = 0L;
            for(int d = 0; d < 4; d++){
               int nx = k + dx[d];
               int ny = j + dy[d];
               if(in(nx,ny) && comp[nx][ny] > 0){
                  if(!curcomps[comp[nx][ny]]){
                     curcomps[comp[nx][ny]] = true;
                     curnum++;
                  }
               }
            }
            for(int d = 0; d < 4; d++){
               int nx = k + dx[d];
               int ny = j + dy[d];
               if(in(nx,ny) && comp[nx][ny] > 0){
                  curcomps[comp[nx][ny]] = false;
               }
            }
            
            sum += compintl - (curnum-1);
            if(sum >= MOD) sum -= MOD;
            total++;
         }
      }
      
      //out.println(compint);
      //out.println(sum + " " + total);
      long answer = (sum * modInverse(total,MOD) + MOD)%MOD;
      out.println(answer);
      
      out.close();
   }
   
   public static class Pair{
      int x;
      int y;
      public Pair(int a, int b){
         x = a;
         y = b;
      }
   }
   
   public static void dfs(int x, int y){
      comp[x][y] = compint;
      seen[x][y] = true;
      
      for(int d = 0; d < 4; d++){
         int nx = x + dx[d];
         int ny = y + dy[d];
         if(!in(nx,ny) || seen[nx][ny] || board[nx][ny] == '.') continue;
         dfs(nx,ny);
      }
      
   }

   public static boolean in(int x, int y){
      return x >= 0 && x < n && y >= 0 && y < m;
   }
   
   
        //from geeksforgeeks
   public static long modInverse(long a, long m) 
   { 
       long m0 = m; 
       long y = 0;
       long x = 1; 
     
       if (m == 1) 
         return 0; 
     
       while (a > 1) 
       { 
           // q is quotient 
           long q = a / m; 
           long t = m; 
     
           // m is remainder now, process same as 
           // Euclid's algo 
           m = a % m;
           a = t; 
           t = y; 
     
           // Update y and x 
           y = x - q * y; 
           x = t; 
       } 
     
       // Make x positive 
       if (x < 0) 
          x += m0; 
       return x; 
   }  

   
}
