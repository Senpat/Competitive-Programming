//make sure to make new file!
import java.io.*;
import java.util.*;

public class D338{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int[] array = new int[m];
      for(int k = 0; k < m; k++){
         array[k] = Integer.parseInt(st.nextToken())-1;
      }
      
      long answer = 0L;
      long[] add = new long[n];
      
      for(int k = 0; k < m-1; k++){
         int min = Math.min(array[k],array[k+1]);
         int max = Math.max(array[k],array[k+1]);
      
         long a = (long)(max - min);
         long b = (long)n - a;
         
         if(a <= b){
            add[min] += b-a;
            add[max] -= b-a;
         } else {
            add[0] += a-b;
            add[min] -= a-b;
            
            add[max] += a-b;
         }
         
         answer += Math.min(a,b);
      }
      
      for(int k = 1; k < n; k++){
         add[k] += add[k-1];
      }
      
      long min = Long.MAX_VALUE;
      for(int k = 0; k < n; k++){
         min = Math.min(min,add[k]);
      }
      
      answer += min;
      
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
      
}