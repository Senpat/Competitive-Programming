//make sure to make new file!
import java.io.*;
import java.util.*;

public class C405{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[][] board = new int[n][n];
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int j = 0; j < n; j++){
            board[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      int answer = 0;
      for(int k = 0; k < n; k++){
         //for(int j = 0; j < n; j++){
            answer = (answer + board[k][k] + 2)%2;
         //}
      }
      
      int q = Integer.parseInt(f.readLine());
      for(int t = 0; t < q; t++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int i = Integer.parseInt(st.nextToken());
         if(i == 3){
            out.print(answer);
         } else {
            if(n%2 < 2) answer = 1-answer;
         }
      }
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}