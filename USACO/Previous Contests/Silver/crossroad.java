/*
USER: patrickgzhang
LANG: JAVA
TASK: crossroad
*/

import java.io.*;
import java.util.*;

class crossroad{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("crossroad.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crossroad.out")));
      
      int num = Integer.parseInt(f.readLine());
      
      String[] array = new String[num];
      for(int k = 0; k < num; k++){
         array[k] = f.readLine();
      }
      
      int count = 0;
      
      for(int k = 0; k < num-1; k++){
         String s = array[k];
         String[] mini = s.split(" ");
         
         if(Integer.parseInt(mini[1])==0){
            if(search0(array,mini[0],k+1)){
               count++;
            }
         } else {
            if(search1(array,mini[0],k+1)){
               count++;
            }
         }
      }
      
      out.println(count);
      out.close();
      
      
   }
   
   public static boolean search0(String[] array, String s,int ind){
      for(int k = ind; k < array.length; k++){
         if(array[k].equals(s + " 1")){
            return true;
         }
         if(array[k].equals(s + " 0"))
            return false;
      }
      return false;
   }
   public static boolean search1(String[] array, String s,int ind){
      for(int k = ind; k < array.length; k++){
         if(array[k].equals(s + " 0")){
            return true;
         }
         if(array[k].equals(s + " 1"))
            return false;
      }
      return false;
   }
}