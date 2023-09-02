//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2422{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      
      //due to symmetry, answer must be on the diagonal
      
      ArrayList<Long> alist = new ArrayList<Long>();
      
      for(long k = 1; k <= n; k++){
         alist.add(k * (n-k+1));
      }
      
      Collections.sort(alist);
      
      long answer = alist.get((int)n/2);
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
      
}