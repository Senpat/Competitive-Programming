//make sure to make new file!
import java.io.*;
import java.util.*;

public class B714{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      long[] fac = new long[200005];
      fac[0] = 1L;
      for(int k = 1; k < 200005; k++){
         fac[k] = ((long)k * fac[k-1] + MOD)%MOD;
      }
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int and = array[0];
         for(int k = 1; k < n; k++){
            and = and & array[k];
         }
         
         long numand = 0L;               //number of times and appears in the array
         for(int k = 0; k < n; k++){
            if(array[k] == and) numand++;
         }
         
         if(numand < 2){
            out.println(0);
            continue;
         } else {
            long answer = (numand * (numand-1) + MOD)%MOD;
            answer = (answer * fac[n-2] + MOD)%MOD;
            out.println(answer);
         }
         
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}