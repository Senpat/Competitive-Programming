//Down or Right
import java.io.*;
import java.util.*;
//WRONG
public class E1023{

   public static BufferedReader f;
   public static PrintWriter out;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      int[][] matrix = new int[n+1][n+1];
      //1 is open path, 0 is blocked
      matrix[1][1] = 1;
      matrix[n][n] = 1;
      
      //find square on diagonal
      int dx = -1;
      int dy = -1;
      for(int k = 1; k <= n; k++){
         if(query(1,1,k,n-k+1) && query(k,n-k+1,n,n)){
            dx = k;
            dy = n-k+1;
            break;
         }
      }
      matrix[dx][dy] = 1;
      
      int x;
      int y;
      //get path from 1,1 to dk,dk
      x = dx;
      y = dy;
      
      while(x+y != 3){
         if(in(x-1,y) && query(x-1,y,n,n)){
            matrix[x-1][y] = 1;
            x--;
         } else {
            matrix[x][y-1] = 1;
            y--;
         }
      }
      
      //get path from dk,dk to n,n
      x = dx;
      y = dy;
      while(2*n-x-y != 1){
         if(in(x+1,y) && query(1,1,x+1,y)){
            matrix[x+1][y] = 1;
            x++;
         } else {
            matrix[x][y+1] = 1;
            y++;
         }
      }
      
      StringJoiner sj = new StringJoiner("");
      sj.add("! ");
      
      x = 1;
      y = 1;
      for(int k = 0; k < 2*n-2; k++){
         if(in(x+1,y) && matrix[x+1][y] == 1){
            sj.add("D");
            x++;
         } else {
            sj.add("R");
            y++;
         }
      }
      
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean in(int x, int y){
      return x >= 1 && x <= n && y >= 1 && y <= n;
   }
   
   public static boolean query(int a, int b, int c, int d) throws IOException{
      out.println("? " + a + " " + b + " " + c + " " + d);
      out.flush();
      
      String s = f.readLine();
      return s.equals("YES");
   }
   
      
}