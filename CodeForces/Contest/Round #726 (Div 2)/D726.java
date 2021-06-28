//make sure to make new file!
import java.io.*;
import java.util.*;

public class D726{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      HashSet<Long> bobs = new HashSet<Long>();
      long i = 2L;
      bobs.add(i);
      while(i <= 1000000000L){
         i*=4L;
         bobs.add(i);
      }
      
      for(int q = 1; q <= t; q++){

         long n = Long.parseLong(f.readLine());
      
         if(n%2 == 1 || bobs.contains(n)){
            out.println("Bob");
         } else {
            out.println("Alice");
         }
      }
      
      
      
      
      out.close();
   }
   
      
}