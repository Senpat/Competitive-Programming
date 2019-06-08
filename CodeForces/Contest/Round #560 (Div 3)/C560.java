//make sure to make new file!
import java.io.*;
import java.util.*;

public class C560{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String s = f.readLine();
      
      StringBuilder sb = new StringBuilder("");
      
      int i = 0;
      while(i<n){
         //find next two to add
         char c = s.charAt(i);
         while(i < n && s.charAt(i) == c){
            i++;
         }
         if(i < n){
            sb.append(c);
            sb.append(s.charAt(i));
         }
         i++;
      }
      
      
      out.println(n-sb.length());
      out.println(sb.toString());
         
      

      
      
      
      
      out.close();
   }
   
      
}