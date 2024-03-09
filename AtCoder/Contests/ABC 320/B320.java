//make sure to make new file!
import java.io.*;
import java.util.*;

public class B320{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      int n = s.length();
      
      int max = 0;
      
      for(int k = 0; k < n; k++){
         for(int j = k; j < n; j++){
            //check if k to j is a palindrome
            boolean fail = false;
            
            int r = j;
            for(int h = k; h <= j; h++){
               if(s.charAt(h) != s.charAt(r)){
                  fail = true;
                  break;
               }
               r--;
               
            }
            
            if(!fail){
               max = Math.max(max,j-k+1);
            }
         }
      }
      
      out.println(max);
      
      
      
      
      
      
      out.close();
   }
   
      
}