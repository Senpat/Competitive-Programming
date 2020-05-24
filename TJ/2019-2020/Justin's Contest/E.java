//make sure to make new file!
import java.io.*;
import java.util.*;

public class E{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());

      HashSet<Integer> hset = new HashSet<Integer>();
      
      for(int k = 0; k < n+1; k++){
         int i = Integer.parseInt(f.readLine());
         if(hset.contains(i)) {
            out.println(i);
            out.close();
            return;
         }
         hset.add(i);
      }
         
      
      
      

      
      
      
      
      
      out.close();
   }
   
      
}