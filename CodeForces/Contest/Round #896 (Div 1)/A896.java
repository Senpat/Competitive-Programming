//make sure to make new file!
import java.io.*;
import java.util.*;

public class A896{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         int[][] board = new int[n][m];
         
         for(int k = 0; k < n && k < m-1; k++){
            for(int j = 0; j < m; j++){
               board[k][j] = (k+j)%m;
            }
         }
         
         for(int k = m-1; k < n; k++){
            for(int j = 0; j < m; j++){
               board[k][j] = j;
            }
         }
         
         if(m == 1){
            out.println(0);
         } else if(n < m){
            out.println(n+1);
         } else {
            out.println(m);
         }
         
         StringJoiner line = new StringJoiner("\n");
         for(int k = 0; k < n; k++){
            StringJoiner sj = new StringJoiner(" ");
            for(int j = 0; j < m; j++){
               sj.add("" + board[k][j]);
            }
            line.add(sj.toString());
         }
         out.println(line.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}