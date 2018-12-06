/*
TASK:
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class Template{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("t.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("t.out")));
      
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      
        
        
      out.close();
   }
      
}