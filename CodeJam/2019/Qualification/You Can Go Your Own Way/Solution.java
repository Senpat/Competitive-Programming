//You Can Go Your Own Way
import java.io.*;
import java.util.*;

public class Solution{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         int i = Integer.parseInt(f.readLine());
         String s = f.readLine();
         
         out.print("Case #" + q + ": ");
         for(int k = 0; k < s.length(); k++){
            if(s.charAt(k) == 'S'){
               out.print('E');
            } else {
               out.print('S');
            }
         }
         out.println();
      }
      
      

      
      
      
      
      out.close();
   }
   
      
}