//make sure to make new file!
import java.io.*;
import java.util.*;

public class B229{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      String a = st.nextToken();
      String b = st.nextToken();
      
      while(a.length() < b.length()) a = "0" + a;
      while(b.length() < a.length()) b = "0" + b;
      
      boolean found = false;
      for(int k = 0; k < a.length(); k++){
         if(Character.getNumericValue(a.charAt(k)) + Character.getNumericValue(b.charAt(k)) >= 10){
            found = true;
            break;
         }
      }
      
      if(found){
         out.println("Hard");
      } else {
         out.println("Easy");
      }
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}