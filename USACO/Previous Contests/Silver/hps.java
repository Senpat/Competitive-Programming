/*
USER: patrickgzhang
TASK: hps
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class hps{


   public static final String H = "hoof";
   public static final String P = "paper";
   public static final String S = "scissors";

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("hps.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
      
      int num = Integer.parseInt(f.readLine());
      
      String[] array = new String[num];
      for(int k = 0; k < array.length; k++){
         array[k] = f.readLine();
      }
      
      out.println(Math.max(dothing(array,H,P,S),Math.max(dothing(array,H,S,P),Math.max(dothing(array,P,H,S),Math.max(dothing(array,P,S,H),Math.max(dothing(array,S,H,P),dothing(array,S,P,H)))))));
      out.close();
   }
  
   public static int dothing(String[] array, String one, String two, String three){
      int count = 0;
      for(int k = 0; k < array.length; k++){
         String s1 = " ";
         String s2 = " ";
         if(array[k].charAt(0) == '1'){
            s1 = one;
         } else if(array[k].charAt(0) == '2'){
            s1 = two;
         } else if(array[k].charAt(0) == '3'){
            s1 = three;
         } else {
            System.out.println("wut1");
         }
         if(array[k].charAt(2) == '1'){
            s2 = one;
         } else if(array[k].charAt(2) == '2'){
            s2 = two;
         } else if(array[k].charAt(2) == '3'){
            s2 = three;
         } else {
            System.out.println("wut2");
         }
         
         if(helper(s1,s2)){
            count++;
         }
      }
      return count;
   }
      
      
      
      
   public static boolean helper(String s1, String s2){
      if(s1 == H && s2 == S)
         return true;
      if(s1 == S && s2 == P)
         return true;
      if(s1 == P && s2 == H)
         return true;
      return false;
   }
}