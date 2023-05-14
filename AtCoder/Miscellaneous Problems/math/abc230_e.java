//make sure to make new file!
import java.io.*;
import java.util.*;
//floor and ceiling practice
public class abc230_e{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long n = Long.parseLong(f.readLine());
      
      long answer = 0L;
      
      long sqrt = 1;
      while(sqrt*sqrt <= n) sqrt++;
      sqrt--;
      
      //i <= sqrt(n)
      for(long i = 1; i <= sqrt; i++){
         answer += n/i;
      }
      
      //i > sqrt(n) -> x = n/i < sqrt(n)
      for(long x = 1; x <= sqrt; x++){
         //x <= n/i < x+1
         
         //find smallest i that fits
         //n/i < x+1 -> N/(x+1) < i -> i = floor(N/(x+1))+1
         long i1 = n/(x+1)+1;
         
         //find biggest i that fits
         //x <= n/i -> i <= n/x -> i = floor(n/x)
         long i2 = n/x;
         
         answer += x * (i2-i1+1);
      }
      
      //overcount sqrt
      if(n/sqrt == sqrt) answer -= sqrt;
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}