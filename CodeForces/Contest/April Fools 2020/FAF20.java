//make sure to make new file!
import java.io.*;
import java.util.*;

public class FAF20{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      
      HashSet<String> yes = new HashSet<String>();
      yes.add("GENIUS");
      yes.add("IRENE");
      yes.add("REVOLVER");
      yes.add("WATSON");
      
      HashSet<String> no = new HashSet<String>();
      no.add("DOCTOR");
      no.add("MARY");
      no.add("SMARTPHONE");
      no.add("HOLMES");
      
      if(!no.contains(s)){
         out.println("YES");
      } else {
         out.println("NO");
      }
      
      
      

      
      
      
      
      
      out.close();
   }
   
      
}