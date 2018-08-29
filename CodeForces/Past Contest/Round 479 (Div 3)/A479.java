//Round 479 (Div 3) A
//Wrong Subtraction

import java.io.*;
import java.util.*;

public class A479{

   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int s = Integer.parseInt(st.nextToken());
      
      int n = Integer.parseInt(st.nextToken());
      
      for(int k = 0; k < n; k++){
         if(s%10!=0){
            s--;
         } else {
            s/=10;
         }
      
      }
      
      System.out.println(s);
   }
}