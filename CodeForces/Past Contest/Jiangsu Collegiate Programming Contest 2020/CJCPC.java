//make sure to make new file!
import java.io.*;
import java.util.*;

public class CJCPC{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 1; k <= n; k++){
         //get last bit of k
         int i = k;
         int count = 0;
         while((i & 1) != 1){
            i >>= 1;
            count++;
         }
         sj.add("" + (20-count));
      }
      
      out.println(sj.toString());
      
      
      
      
      
      
      out.close();
   }
   
      
}