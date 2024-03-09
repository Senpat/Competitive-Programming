//make sure to make new file!
import java.io.*;
import java.util.*;
import java.math.*;

public class P1728{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      BigDecimal answer = BigDecimal.ZERO;
      for(int k = 0; k < n; k++){
         for(int j = k+1; j < n; j++){
            int sum = 0;
            for(int x = 2; x <= array[k]; x++){
               sum += Math.min(x-1,array[j]);
            }
            answer = answer.add((new BigDecimal(sum)).divide(new BigDecimal(array[k]*array[j]),100,BigDecimal.ROUND_HALF_EVEN));
         }
      }
      
      out.println(answer.setScale(6,BigDecimal.ROUND_HALF_EVEN));
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}