//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
//comment
public class C767{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         int[][] board = new int[n][n];
         for(int k = 0; k < n; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            for(int j = 0; j < n; j++){
               board[k][j] = Integer.parseInt(st.nextToken());
            }
         }
      
         boolean[][] add = new boolean[n][n];
         
         for(int k = 0; k < 2*n-1; k+=4){
            int start = k;
            if(k >= n) start = n-1;
            for(int j = k-start; j < n && k-j >= 0; j+=2){
               add[k-j][j] = true;
            }
         }
         
         int answer = 0;
         for(int k = 0; k < n; k++){
            for(int j = 0; j < n; j++){
               if(add[k][j]){
                  answer ^= board[k][j];
                  answer ^= board[k][n-j-1];
               }
            }
         }
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
      
}