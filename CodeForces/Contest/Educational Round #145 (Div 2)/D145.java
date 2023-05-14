//make sure to make new file!
import java.io.*;
import java.util.*;

public class D145{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long BIG = 1000000000000L;
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         String s = f.readLine();
         int n = s.length();
         
         int[] array = new int[n];
         long[] freq = new long[2];
         for(int k = 0; k < n; k++){
            array[k] = Character.getNumericValue(s.charAt(k));
            freq[array[k]]++;
         }
         
         long answer = Long.MAX_VALUE;
         //all 0 or all 1
         answer = (BIG+1L)*Math.min(freq[0],freq[1]);
         
         long[] prefreq = new long[2];
         for(int k = 0; k < n-1; k++){
            prefreq[array[k]]++;
            
            long numremovebefore = prefreq[1];
            long numremoveafter = freq[0]-prefreq[0];
            long swap = 0;
            if(array[k] == 1 && array[k+1] == 0){
               numremovebefore--;
               numremoveafter--;
               swap++;
            }
            
            answer = Math.min(answer,(numremovebefore+numremoveafter)*(BIG+1) + BIG*swap);
         }
         
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}