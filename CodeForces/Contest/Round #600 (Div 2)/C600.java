//make sure to make new file!
import java.io.*;
import java.util.*;

public class C600{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      Long[] array = new Long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      Arrays.sort(array);
      
      long[] msum = new long[m];
      
      long sum = 0;
      
      ArrayList<Long> answer = new ArrayList<Long>(n);
      
      for(int k = 0; k < n; k++){
         sum = sum + array[k] + msum[k%m];
         msum[k%m] += array[k];
         answer.add(sum);
      }
      
      
      for(long i : answer){
         out.print(i + " ");
      }
      
      
      
      

      
      
      
      
      
      out.close();
   }
   
      
}