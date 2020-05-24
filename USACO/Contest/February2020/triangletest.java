/*
TASK:
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class triangletest{
   
   public static void main(String[] args) throws IOException{
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("triangletest.out")));
      
      
      int n = 50;
      
      out.println(n);
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            if(Math.random()<.85){
               out.print('*');
            } else {
               out.print('.');
            }
         }
         out.println();
      }
      
        
        
      out.close();
   }
      
}