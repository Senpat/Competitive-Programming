//Divisibility by 8
//too slow
import java.io.*;
import java.util.*;

public class C550{
   
   public static int answer;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      String s = f.readLine();
      
      if(s.contains("0")){
         out.println("YES");
         out.println("0");
         out.close();
         System.exit(0);
      }
      if(s.contains("8")){
         out.println("YES");
         out.println("8");
         out.close();
         System.exit(0);
      }
      
      dothing(s);
      
      out.println("NO");
      
      out.close();
   }
   
   public static void dothing(String s){
      int i;
      if(s.length()<=3) i = Integer.parseInt(s);
      else i = Integer.parseInt(s.substring(s.length()-3));
      if(i%8==0){
         out.println("YES");
         out.println(i);
         out.close();
         System.exit(0);
      } 
      else {
         if(s.length()>1){
            for(int k = 0; k < s.length() && k<3; k++){
               if(k>=1 && s.charAt(s.length()-1-k) == s.charAt(s.length()-k)) continue;
               dothing(s.substring(0,s.length()-1-k) + s.substring(s.length()-k));
            }
         }
      }
      
   }
}