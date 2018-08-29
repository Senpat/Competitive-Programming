/*
USER: patrickgzhang
LANG: JAVA
TASK: palsquare
*/

import java.io.*;
import java.util.*;

class palsquare{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("palsquare.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
      
      int base = Integer.parseInt(f.readLine());
      
      for(int k = 1; k<=300; k++){
         if(ispal(k,base)){
            out.println(Integer.toString(k,base).toUpperCase() + " " + Integer.toString((int)Math.pow(k,2),base).toUpperCase());
         }
      }
      
      out.close();
      
      
   }
   public static boolean ispal(int n, int b){
      int squared = (int)Math.pow(n,2);
      String s = Integer.toString(squared,b);
      String back = "";
      for(int k = s.length()-1; k >= 0; k--){
         back+=s.charAt(k);
      }
      
      if(s.equals(back))
         return true;
      return false;
   }
}