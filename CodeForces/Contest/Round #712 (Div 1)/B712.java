//make sure to make new file!
import java.io.*;
import java.util.*;

public class B712{
   
   public static BufferedReader f;
   public static PrintWriter out;
   
   public static int n;
   public static int x1;
   public static int y1;
   public static int x2;
   public static int y2;
   
   public static boolean filled1;
   public static boolean filled2;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      int[][] board = new int[n][n];
      
      x1 = 0;
      y1 = 0;
      x2 = 1;
      y2 = 0;
      
      filled1 = false;
      filled2 = false;
      
      while(!filled1 && !filled2){
         int x = read();
         
         if(x == 1){
            query(2,x2,y2);
            board[x2][y2] = 2;
            inc2();
         } else {
            query(1,x1,y1);
            board[x1][y1] = 1;
            inc1();
         }
      }
      
      if(filled1){
         //fill 2s with 2 or 3
         while(!filled2){
            int x = read();
            if(x == 2){
               query(3,x2,y2);
               board[x2][y2] = 3;
               inc2();
            } else {
               query(2,x2,y2);
               board[x2][y2] = 2;
               inc2();
            }
         }
      } else {
         //fill 1s with 1 or 3
         while(!filled1){
            int x = read();
            if(x == 1){
               query(3,x1,y1);
               board[x1][y1] = 3;
               inc1();
            } else {
               query(1,x1,y1);
               board[x1][y1] = 1;
               inc1();
            }
         }
      }
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void inc1(){
      x1+=2;
      if((n%2 == 0 && x1 == n) || (n%2 == 1 && x1 == n+1)){
         x1 = 1;
         y1++;
      } else if((n%2 == 1 && x1 == n) || (n%2 == 0 && x1 == n+1)){
         x1 = 0;
         y1++;
      }
      if(y1 >= n){
         filled1 = true;
      }
   }
   
   public static void inc2(){
      x2+=2;
      if((n%2 == 0 && x2 == n) || (n%2 == 1 && x2 == n+1)){
         x2 = 1;
         y2++;
      } else if((n%2 == 1 && x2 == n) || (n%2 == 0 && x2 == n+1)){
         x2 = 0;
         y2++;
      }
      if(y2 >= n){
         filled2 = true;
      }
   }
   
   public static int read() throws IOException{
      return Integer.parseInt(f.readLine());
   }
   
   public static void query(int b, int x, int y){
      out.println(b + " " + (x+1) + " " + (y+1));
      out.flush();
   }
      
}