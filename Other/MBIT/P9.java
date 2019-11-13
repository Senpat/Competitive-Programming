//make sure to make new file!
import java.io.*;
import java.util.*;

public class P9{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      
      if(n%a == 0 && m%b == 0){
         out.println("No");
         out.close();
         return;
      }
      out.println("Yes");
      
      int[][] board = new int[n][m];
      
      
           
      if(n%a == 0){
      //go by m
         int pstart = (b-1)*(b)/2*10000 + 1;        //positive start - sum of 1 to b-1, times 10000 add 1
         for(int k = 0; k < m; k++){
            if(k > 0 && 0 == (k+1)%b){
               board[0][k] = pstart;
            } else {
               board[0][k] = ((k+1)%b)*-10000;
            }
         }
         
         for(int k = 1; k < n; k++){
            for(int j = 0; j < m; j++){
               board[k][j] = board[0][j] + k%a;
            }
         }

      
      } else {
      //go by n
         int pstart = (a-1)*(a)/2*10000 + 1;        //positive start - sum of 1 to b-1, times 10000 add 1
         for(int k = 0; k < n; k++){
            if(k > 0 && 0 == (k+1)%a){
               board[k][0] = pstart;
            } else {
               board[k][0] = ((k+1)%a)*-10000;
            }
         }
         
         for(int k = 1; k < m; k++){
            for(int j = 0; j < n; j++){
               board[j][k] = board[j][0] + k%b;
            }
         }
      
      }
      

      for(int k = 0; k < n; k++){
         StringJoiner joiner = new StringJoiner(" ");
         for(int j = 0; j < m; j++){
            joiner.add("" + board[k][j]);
         }
         out.println(joiner);
      }
      
      
      
      out.close();
   }
   
      
}