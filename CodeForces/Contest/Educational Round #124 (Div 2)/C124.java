//make sure to make new file!
import java.io.*;
import java.util.*;

public class C124{
   
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
         
         for(int k = 0; k < n; k++){
            a[k] = Long.parseLong(st1.nextToken());
            b[k] = Long.parseLong(st2.nextToken());
         }
         
         long mina0 = Long.MAX_VALUE;
         long minan = Long.MAX_VALUE;
         long minb0 = Long.MAX_VALUE;
         long minbn = Long.MAX_VALUE;
      
         for(int k = 0; k < n; k++){
            mina0 = Math.min(mina0,Math.abs(a[0]-b[k]));
            minan = Math.min(minan,Math.abs(a[n-1]-b[k]));
            minb0 = Math.min(minb0,Math.abs(b[0]-a[k]));
            minbn = Math.min(minbn,Math.abs(b[n-1]-a[k]));
         }
         
         long answer = Long.MAX_VALUE;
         answer = Math.min(answer, Math.abs(a[0]-b[0]) + Math.abs(a[n-1]-b[n-1]));
         answer = Math.min(answer, Math.abs(a[0]-b[n-1]) + Math.abs(a[n-1]-b[0]));
         answer = Math.min(answer, Math.abs(a[0]-b[0]) + minan + minbn);
         answer = Math.min(answer, Math.abs(a[0]-b[n-1]) + minan + minb0);
         answer = Math.min(answer, Math.abs(a[n-1]-b[0]) + mina0 + minbn);
         answer = Math.min(answer, Math.abs(a[n-1]-b[n-1]) + mina0 + minb0);
         answer = Math.min(answer, mina0 + minan + minb0 + minbn);
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
      
}