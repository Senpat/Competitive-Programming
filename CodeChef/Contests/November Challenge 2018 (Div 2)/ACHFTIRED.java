//make sure to make new file!
import java.io.*;
import java.util.*;

public class ACHFTIRED{
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      for(int k = 0; k < n; k++) out.println("YES");
      
      
      out.close();
   }
   
}