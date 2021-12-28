//make sure to make new file!
import java.io.*;
import java.util.*;

public class DG17{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long[] pow2 = new long[200005];
      pow2[0] = 1L;
      for(int k = 1; k < 200005; k++){
         pow2[k] = (2L*pow2[k-1] + MOD)%MOD;
      }
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      
      int[] freq2 = new int[31];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         
         if(array[k] % 2 == 0){
            int x = array[k];
            int i = 0;
            while(x % 2 == 0){
               i++;
               x >>= 1;
            }
            freq2[i]++;
         }
      }
      
      long sub = 1L;
      
      long subsub = 0L;
      long subsubcumu = 1L;
      for(int k = 30; k >= 0; k--){
         sub = (sub * pow2[freq2[k]] + MOD)%MOD;
         
         if(freq2[k] >= 2){
            long sub1 = (pow2[freq2[k]-1]-1 + MOD)%MOD;
            sub1 = (sub1 * subsubcumu + MOD)%MOD;
            subsub = (subsub + sub1 + MOD)%MOD;
            
         }
         subsubcumu = (subsubcumu * pow2[freq2[k]] + MOD)%MOD;
         
      }
      sub = (sub-subsub + MOD)%MOD;
      
      long answer = (pow2[n] - sub + MOD)%MOD;
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}