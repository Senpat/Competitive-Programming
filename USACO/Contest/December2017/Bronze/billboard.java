/*
ID: patrickgzhang
LANG: JAVA
TASK: billboard
*/

import java.io.*;
import java.util.*;

class billboard{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("billboard.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("billboard.out")));
      
      
      String b1 = f.readLine();
      String b2 = f.readLine();
      String truck = f.readLine();
      
      String[] b1c = b1.split(" ");
      String[] b2c = b2.split(" ");
      String[] truckc = truck.split(" ");
      
      int[] bill1 = new int[4];
      int[] bill2 = new int[4];
      int[] truckarray = new int[4];
      
      for(int k = 0; k < b1c.length; k++){
         bill1[k] = Integer.parseInt(b1c[k]);
         bill2[k] = Integer.parseInt(b2c[k]);
         truckarray[k] = Integer.parseInt(truckc[k]);
         
      }
      
      System.out.println(calc(bill1,truckarray) + " " + calc(bill2,truckarray));
      out.println(calc(bill1,truckarray) + calc(bill2,truckarray));
      out.close();
      
      
   }

   public static int calc(int[] board, int[] truck){
      int x = 0;
      int y = 0;
      
      if(truck[2]>=board[2] && truck[0] <= board[0]){
         System.out.println(1);
         x = board[2]-board[0];
      } 
      else if(truck[2] <= board[2] && truck[0] >= board[0]){
         System.out.println(2);
         x = truck[2]-truck[0];
      } 
      else if(truck[2] <= board[0]){
         x = 0;
      } 
      else if(truck[0] >= board[2]){
         x = 0;
      }
      else if(truck[2] >= board[2]){
         System.out.println(3);
         x = board[2]-truck[0];
      } 
      else if(board[0] >= truck[0]){
         System.out.println(4);
         x = truck[2]-board[0];
      }
      
      if(truck[3]>=board[3] && truck[1] <= board[1]){
         y = board[3]-board[1];
      } 
      else if(truck[3] <= board[3] && truck[1] >= board[1]){
         y = truck[3]-truck[1];
      } 
      else if(truck[1] >= board[3]){
         y = 0;
      } 
      else if(truck[3] <= board[1]){
         y = 0;
      }
      else if(truck[3] >= board[3]){
         y = board[3]-truck[1];
      } 
      else if(board[1] >= truck[1]){
         y = truck[3]-board[1];
      }
      
      System.out.println(x + " " + y);
      return ((board[2]-board[0]) * (board[3]-board[1])) - (x * y);
   }

}


      
      
