/*
USER: patrickgzhang
TASK: cowtip
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class cowtip{

   public static int[][] mat;

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("cowtip.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtip.out")));
      
      int num = Integer.parseInt(f.readLine());
      
      mat = new int[num][num];
      
      for(int r = 0; r < num; r++){
         String s = f.readLine();
         for(int c = 0; c < num; c++){
            mat[r][c] = Integer.parseInt(s.substring(c,c+1));
            System.out.println(r + " " + c + " " + Integer.parseInt(s.substring(c,c+1)));
         }
      }
      
      int count = 0;
      while(checkdone()){
         flip(pickx(mat),picky(mat));
         count++;
      }
      
      out.println(count);
      out.close();
   }
      
      
      
   public static int pickx(int[][] matrix){
      int max = 0;
      int x = 0;
      int y = 0;
      
      for(int r = 0; r < matrix.length; r++){
         for(int c = 0; c < matrix[0].length; c++){
            if(matrix[r][c] == 1){
               if(r + c > max){
                  max = r+c;
                  x = r;
                  y = c;
               }
            }
         }
      }
      
      return x;
   }
   
   public static int picky(int[][] matrix){
      int max = 0;
      int x = 0;
      int y = 0;
      
      for(int r = 0; r < matrix.length; r++){
         for(int c = 0; c < matrix[0].length; c++){
            if(matrix[r][c] == 1){
               if(r + c > max){
                  max = r+c;
                  x = r;
                  y = c;
               }
            }
         }
      }
      
      return y;
   }
   
   public static void flip(int a, int b){
      for(int r = 0; r <= a; r++){
         for(int c = 0; c <= b; c++){
            if(mat[r][c] == 1){
               mat[r][c] = 0;
            } else if(mat[r][c] == 0){
               mat[r][c] = 1;
            } else {
               System.out.println("wut");
            }
         }
      }
   }
   
   public static boolean checkdone(){
      for(int r = 0; r < mat.length; r++){
         for(int c = 0; c < mat[0].length; c++){
            if(mat[r][c] == 1){
               return true;
            }
         }
      }
      return false;
   }
}
