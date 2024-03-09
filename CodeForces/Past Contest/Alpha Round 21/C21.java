//make sure to make new file!
import java.io.*;
import java.util.*;

public class C21{
   
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
      
      if(sum % 3 != 0){
         out.println(0);
         out.close();
         return;
      }
      
      long thresh = sum/3L;
      
      long answer = 0L;
      
      long first = 0L;        //# of times that you can make the first segment
      
      long cursum = 0L;
      
      for(int k = 0; k < n; k++){
         cursum += array[k];
         
         if(cursum == 2*thresh && k != n-1){
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