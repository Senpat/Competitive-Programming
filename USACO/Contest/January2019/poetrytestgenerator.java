/*
TASK:
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class poetrytestgenerator{
   
   public static void main(String[] args) throws IOException{
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("poetry.in")));
      
      int n = 5000;
      int m = 100000;
      int k = 5000;
      
      out.println(n + " " + m + " " + k);
      
      for(int q = 1; q <= n; q++){
         out.println(k + " " + q);
      }
      
      for(int q = 0; q < m; q++){
         out.println("A");
      }
      
      
        
        
      out.close();
   }
      
}