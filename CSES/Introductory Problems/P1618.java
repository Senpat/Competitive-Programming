//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1618{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N5 = 20;
      long[] pow5 = new long[N5];
      pow5[0] = 1L;
      for(int k = 1; k < N5; k++){
         pow5[k] = 5L * pow5[k-1];
      }
      
      long n = Long.parseLong(f.readLine());
      
      long answer = 0L;
      for(int k = 1; k < N5; k++){
         answer += n/pow5[k];
      }
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}