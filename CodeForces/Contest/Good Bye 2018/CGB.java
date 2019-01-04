//make sure to make new file!
import java.io.*;
import java.util.*;

public class CGB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      
      ArrayList<Long> alist = new ArrayList<Long>();
      ArrayList<Long> alist2 = new ArrayList<Long>();
      long sq = (long)Math.floor(Math.sqrt(n));
      
      for(long k = 1L; k <= sq; k++){
         if(n%k != 0) continue;
         
         alist.add((n/k) * (1 + n-k+1)/2);
         if(n/k != k) alist2.add(k * (1 + n-n/k + 1)/2);
         
      }
      
      for(long i : alist2){
         out.print(i + " ");
      }
      
      for(int k = alist.size()-1; k >= 0; k--){
         out.print(alist.get(k) + " ");
      }
      
      
      
      out.close();
   }
   
}