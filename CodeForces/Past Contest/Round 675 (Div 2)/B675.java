//make sure to make new file!
import java.io.*;
import java.util.*;

public class B675{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         long[][] board = new long[n][m];
         for(int k = 0; k < n; k++){
            st = new StringTokenizer(f.readLine());
            for(int j = 0; j < m; j++){
               board[k][j] = Long.parseLong(st.nextToken());
            }
         }
         
         long answer = 0L;
         for(int k = 0; k < (n+1)/2; k++){
            for(int j = 0; j < (m+1)/2; j++){
               if(k == n-k-1 && j == m-j-1) continue;
               if(k == n-k-1){
                  long v1 = board[k][j];
                  long v2 = board[k][m-j-1];
                  long average = (v1+v2)/2;
                  answer += Math.abs(v1-average) + Math.abs(v2-average);
               } else if(j == m-j-1){
                  long v1 = board[k][j];
                  long v2 = board[n-k-1][j];
                  long average = (v1+v2)/2;
                  answer += Math.abs(v1-average) + Math.abs(v2-average);
               } else {
                  long v1 = board[k][j];
                  long v2 = board[n-k-1][j];
                  long v3 = board[k][m-j-1];
                  long v4 = board[n-k-1][m-j-1];
                  long a1 = (v1+v2+v3+v4)/4L;
                  long a2 = a1+1;
                  //out.println(k + " " + j + " " + Math.min(Math.abs(v1-a1)+Math.abs(v2-a1)+Math.abs(v3-a1)+Math.abs(v4-a1),Math.abs(v1-a2)+Math.abs(v2-a2)+Math.abs(v3-a2)+Math.abs(v4-a2)));
                  answer += Math.min(Math.abs(v1-a1)+Math.abs(v2-a1)+Math.abs(v3-a1)+Math.abs(v4-a1),Math.abs(v1-a2)+Math.abs(v2-a2)+Math.abs(v3-a2)+Math.abs(v4-a2));
               }
               //out.println(k + " " + j + " " + answer);
            }
         }
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
      
}