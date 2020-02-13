//make sure to make new file!
import java.io.*;
import java.util.*;

public class B79{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long m = Long.parseLong(st.nextToken());
      
         long[] array = new long[n];
         
         st = new StringTokenizer(f.readLine());  
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         int start = 0;
         int end = start;
         long sum = 0L;
      
         int max = 0;         
         int answer = -1;
         while(end < n){
            if(sum > m){
               if(end-start > max){
                  max = end-start;
                  answer = start;
               }
               
               sum -= array[start];
               start++;
            } else {
               sum += array[end];
               end++;
            }
         }
         
         if(end-start > max){
            max = end-start;
            answer = start;
         }
            
         
         
         
         out.println(answer);
         
         
      }
      
      
      
      
      out.close();
   }
   
      
}