//make sure to make new file!
import java.io.*;
import java.util.*;
//WRONG:
/*
4
0 0 2 2
2 2 0 0
*/
public class P2180{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st1 = new StringTokenizer(f.readLine());
      StringTokenizer st2 = new StringTokenizer(f.readLine());
      
      long[][] array = new long[n][2];
      long[] a = new long[n];
      long sum0 = 0;
      long sum1 = 0;
      for(int k = 0; k < n; k++){
         array[k][0] = Long.parseLong(st1.nextToken());
         array[k][1] = Long.parseLong(st2.nextToken());
         sum0 += array[k][0];
         sum1 += array[k][1];
         a[k] = array[k][0] + array[k][1];
      }
      
      long answer = Math.abs(sum1-sum0)/2;
      
      long extra = 0L;
      for(int k = 0; k < n; k++){
         extra += a[k]-2L;
         answer += Math.abs(extra);
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}