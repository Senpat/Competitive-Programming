//Divisibility by 8
//semi-tutorial
import java.io.*;
import java.util.*;

public class C550b{
   
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
      
      for(int k = 0; k < s.length()-1; k++){
         for(int j = k+1; j < s.length(); j++){
            if((Character.getNumericValue(s.charAt(k))*10+Character.getNumericValue(s.charAt(j)))%8==0){
               out.println("YES");
               out.println(""+s.charAt(k)+s.charAt(j));
               out.close();
               System.exit(0);
            }
         }
      }
      
      for(int k = 0; k < s.length()-2; k++){
         for(int j = k+1; j < s.length()-1; j++){
            for(int h = j+1; h < s.length(); h++){
               if((Character.getNumericValue(s.charAt(k))*100+Character.getNumericValue(s.charAt(j))*10 + Character.getNumericValue(s.charAt(h)))%8==0){
                  out.println("YES");
                  out.println(""+s.charAt(k)+s.charAt(j)+s.charAt(h));
                  out.close();
                  System.exit(0);
               }
            }
         }
      }
      
      out.println("NO");
      out.close();
   }
   
 
}