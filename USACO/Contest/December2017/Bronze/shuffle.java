/*
ID: patrickgzhang
LANG: JAVA
TASK: shuffle
*/

import java.io.*;
import java.util.*;

class shuffle{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("shuffle.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("shuffle.out")));
      
      int num = Integer.parseInt(f.readLine());
      
      String com = f.readLine();
      int[] commands = new int[num];
      for(int k = 0; k < num; k++){
         commands[k] = Integer.parseInt(com.split(" ")[k]);
         //System.out.println(Integer.parseInt(com.split(" ")[k]));

      }
      
      String i = f.readLine();
      int[] id = new int[num];
      for(int k = 0; k < num; k++){
         id[k] = Integer.parseInt(i.split(" ")[k]);
      }
      
      for(int k = 0; k < 3; k++){
         id = shuffle1(id,commands);
      }
      
      for(int k = 0; k < id.length; k++){
         out.println(id[k]);
      }
      out.close();
      
   }


   public static int[] shuffle1(int[] id, int[] commands){
      int[] array = new int[id.length];
      
      for(int k = 0; k < commands.length; k++){
         array[indexof(commands,k+1)] = id[k];
      }
      
      return array;
   }

   public static int indexof(int[] array, int x){
      for(int k = 0; k < array.length; k++){
         if(array[k] == x){
            return k;
         }
         System.out.println(array[k] + " " + x);
      }
      System.out.println(x);
      return -1;
   }



}
      