/*
TASK:
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class A623test{
   
   public static void main(String[] args) throws IOException{
      //BufferedReader f = new BufferedReader(new FileReader("t.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));
      
      
      int n = 200000;
      out.println(n);
      for(int k = 0; k < n; k++){
         out.print("1000000000 ");
      }
      out.println();
      for(int k = 0; k < n; k++){
         out.print("1 ");
      }
      
      
        
        
      out.close();
   }
      
}