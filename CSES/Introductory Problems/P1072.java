//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1072{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 10000;
      long[] answer = new long[N+1];
      
      answer[1] = 0;
      answer[2] = 6;
      answer[3] = 28;
      
      for(int k = 4; k <= N; k++){
         long x = (long)k;
         long attack = 0L;
         attack += 8L*(x-4)*(x-4);
         attack += 6L*(4L*(x-4));
         attack += 4L*(4L*(x-4) + 4);
         attack += 3L*8;
         attack += 2L*4;
         
         attack >>= 1;
         
         long total = x*x * (x*x - 1) / 2L;
         answer[k] = total-attack;
      }
      
      int n = Integer.parseInt(f.readLine());
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 1; k <= n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}