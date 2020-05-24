//make sure to make new file!
import java.io.*;
import java.util.*;

public class CARSELL{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         Long[] array = new Long[n];
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         Arrays.sort(array);
         
         //int index = 0;
         long answer = 0L;
         long year = 0;
         
         for(int k = n-1; k >= 0; k--){
            if(array[k] <= year) continue;
            answer = (answer + array[k]-year+ MOD)%MOD;
            year++;
         }
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
}