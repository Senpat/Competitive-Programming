//make sure to make new file!
import java.io.*;
import java.util.*;

public class gentests{
   
   public static PrintWriter out;
   
   public static int sumn = 0;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(new BufferedWriter(new FileWriter("omkar-and-square.judge.in")));
      
      int T = 1010;
      out.println(T);
      
      //small
      genrandom(3,1,1,0,0.0);
      genrandom(3,1,1,0,0.0);
      genrandom(5,3,1,0,0.0);
      genrandom(5,3,1,0,0.0);
      
      for(int k = 0; k < 1000; k++){
         int n = (int)(Math.random()*10)+5; 
         int ns = (int)(Math.random()*n)+3;
         int nseg = (int)(Math.random()*n)+3;
         int fseg = (int)(Math.random()*3);
         
         genrandom(n,ns,nseg,fseg,0.5);
      }
      
      genmax(3);
      genmax(100);
      genmax(480);
      
      
      genrandom(200,400,200,4,0.8);
      genrandom(500,500,500,8,0.8);
      genrandom(600,600,600,8,0.8);
      
      
      
      
      
      System.out.println(sumn);
      
      out.close();
   }
   
   public static void genmax(int n){
      sumn += n*n;
      
      out.println(n);
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            out.print("#");
         }
         out.println();
      }
   }
   
   public static void genrandom(int n, int numsquares, int numsegments, int fullsegments, double scaledown){
      sumn += n*n;
      
      char[][] board = new char[n][n];
      for(int k = 0; k < n; k++) Arrays.fill(board[k],'.');
      
      for(int s = 0; s < numsquares; s++){
         int size = (int)(Math.random()*n)+1;
         
         //scale it down potentially
         if(Math.random() < scaledown){
            size = (int)Math.sqrt((double)size);
         }
         
         int x = (int)(Math.random()*(n-size));
         int y = (int)(Math.random()*(n-size));
         
         for(int k = 0; k < size; k++){
            board[x+k][y] = '#';
            board[x][y+k] = '#';
            board[x+size-1-k][y+size-1] = '#';
            board[x+size-1][y+size-1-k] = '#';
         }
      }
      
      for(int s = 0; s < numsegments; s++){
         int size = (int)(Math.random()*n)+1;
         
         int x = (int)(Math.random()*n);
         int y = (int)(Math.random()*(n-size));
         
         if(Math.random() < 0.5){
            
            for(int k = 0; k < size; k++){
               board[x][y+k] = '#';
            }
         
         } else {
         
            for(int k = 0; k < size; k++){
               board[y+k][x] = '#';
            }
         }
         
      }
      
      for(int s = 0; s < fullsegments; s++){
         int x = (int)(Math.random()*n);
         
         if(Math.random()<0.5){
            for(int y = 0; y < n; y++){
               board[x][y] = '#';
            }
         } else {
            for(int y = 0; y < n; y++){
               board[y][x] = '#';
            }
         }
      }
      
      out.println(n);
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            out.print(board[k][j]);
         }
         out.println();
      }
   }
   
   
      
}