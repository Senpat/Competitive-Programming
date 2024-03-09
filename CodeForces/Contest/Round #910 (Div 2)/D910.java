//make sure to make new file!
import java.io.*;
import java.util.*;

public class D910{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         
         long[] a = new long[n];
         long[] b = new long[n];
         long sum = 0L;
         long minmax = Long.MAX_VALUE;
         long maxmin = 0L;
         for(int k = 0; k < n; k++){
            a[k] = Long.parseLong(st1.nextToken());
            b[k] = Long.parseLong(st2.nextToken());
            sum += Math.abs(a[k]-b[k]);
            
            minmax = Math.min(minmax,Math.max(a[k],b[k]));
            maxmin = Math.max(maxmin,Math.min(a[k],b[k]));
         }
         
         sum += 2*Math.max(0,maxmin-minmax);
         
         out.println(sum);
      

      }
      
      
      
      
      out.close();
   }
   
      
}