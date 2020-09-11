//make sure to make new file!
import java.io.*;
import java.util.*;

public class D524{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long n = Long.parseLong(st.nextToken());
         long m = Long.parseLong(st.nextToken());
         
         long k2 = 1L;
         long k4 = 1L;
         
         long min = -1L;
         long max = -1L;
         
         boolean found = false;
         for(long k = 0L; k <= n; k++){
            min = k2*2L - k - 2L;
            out.println(k + " " + min);
            if(min > m) break;
            if(n >= 31){
               max = Long.MAX_VALUE;
            } else {
               max = (k4-1L)/3L + ((2L << (n-k))-1L)/3L*(k2-1L)*(k2-1L);
               if(max < 0) max = Long.MAX_VALUE;
            }
            out.println(k + " " + max);
            if(m >= min && m <= max){
               found = true;
               long answer = n-k;
               out.println("YES " + answer);
               break;
            }
            
            k2 *= 2L;
            k4 *= 4L;
         }
         
         
         if(!found){
            out.println("NO");
         }
      }
      
      
      
      
      out.close();
   }
   
      
}