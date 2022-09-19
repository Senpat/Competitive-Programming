//make sure to make new file!
import java.io.*;
import java.util.*;

public class A126{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
      
         int[] a = new int[n];
         int[] b = new int[n];
         
         for(int k = 0; k < n; k++){
            int ai = Integer.parseInt(st1.nextToken());
            int bi = Integer.parseInt(st2.nextToken());
            
            a[k] = Math.min(ai,bi);
            b[k] = Math.max(ai,bi);
            
         }
         
         long answer = 0L;
         for(int k = 0; k < n-1; k++){
            answer += (long)Math.abs(a[k]-a[k+1]) + (long)Math.abs(b[k]-b[k+1]);
         }
         
         out.println(answer);
         
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}