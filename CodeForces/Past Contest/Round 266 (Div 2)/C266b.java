//make sure to make new file!
import java.io.*;
import java.util.*;

public class C266b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      long sum = 0L;
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         sum += array[k];
      }      
      if(sum % 3L != 0L){
         out.println(0);
         out.close();
         return;
      }
      long thresh = sum/3L;
      
      long answer = 0L;
      
      long cursum = 0L;

      long first = 0L;
      
      for(int k = 0; k < n; k++){
         cursum += array[k];
         
         if(cursum == 2*thresh && !(thresh == 0L && k == n-1)){
            answer += first;
         }
         
         if(cursum == thresh){
            first++;
         }
      }
      out.println(answer);
      
      
      
      
      out.close();
   }
   
      
}