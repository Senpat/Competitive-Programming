/*
TASK: range
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class range{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("range.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("range.out")));
      
      
      int n = Integer.parseInt(f.readLine());
      
      int[][] board = new int[n+1][n+1];
      
      for(int k = 1; k <= n; k++){
         String s = f.readLine();
         for(int j = 1; j <= n; j++){
            board[k][j] = Character.getNumericValue(s.charAt(j-1));  
         }
      }
      
      int[][] psums = new int[n+1][n+1];
      for(int k = 1; k <= n; k++){
         for(int j = 1; j <= n; j++){
            psums[k][j] = psums[k-1][j] + psums[k][j-1] - psums[k-1][j-1] + board[k][j];  
         }
      }
        
      int[] answer = new int[n+1];
      
      //starting point
      for(int k = 1; k <= n; k++){
         for(int j = 1; j <= n; j++){
            //size
            for(int size = 2; size <= n && k + size-1 <= n && j + size-1 <= n; size++){
               int num = psums[k+size-1][j+size-1] - psums[k+size-1][j-1] - psums[k-1][j+size-1] + psums[k-1][j-1];
               if(num == size*size){
                  answer[size]++;
               }
            }
         }  
      }
      
      for(int k = 2; k <= n; k++){
         if(answer[k] >= 1){
            out.println(k + " " + answer[k]);
         }
      }
      
      
      
      out.close();
   }
      
}