/*
USER: patrickgzhang
TASK: skidesign
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class skidesign{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("skidesign.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      int[] original = new int[n];
      
      
      for(int k = 0; k < n; k++){
         original[k] = Integer.parseInt(f.readLine());
         
      }
      
      Arrays.sort(original);
      
      if(original[original.length-1]-original[0]<=17){
         out.println(0);
         out.close();
         System.exit(0);
      }
      
      int minmoney = Integer.MAX_VALUE;
      for(int k = original[0]; k < original[original.length-1]-17; k++){
         int money = 0;
         for(int j = 0; j < original.length; j++){
            if(original[j] < k){
               money += Math.pow(k-original[j],2);
            } else if(original[j] > k+17){
               money += Math.pow(original[j]-(k+17),2);
            }
         }
         minmoney = Math.min(money,minmoney);
      }
      
      System.out.println(minmoney);
      out.println(minmoney);
      out.close();
   }
   
   public static void printarray(int[] array){
      for(int k = 0; k < array.length; k++){
         System.out.print(array[k]+" ");
      }
      System.out.println();
   }
   
}
      
      
      
      