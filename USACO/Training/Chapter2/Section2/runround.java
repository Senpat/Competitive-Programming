/*
USER: pgz11901
TASK: runround
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class runround{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("runround.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      n++;
            
      while(true){
         if(check(n)){
            System.out.println(n);
            out.println(n);
            out.close();
            System.exit(0);
         }
         n++;
      }
      
   }
   
   public static boolean check(int i){
      String s = String.valueOf(i);
      
      char cur = s.charAt(0);
      int curint = 0;
      ArrayList<Integer> used = new ArrayList<Integer>();
      //used.add(Character.getNumericValue(cur));
      
      for(int k = 0; k < s.length(); k++){
         int x = Character.getNumericValue(cur);
         //System.out.println(x);
         cur = s.charAt((curint + x)%s.length());
         curint = (curint+x)%s.length();
         if(used.contains(Character.getNumericValue(cur))) return false;
         used.add(Character.getNumericValue(cur));
      }
      
      return true;
   }
   
   
   
}