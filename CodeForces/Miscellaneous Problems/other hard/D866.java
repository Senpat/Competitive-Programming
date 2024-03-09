//Buy Low Sell High
import java.io.*;
import java.util.*;
//tutorial
public class D866{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      PriorityQueue<Long> options = new PriorityQueue<Long>();
      options.add(array[0]);
      
      long answer = 0L;
      for(int k = 1; k < n; k++){
         if(options.peek() < array[k]){
            //exercise (buy) that option and sell at array[k]
            answer += array[k]-options.poll();
            //now you have the option to buy that stock at the new price instead
            options.add(array[k]);
         }
         options.add(array[k]);
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}