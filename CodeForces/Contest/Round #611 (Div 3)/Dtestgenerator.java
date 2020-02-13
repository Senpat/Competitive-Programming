/*
TASK:
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class Dtestgenerator{
   
   public static void main(String[] args) throws IOException{
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Dtest.out")));
      
      
      int n = 200000;
      int m = 200000;
      
      out.println(n + " " + m);
      for(int k = 0; k < n; k++){
         out.print(k + " ");
      }
      out.println();
      
        
        
      out.close();
   }
      
}