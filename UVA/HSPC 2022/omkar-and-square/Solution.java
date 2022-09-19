//make sure to make new file!
import java.io.*;
import java.util.*;

public class Solution{

   public static int n;
   public static long[] bits;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("omkar-and-square.judge.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("omkar-and-square.judge.out")));
      //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      //PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         n = Integer.parseInt(f.readLine());
      
      
         char[][] board = new char[n][n];
         for(int k = 0; k < n; k++){
            String s = f.readLine();
            for(int j = 0; j < n; j++){
               board[k][j] = s.charAt(j);
            }
         }
         
         int[][] maxl = new int[n][n];
         int[][] maxr = new int[n][n];
         int[][] maxu = new int[n][n];
         int[][] maxd = new int[n][n];
         
         
         for(int k = 0; k < n; k++){
            for(int j = 0; j < n; j++){
               if(board[k][j] == '.') maxl[k][j] = 0;
               else if(j >= 1 && board[k][j-1] == '#') maxl[k][j] = maxl[k][j-1]+1;
               else maxl[k][j] = 1;
            }
         }
         
         for(int k = 0; k < n; k++){
            for(int j = n-1; j >= 0; j--){
               if(board[k][j] == '.') maxr[k][j] = 0;
               else if(j < n-1 && board[k][j+1] == '#') maxr[k][j] = maxr[k][j+1]+1;
               else maxr[k][j] = 1;
               
            }
         }
         
         for(int j = 0; j < n; j++){
            for(int k = 0; k < n; k++){
               if(board[k][j] == '.') maxu[k][j] = 0;
               else if(k >= 1 && board[k-1][j] == '#') maxu[k][j] = maxu[k-1][j]+1;
               else maxu[k][j] = 1;
               
            }
         }
         
         for(int j = 0; j < n; j++){
            for(int k = n-1; k >= 0; k--){
               if(board[k][j] == '.') maxd[k][j] = 0;
               else if(k < n-1 && board[k+1][j] == '#') maxd[k][j] = maxd[k+1][j]+1;
               else maxd[k][j] = 1;
               
            }
         }
         
         
         long answer = 0;
         
         for(int k = 0; k < 2*n-1; k++){
            int x;
            int y;
            if(k < n){
               x = k;
               y = 0;
            } else {
               x = 0;
               y = k-n+1;
            }
         
            bits = new long[n+1];
            ArrayList<ArrayList<Integer>> remove = new ArrayList<ArrayList<Integer>>(n+1);
            for(int j = 0; j <= n; j++) remove.add(new ArrayList<Integer>());
            
            for(int d = 1; ((x+d-1) < n) && ((y+d-1) < n); d++){
               int curx = x+d-1;
               int cury = y+d-1;
               if(board[curx][cury] == '#'){
                  update(d,1L);
                  int removei = d+Math.min(maxr[curx][cury],maxd[curx][cury])-1;
                  remove.get(removei).add(d);
               
                  answer += psum(d) - psum(d-Math.min(maxl[curx][cury],maxu[curx][cury]));
               }
               for(int i : remove.get(d)){
                  update(i,-1);
               }
            }
            
            //System.out.println(x + " " + y + " " + answer);
            
         }
         
         
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
   public static void update(int i, long x){
      for(; i <= n; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static long psum(int i){
      long sum = 0;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   
   }
   
   
   
}