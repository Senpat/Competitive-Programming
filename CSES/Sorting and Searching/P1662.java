//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1662{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] array = new int[n];
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int psum = 0;
      long[] freq = new long[n];
      freq[0] = 1L;
      long answer = 0L;
      
      for(int k = 0; k < n; k++){
         psum = ((psum + array[k])%n + n)%n;
         answer += freq[psum];
         freq[psum]++;
      }
      
      out.println(answer);
      
       
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}