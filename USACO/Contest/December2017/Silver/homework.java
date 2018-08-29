/*
ID: patrickgzhang
LANG: JAVA
TASK: homework
*/

import java.io.*;
import java.util.*;

class homework{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("homework.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
      
      int num = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[num];
      for(int k = 0; k < num; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      
      double[] values = new double[array.length-2];
      double max = 0;
      for(int k = 0; k < values.length; k++){
         double x = calc(array,k);
         max = Math.max(max,x);
         values[k] = x;
         
      }
      
      for(int k = 0; k < values.length; k++){
         if(values[k] == max){
            out.println(k);
         }
      }
      
      out.close();
   
 
      
   }

   public static double calc(int[] array, int i){
      int min = array[i];
      int index = 0;
      
      for(int k = i; k < array.length; k++){
         min = Math.min(array[k],min);
      }
      
      boolean done = false;
      double sum = 0.0;
      for(int k = i; k < array.length; k++){
         if(array[k] == min && done == false){
            done = true;
         } else {
            sum+=array[k];
         }
      }
      
      System.out.println(i + " " + min + " " + sum + " " + (array.length-i-1));
      
      return sum/((double)(array.length-i-1));
   }








}