//make sure to make new file!
import java.io.*;
import java.util.*;

public class B599{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
   
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         String s1 = f.readLine();
         String s2 = f.readLine();
         
         if(check(s1,s2)){
            out.println("Yes");
         } else {
            out.println("No");
         }
      
      }
      
   
      
      
      
      
      out.close();
   }
   
   public static boolean check(String s1, String s2){
      char a = '?';
      char b = '?';
      boolean secondused = false;
      
      int n = s1.length();
      for(int k = 0; k < n; k++){
         if(s1.charAt(k) == s2.charAt(k)) continue;
         if(secondused) return false;
         if(a != '?'){
            if(s1.charAt(k) != a || s2.charAt(k) != b){
               return false;
            }
            secondused = true;
         
         } else {
            a = s1.charAt(k);
            b = s2.charAt(k);
         }
      }
      
      return (a != '?' && secondused);
   }
      
      
}