//make sure to make new file!
import java.io.*;
import java.util.*;

public class SNUG_FIT{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
      
      
         long[] a = new long[n];
         long[] b = new long[n];
         
         for(int k = 0; k < n; k++){
            a[k] = Long.parseLong(st1.nextToken());
            b[k] = Long.parseLong(st2.nextToken());
         }
         
         Arrays.sort(a);
         Arrays.sort(b);
         
         long answer = 0L;
         for(int k = 0; k < n; k++){
            answer += Math.min(a[k],b[k]);
         }
         
         out.println(answer);
      
      }
      
   
      
      
      
      
      
      out.close();
   }
   
      
}