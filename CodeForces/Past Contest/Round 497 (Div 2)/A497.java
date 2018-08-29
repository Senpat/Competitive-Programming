//Romaji
import java.io.*;
import java.util.*;

public class A497{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      if(check(s)) out.println("YES");
      else out.println("NO");
      
      out.close();
      
   }
   
   public static boolean v(char c){
      return c=='a' || c == 'e' || c == 'i' || c =='o' || c=='u';
   }
   public static boolean check(String s){
      for(int k = 1; k < s.length(); k++){
         if(s.charAt(k-1) != 'n' && !v(s.charAt(k-1)) && !v(s.charAt(k))){
            return false;
         }
      }
      return s.charAt(s.length()-1) == 'n' || v(s.charAt(s.length()-1));
   }
}