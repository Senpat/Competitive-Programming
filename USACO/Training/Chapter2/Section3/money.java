/*
USER: pgz11901
TASK: money
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class money{

  public static void main(String[] args)throws IOException{
    BufferedReader f = new BufferedReader(new FileReader("money.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));

		StringTokenizer st = new StringTokenizer(f.readLine());

		int v = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());

//		int[] array = new int[v]; 
      ArrayList<Integer> al = new ArrayList<Integer>();   
      int acount = 0;
      while(acount<v){
         st = new StringTokenizer(f.readLine());
         while(st.hasMoreTokens()){
            int i = Integer.parseInt(st.nextToken());
            if(i<=n) al.add(i);
            acount++;
         }
         
      }
      
		/*for(int k = 0; k < array.length; k++){
			array[k] = Integer.parseInt(st.nextToken());
		}*/

		long[][] board = new long[al.size()][n+1];

      for(int k = 0; k < board.length; k++){
   		Arrays.fill(board[k],0L);
      }
      
		for(int k = 0; k < al.size(); k++){
			board[k][al.get(k)]++;
		}
		for(int k = 1; k < board[0].length; k++){
 		   for(int j = 0; j < board.length; j++){
            for(int h = j; h < al.size(); h++){
               if(k+al.get(h)<=board[0].length){
                  board[h][k+al.get(h)-1]+=board[j][k-1];
               }
            }
         }
      }
      
      long sum = 0L;
      for(int k = 0; k < board.length; k++){
         sum += board[k][n];
      }
         
         
		System.out.println(sum);
		out.println(sum);
		out.close();
      
      
      
      
      
      
      
      
      
      
      /*int c = 0;
      for(int k = 0; k < board.length; k++){
         for(int j = 0; j < board[0].length; j++){
            board[k][j] = c;
            c++;
         }
      }
      
      for(int k = 0; k < board.length; k++){
         for(int j = 0; j < board[0].length; j++){
            System.out.print(board[k][j] + " ");
         }
         System.out.println();
      }*/
	}
   
   
   public static void printboard(long[][] board){
      for(int k = 0; k < board.length; k++){
         for(int j = 0; j < board[0].length; j++){
            System.out.print(board[k][j] + " ");
         }
         System.out.println();
      }
   }
}
