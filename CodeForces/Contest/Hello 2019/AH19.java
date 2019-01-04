//make sure to make new file!
import java.io.*;
import java.util.*;

public class AH19{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < 5; k++){
         String s1 = st.nextToken();
         
         if(s.charAt(0) == s1.charAt(0) || s.charAt(1) == s1.charAt(1)){
            out.println("YES");
            out.close();
            System.exit(0);
         }
      }
      
      out.println("NO");   
      
      
      out.close();
   }
   
}