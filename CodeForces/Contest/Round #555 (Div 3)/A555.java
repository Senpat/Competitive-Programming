//make sure to make new file!
import java.io.*;
import java.util.*;

public class A555{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      
      HashSet<Integer> hs = new HashSet<Integer>();
      
      //hs.add(n);
      
      int i = n+1;
      while(!hs.contains(i)){
         while(i%10 == 0) i/=10;
         hs.add(i);
         //if(i%10 == 0) i/=10;
         i++;
      }
      hs.add(n);
      out.println(hs.size());
      

      
      
      
      
      out.close();
   }
   
      
}