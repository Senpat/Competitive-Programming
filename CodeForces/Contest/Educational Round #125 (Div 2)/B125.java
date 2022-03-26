//make sure to make new file!
import java.io.*;
import java.util.*;

public class B125{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         long x = Long.parseLong(st.nextToken());
         long y = Long.parseLong(st.nextToken());
      
         long[] a = new long[n+1];
         a[0] = 0L;
         
         
         long sum = 0L;
         for(int k = 1; k <= n; k++){
            if(a[k-1] + x <= b){
               a[k] = a[k-1]+x;
            } else {
               a[k] = a[k-1]-y;
            }
            sum += a[k];
         }
         
         out.println(sum);

      }
      
      
      
      
      out.close();
   }
   
      
}