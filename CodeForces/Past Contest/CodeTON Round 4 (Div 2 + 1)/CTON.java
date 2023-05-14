//make sure to make new file!
import java.io.*;
import java.util.*;

public class CTON{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long c = Long.parseLong(st.nextToken());
         long d = Long.parseLong(st.nextToken());
         
         int N = 2*n;
         int[] freq = new int[N+1];
         int[] array = new int[n];
         st = new StringTokenizer(f.readLine());
         long rem = 0L;
         long total = 0L;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(array[k] <= N){
               freq[array[k]]++;
               total++;
            } else {
               rem++;
            }
         }
         
         long numadd = 0L;
         long numrem = 0L;
         
         long answer = Long.MAX_VALUE;
         for(int k = 1; k <= N; k++){
            if(freq[k] == 0) numadd++;
            if(freq[k] > 1) numrem += (long)(freq[k]-1);
            
            total -= freq[k];
            
            answer = Math.min(answer, d*numadd + c*(rem + numrem + total));
         }
         
         out.println(answer);
         

      }
      
      
      
      
      out.close();
   }
   
      
}