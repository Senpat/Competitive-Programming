//make sure to make new file!
import java.io.*;
import java.util.*;

public class A529{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String s = f.readLine();
      
      StringBuilder sb = new StringBuilder("");
      
      int cur = 0;
      int count = 1;
      while(cur < s.length()){
         sb.append(s.charAt(cur));
         cur += count;
         count++;
      }
      
      out.println(sb.toString());
      
      
      out.close();
   }
   
}