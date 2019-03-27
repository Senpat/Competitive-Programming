/*
TASK:
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class cowdatetest{
   
   public static void main(String[] args) throws IOException{
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowdate.in")));
      
      
      int n = 4000;
      
      Random rand = new Random();
      
      
      out.println(n);
      for(int k = 0; k < n; k++){
         out.println(rand.nextInt(450000)+1);
      }
        
        
      out.close();
   }
      
}