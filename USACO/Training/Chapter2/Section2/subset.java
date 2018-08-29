/*
USER: pgz11901
TASK: subset
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class subset{

   public static long[][] board;

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
      
      board = new long[(int)Math.pow(n,2)/2][n+1];
           
      for(int k = 0; k < board.length;k++){
         Arrays.fill(board[k],-1);
      }
            
      long counter = count(shouldbe,n);
      
      System.out.println(counter/2);
      out.println(counter/2);
      out.close();
      
      
      
      
      
      
   }

   public static long count(int sum, int num){
      if(sum<0||num<0) return 0;
      if(board[sum][num]!=-1) return board[sum][num];
      if(sum==0&&num==0) return board[sum][num] = 1;
      return board[sum][num] = count(sum,num-1) + count(sum-num,num-1);
   }
   
   
}