/*
ID: patrickgzhang
LANG: JAVA
TASK: transform
*/

import java.io.*;
import java.util.*;

class transform{
   public static int n;
   public static double t;
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("transform.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
      
      n = Integer.parseInt(f.readLine());
      t=(n-1)/2.0;
      
      char[][] start = new char[n][n];
      char[][] end = new char[n][n];
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < n; j++){
            start[k][j] = s.charAt(j);
         }
      }
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < n; j++){
            end[k][j] = s.charAt(j);
         }
      }
      
      System.out.println(1);
      if(checkone(start,end)){
         out.println(1);
         out.close();
         System.exit(0);
      }
      System.out.println(1);
      if(checktwo(start,end)){
         out.println(2);
         out.close();
         System.exit(0);
      }
      System.out.println(1);
      if(checkthree(start,end)){
         out.println(3);
         out.close();
         System.exit(0);
      }
      System.out.println(1);
      char[][] c = makefour(start);
      
      //print c
      printit(c);
      printit(end);
      System.out.println(Arrays.equals(end,c));
      if(arrayequal(c,end)){
         out.println(4);
         out.close();
         System.exit(0);
      }
      System.out.println(1);
      
      if(checkone(c,end) || checktwo(c,end) || checkthree(c,end)){
         out.println(5);
         out.close();
         System.exit(0);
      }
      System.out.println(1);
      if(Arrays.equals(start,end)){
         out.println(6);
         out.close();
         System.exit(0);
      }
      System.out.println(1);
      out.println(7);
      
      out.close();
      
   }
   
   public static boolean checkone(char[][] start, char[][] end){
      boolean b = true;
      for(int k = 0; k < start.length; k++){
         for(int j = 0; j < start[0].length; j++){
            //System.out.println(k + " " + j);
            if(start[k][j] == '@'){
               if(end[(int)(j+t-t)][(int)(-k+t+t)] != '@'){
                  b = false;
               }
            } else {
               if(end[(int)(j+t-t)][(int)(-k+t+t)] != '-'){
                  b = false;
               }
            }
         }
      }
      return b;
   }
   
   public static boolean checktwo(char[][] start, char[][] end){
      boolean b = true;
      double a = (start.length-1)/2.0;
      for(int k = 0; k < start.length; k++){
         for(int j = 0; j < start[0].length; j++){
            //System.out.println(k + " " + j);
            
            if(start[k][j] == '@'){
               
               if(end[(int)(-(k - a) + a)][(int)(-(j - a) + a)] != '@'){
                  return false;
               }
            } else {
               if(end[(int)(-(k - a) + a)][(int)(-(j - a) + a)] != '-'){
                  return false;
               }
            }
         }
      }
      return b;
   }
   
   public static boolean checkthree(char[][] start, char[][] end){
      boolean b = true;
      for(int k = 0; k < start.length; k++){
         for(int j = 0; j < start[0].length; j++){
            if(start[k][j] == '@'){
               if(end[(int)(-j+t+t)][(int)(k+t-t)] != '@'){
                  b = false;
               }
            } else {
               if(end[(int)(-j+t+t)][(int)(k+t-t)] != '-'){
                  b = false;
               }
            }
         }
      }
      return b;
   }
   public static char[][] makefour(char[][] start){
      char[][] c = new char[start.length][start[0].length];
      for(int k = 0; k < start.length; k++){
         for(int j = 0; j < start.length; j++){
            //c[k][j] = start[k][start.length-1-j];
            c[k][c.length-1-j] = start[k][j];
         }
      }/*
      if(n%2 == 1){
         for(int a = 0; a < start.length; a++){
            c[a][start.length/2] = start[a][start.length/2];
         }
      }*/
      return c;
   }
   
   public static boolean arrayequal(char[][] a, char[][] b){
      for(int k = 0; k < a.length; k++){
         for(int j = 0; j < a[0].length; j++){
            if(a[k][j]!=b[k][j]){
               return false;
            }
         }
         
      }
      return true;
   }
   
   public static void printit(char[][] c){
      for(int k = 0; k < c.length; k++){
         for(int j = 0; j < c[0].length; j++){
            System.out.print(c[k][j]);
         }
         System.out.println();
      }
   }
}
               