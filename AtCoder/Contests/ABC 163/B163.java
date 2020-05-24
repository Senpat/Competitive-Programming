//make sure to make new file!
import java.io.*;
import java.util.*;

public class B163{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < m; k++){
         n -= Long.parseLong(st.nextToken());
      }
      
      if(n < 0){
         out.println(-1);
      } else {
         out.println(n);
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}