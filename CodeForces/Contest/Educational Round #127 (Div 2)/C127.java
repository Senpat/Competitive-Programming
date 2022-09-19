//make sure to make new file!
import java.io.*;
import java.util.*;

public class C127{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long x = Long.parseLong(st.nextToken());
         
         long sum = 0L;
         Long[] array = new Long[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            sum += array[k];
         }
         
         Arrays.sort(array);
         
         int i = n-1;
         
         long day = 0;
         long answer = 0L;
         while(i >= 0){
            long shops = (long)(i+1);
            long cursum = sum+day*shops;
            if(cursum <= x){
               long caneat = (x-cursum)/shops + 1;
               answer += caneat*shops;
               day += caneat;
            }
            sum -= array[i];
            i--;
            
         }
         
         out.println(answer);
         
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}