//make sure to make new file!
import java.io.*;
import java.util.*;

public class V{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      Long[] array = new Long[n];
      st = new StringTokenizer(f.readLine());
      long sum = 0L;
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         sum += array[k];
      }
      
      Arrays.sort(array);
      
      int i = 0;
      
      long bar = 0L;
      long count = (long)n;
      for(int k = 0; k < q; k++){
         int t = Integer.parseInt(f.readLine());
         
         if(t == 0){
            out.println(sum);
            continue;
         }
         bar += t;
         while(i < n && array[i] <= bar){
            count--;
            sum -= array[i];
            i++;
         }
         
         long answer = sum-count*bar;
         out.println(answer);
         
      }
      
      
         
            
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}