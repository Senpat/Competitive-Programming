//make sure to make new file!
import java.io.*;
import java.util.*;

public class BMRC{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      
      long n = Long.parseLong(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      long answer = 0L;
      for(long k = 1L; k <= n; k++){
         for(long j = k+1; j <= n; j++){
            if(((long)Math.pow(k,2) + (long)Math.pow(j,2)) % m == 0){
               answer+=2;
            }
         }
      }
      
      for(long k = 1L; k <= n; k++){
         if(2 * (long)Math.pow(k,2) % m == 0){
            answer++;
         }
      }
      
      
      out.println(answer);
      
      out.close();
   }
   
}