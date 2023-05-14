/*
TASK:
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class p1gen{
   
   public static void main(String[] args) throws IOException{
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("p1.out")));
      
      int l = 1;
      int r = 200000;
      int x = 200000;
      
      out.println(l + " " + r + " " + x);
      StringJoiner sj = new StringJoiner("\n");
      StringJoiner line1 = new StringJoiner("");
      line1.add("a ");
      for(int k = 0; k < 100000; k++){
         line1.add("a");
      }
      
      sj.add(line1.toString());
      
      for(int k = 0; k < x-1; k++){
         sj.add("a a");
      }
      
      out.println(sj.toString());
      
      
        
        
      out.close();
   }
      
}