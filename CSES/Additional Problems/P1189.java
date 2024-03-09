//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1189{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st1 = new StringTokenizer(f.readLine());
      StringTokenizer st2 = new StringTokenizer(f.readLine());
      
      long[] vals = new long[n];
      for(int k = 0; k < n; k++){
         vals[k] = Long.parseLong(st2.nextToken()) - Long.parseLong(st1.nextToken());
      }
      
      long NINF = -1000000000000L;
      
      long[] array = new long[n];
      array[0] = NINF;
      long sum = NINF;
      long answer = 0L;
      for(int k = 1; k < n; k++){
         array[k] = array[k-1] - vals[k];
         sum += array[k];
         
         answer += Math.abs(array[k]);
      }
      
      Arrays.sort(array);
      //reverse array
      for(int k = 0; n-k-1 > k; k++){
         long temp = array[k];
         array[k] = array[n-k-1];
         array[n-k-1] = temp;
      }
      
      long possum = 0L;
      long negsum = sum;
      for(int k = 0; k < n; k++){
         //make this value 0
         
         possum += array[k];
         negsum -= array[k];
         
         long posadd = (long)(k+1) * -1L * array[k];
         long negsub = (long)(n-k-1) * -1L * array[k];
         
         long cur = possum + posadd + -1L*negsum - negsub;
         answer = Math.min(answer,cur);
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}