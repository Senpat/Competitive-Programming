//make sure to make new file!
import java.io.*;
import java.util.*;

public class E71{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      out.print("?");
      for(int k = 0; k < 100; k++){
         out.print(" " + k);
      }
      out.println();
      out.flush();
      
      //get first 7 digits
      long a = Long.parseLong(f.readLine());
      String astring = Long.toBinaryString(a);
      int alen = astring.length();
      for(int k = 0; k < 14-alen; k++){
         astring = "0" + astring;
      }
      
      out.print("?");
      for(int k = 1000; k < 1100; k++){
         out.print(" " + k);
      }
      out.println();
      out.flush();
      
      //get last 7 digits
      long b = Long.parseLong(f.readLine());
      String bstring = Long.toBinaryString(b);
      int blen = bstring.length();
      for(int k = 0; k < 14-blen; k++){
         bstring = "0" + bstring;
      }
      
      
      
      long answer = Long.parseLong(astring.substring(0,7) + bstring.substring(7),2);
      out.println("! " + answer);
      
      
      

      
      
      
      
      out.close();
   }
   
      
}