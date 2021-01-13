//make sure to make new file!
import java.io.*;
import java.util.*;

public class FRR1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      char[] s = f.readLine().toCharArray();
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Character.getNumericValue(s[k]);
      }
      
      long baseline = 0L;
      long maxstreak = 0L;
      long curstreak = 0L;
      long curadd = 0L;
      
      for(int k = 0; k < n; k++){
         if(array[k] == 1){
            curstreak++;
            if(curstreak > maxstreak){
               curadd += curstreak;
               maxstreak = curstreak;
            }
         
         } else {
            curstreak = 0L;
         
         }  
         
         baseline += curadd;
      }
      
      long answer = baseline;
      
      long[] last = new long[n+1];
      Arrays.fill(last,-1);
      
      //find first chunk
      int start = -1;
      int end = -1;
      for(int k = 0; k < n; k++){
         if(array[k] == 1){
            if(start == -1) start = k;
         } else {
            if(start != -1){
               end = k-1;
               break;
            }
         }
      }
      
      if(start != -1 && end == -1) end = n-1;
      
      for(int k = 1; k <= end-start+1; k++){
         answer += (long)k * (start-1 +1);
      }
      
      for(int k = 1; k <= end-start+1; k++){
         last[k] = end-k+1;
      }
      
      int l = -1;
      int r = -1;
      for(int k = end+1; k < n; k++){
         if(array[k] == 1){
            if(l == -1) l = k;
         } else {
            if(r != -1){
               r = k-1;
               
               //update answer and update last
               for(int j = 1; j <= r-l+1; j++){
                  if(last[j] == -1) answer += (long)j * (l+1 -1);
                  else answer += (long)j * (l+j-1 - last[j]+1 -1);
               }
               
               //update last
               for(int j = 1; j <= end-start+1; j++){
                  last[j] = end-j+1;
               }
               
               l = -1;
               r = -1;
            }
         }
      }
      
      if(array[n-1] == 1){
         r = n-1;
         for(int j = 1; j <= r-l+1; j++){
            if(last[j] == -1) 
               continue;
            answer += (long)j * (l+j-1 - last[j]+1 -1);
         }
      }
            
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}