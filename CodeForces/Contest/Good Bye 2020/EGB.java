//make sure to make new file!
import java.io.*;
import java.util.*;

public class EGB{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      
      long[] pow2 = new long[61];
      pow2[0] = 1L;
      for(int k = 1; k <= 60; k++){
         pow2[k] = (pow2[k-1] * 2L + MOD)%MOD;
      }
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long[] array = new long[n];
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         long[] bitcount = new long[61];
         long i = 1L;
         for(int k = 0; k <= 60; k++){
            for(int j = 0; j < n; j++){
               if((array[j] & i) == i) bitcount[k]++;
            }
            i <<= 1;
         }
         
         //bit and contributes
         long[] andcont = new long[61];
         for(int k = 0; k <= 60; k++){
            andcont[k] = (bitcount[k]*pow2[k] + MOD)%MOD;
         }
         
         long[] and = new long[n];
         long[] or = new long[n];
         
         //calculate and, bitcount[i]*pow2[i] for all pow2s in array[k]
         i = 1L;
         for(int j = 0; j <= 60; j++){
            for(int k = 0; k < n; k++){
               if((array[k] & i) == i) and[k] = (and[k] + andcont[j] + MOD)%MOD;
            }
            i <<= 1;
         }
         
         //calculate or, sum of bitcount of pows not in array[k] + n*array[k]
         long nl = (long)n;
         for(int k = 0; k < n; k++){
            i = 1L;
            for(int j = 0; j <= 60; j++){
               if((array[k] & i) == 0L && bitcount[j] >= 1) or[k] = (or[k] + andcont[j] + MOD)%MOD;
               i <<= 1;
            }
            long arraymod = (array[k] + MOD)%MOD;
            long prod = (nl*arraymod + MOD)%MOD;
            or[k] = (or[k] + prod + MOD)%MOD;
         }
         
         long answer = 0L;
         for(int k = 0; k < n; k++){
            long prod = (and[k] * or[k] + MOD)%MOD;
            answer = (answer + prod + MOD)%MOD;
         }
         
         out.println(answer);
         
      
      }
      
      
      
      
      out.close();
   }
   
      
}