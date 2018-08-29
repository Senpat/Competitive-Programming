//Round #494 (Div 3) D
//Coins and Queries

import java.io.*;
import java.util.*;

public class Candq{
   
   public static void main(String[] args)throws IOException{
      Scanner sc = new Scanner(System.in);
      
      StringTokenizer st = new StringTokenizer(sc.nextLine());
      
      int c = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      int[] coins = new int[c];
      int[] queries = new int[q];
      
      st = new StringTokenizer(sc.nextLine());
      
      for(int k = 0; k < c; k++){
         coins[k] = Integer.parseInt(st.nextToken());
      }
      
      int maxq = 0;
      for(int k = 0; k < q; k++){
         queries[k] = Integer.parseInt(sc.nextLine());
         maxq = Math.max(maxq,queries[k]);
      }
      
      int[][] board = new int[c][maxq+1];
      
      for(int k = 0; k < board.length; k++){
         Arrays.fill(board[k],-1);
      }
      
      for(int k = 0; k < coins.length; k++){
         if(coins[k]<=board[0].length){
            board[k][coins[k]] = 1;
         }
      }
      for(int k = 1; k < board[0].length; k++){
         for(int j = 0; j < board.length; j++){
            
            for(int h = j; h < coins.length; h++){
               
               if(board[j][k-1]!=-1){
                  if(k+coins[h]<=board[0].length){
                     board[h][k+coins[h]-1] = min(board[h][k+coins[h]-1],board[j][k-1]+1);
                  }
               }
            }
         }
      }
      
      for(int k = 0; k < queries.length; k++){
         int min = -1;
         for(int j = 0; j < board.length; j++){
            min = min(min,board[j][queries[k]]);
         }
         System.out.println(min);
      }
      
      
   }
   
   public static int max(int a, int b){
      if(a == -1) 
         return b;
      return Math.max(a,b);  
   }
   
   public static int min(int a, int b){
      if(a == -1) 
         return b;
      return Math.min(a,b);  
   }
   
}