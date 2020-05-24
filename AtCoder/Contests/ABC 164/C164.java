//make sure to make new file!
import java.io.*;
import java.util.*;

public class C164{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      HashSet<String> hs = new HashSet<String>();
      
      for(int k = 0; k < n; k++){
         hs.add(f.readLine());
      }
      
      out.println(hs.size());
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}