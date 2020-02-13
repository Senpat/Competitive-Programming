//make sure to make new file!
import java.io.*;
import java.util.*;

public class A619{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
         String a = f.readLine();
         String b = f.readLine();
         String c = f.readLine();
         
         if(check(a,b,c)){
            out.println("YES");
         } else {
            out.println("NO");
         }
      }
      
      

      
      
      
      
      
      out.close();
   }
   
 
   public static boolean check(String a, String b, String c){
      for(int k = 0; k < a.length(); k++){
         if(a.charAt(k) == c.charAt(k) || b.charAt(k)==c.charAt(k)){
            continue;
         }
         return false;
      }
      return true;
   }     
}