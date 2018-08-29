/*
USER: patrickgzhang
TASK: skidesign
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class skidesign1{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("skidesign.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      int[] original = new int[n];
      int[] change = new int[n];
      
      for(int k = 0; k < n; k++){
         original[k] = Integer.parseInt(f.readLine());
         change[k] = original[k];
      }
      
      Arrays.sort(original);
      Arrays.sort(change);
      
      boolean bool = true;
      int count = 0;
      while(bool){
         if(change[change.length-1-count] - change[count] <= 17){
            //System.out.println(change[count] + " " + change[count+1] + " " + change[change.length-1-count] + " " + change[change.length-1-count-1]);
            if(change[count] >= change[count+1] && change[change.length-1-count] >= change[change.length-1-count-1]){
               bool = false;
            } else {
               count++;
            }
         } else if(change[change.length-1-count] - change[count] == 18){
            int a = change[count+1] - change[count];
            int b = change[change.length-1-count] - change[change.length-2-count];
            if(b < a){
               change[change.length-1-count]--;
            } else {
               change[count]++;
            }
         } else {
            change[count]++;
            change[change.length-1-count]--;
         }
      }
      
      int money = 0;
      for(int k = 0; k < n; k++){
         money += Math.pow(Math.abs(change[k]-original[k]),2);
      }
      printarray(change);
      System.out.println(money);
      out.println(money);
      out.close();
   }
   
   public static void printarray(int[] array){
      for(int k = 0; k < array.length; k++){
         System.out.print(array[k]+" ");
      }
      System.out.println();
   }
   
}
      
      
      
      