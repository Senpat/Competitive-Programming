//make sure to make new file!
import java.io.*;
import java.util.*;

public class B906b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long c = Long.parseLong(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         long[] array = new long[n+1];
         long[] psum = new long[n+1];
         for(int k = 1; k <= n; k++){
            array[k] = Long.parseLong(st.nextToken());
            psum[k] = psum[k-1] + array[k];
         }
         
         boolean succeed = false;
         long sum = array[1];
         for(int k = 2; k <= n; k++){
            if(sum + array[k] >= (long)k * c){
               sum = psum[k];
               if(n == k) succeed = true;
            }
         }
         
         if(succeed){
            out.println("Yes");
         } else {
            out.println("No");
         }
         
      }
      
      
      
      
      out.close();
   }
   
      
}