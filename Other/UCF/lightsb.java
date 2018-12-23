//make sure to make new file!
import java.io.*;
import java.util.*;

public class lightsb{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n1 = Integer.parseInt(f.readLine());
      
      for(int k = 1; k <= n1; k++){
      
         String s = f.readLine();
         
         StringBuilder a = new StringBuilder();
         StringBuilder b = new StringBuilder();
         
         boolean bool = false;
         int index = 0;
         while(!bool && index*2 < s.length()){
            a.append(s.charAt(index));
            b.insert(0,s.charAt(s.length()-index-1));
            
            if(a.toString().equals(b.toString())){
               bool = true;
            }
            index++;
         }
         
         if(bool) out.println("OK");
         else out.println("MESSY");
      
      }
      
      out.close();
   }
   
}