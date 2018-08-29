/* 
ID: patrickgzhang
LANG: JAVA
TASK: ride
*/

import java.util.*;
import java.io.*;

class ride{
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("ride.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      String a1 = st.nextToken();
      
      StringTokenizer st2 = new StringTokenizer(f.readLine());
      String a2 = st2.nextToken();
      
      int i1 = 1;
      for(int k = 0; k < a1.length(); k++){
         i1 *= (a1.charAt(k) - 'A' + 1);
      }
      
      int i2 = 1;
      for(int k = 0; k < a2.length(); k++){
         i2 *= (a2.charAt(k) - 'A' + 1);
      }
      System.out.println(i1 + " " + i2);
      if(i1 % 47 == i2 % 47){
         out.println("GO");
      } else {
         out.println("STAY");
      }
      out.close();
   }
}