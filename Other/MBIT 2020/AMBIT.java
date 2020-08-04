//make sure to make new file!
import java.io.*;
import java.util.*;

public class AMBIT{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      long[] array = new long[n];
      
      st = new StringTokenizer(f.readLine());
      
      long sum = 0L;
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         sum += array[k];
      }
      
      //psum
      long[] psum = new long[n+1];
      psum[0] = 0L;
      for(int k = 1; k <= n; k++){
         psum[k] = psum[k-1] + array[k-1];
      }
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         int a11 = Integer.parseInt(st.nextToken())-1;
         int b11 = Integer.parseInt(st.nextToken())-1;
         
         int a = Math.min(a11,b11);
         int b = Math.max(a11,b11);
         
         
         long a1 = psum[b]-psum[a];
         long a2 = sum-a1;
         
         out.println(Math.min(a1,a2));
      }
         
      
      
      
      
      
      
      
      out.close();
   }
   
      
}