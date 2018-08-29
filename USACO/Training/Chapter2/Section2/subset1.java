/*
USER: pgz11901
TASK: subset
LANG: JAVA
*/

//Better solution to subset
import java.io.*;
import java.util.*;

class subset1{



   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("subset.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      int shouldbe = n*(n+1)/2;
      
      if(shouldbe%2!=0){
         System.out.println(0);
         out.println(0);
         out.close();
         System.exit(0);
      }
      
      shouldbe/=2;
      
      int[][] board = new int[(int)Math.pow(n+1,2)/4][n+1];
           
      board[0][0] = 1;
      for(int k = 1; k < n; k++){
         for(int j = 0; j <= shouldbe; j++){
            board[j][k] = board[j][k-1];                    //don't add a number, just use number from left of column
         }
         for(int j = 0; j <= shouldbe - k; j++){
            board[j+k][k]+=board[j][k-1];                   //add number, add number in appropriate column the value of left.
         }
      }
            
      System.out.println(board[shouldbe][n-1]);
      out.println(board[shouldbe][n-1]);
      out.close();
      
      
      
      
      
      
   }

   
}