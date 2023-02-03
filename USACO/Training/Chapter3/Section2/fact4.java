/*
TASK: fact4
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class fact4{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("fact4.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fact4.out")));
      
      long MOD = 1000000000000L;
      
      long n = Long.parseLong(f.readLine());
      
      long x = 1L;
      
      for(long i = 1L; i <= n; i++){
         x = (x * i + MOD)%MOD;
         while(x % 10 == 0) x /= 10L;
      }
      
      out.println(x % 10);
        
        
      out.close();
   }
      
}