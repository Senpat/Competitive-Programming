//make sure to make new file!
import java.io.*;
import java.util.*;

public class C770{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         if(m == 1){
            StringJoiner sj = new StringJoiner("\n");
            sj.add("YES");
            for(int k = 1; k <= n; k++){
               sj.add("" + k);
            }
            out.println(sj.toString());
            continue;
         }
         
         if(n % 2 == 0){
            
            int[][] board = new int[n+1][m+1];
            for(int k = 1; k <= n; k += 2){
               int start = (k-1)*m+1;
               for(int j = 1; j <= m; j++){
                  board[k][j] = start;
                  start += 2;
               }
               start = (k-1)*m+2;
               for(int j = 1; j <= m; j++){
                  board[k+1][j] = start;
                  start += 2;
               }
            }
            
            out.println("YES");
            for(int k = 1; k <= n; k++){
               StringJoiner sj = new StringJoiner(" ");
               for(int j = 1; j <= m; j++){
                  sj.add("" + board[k][j]);
               }
               out.println(sj.toString());
            }
            
            
         } else {
            out.println("NO");
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}