//make sure to make new file!
import java.io.*;
import java.util.*;

public class C529{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      //powers of 2 up to 32
      
      long[] pow2 = new long[50];
      pow2[0] = 1;
      for(int k = 1; k < pow2.length; k++){
         pow2[k] = pow2[k-1] * 2;
      }
      
      long[] freq = new long[50];
      
      freq[1] = n/2;
      freq[0] = n%2 == 0 ? 0 : 1;
      
      if(m > n){
         out.println("NO");
         out.close();
         System.exit(0);
      }
      
      long cur = freq[1] + freq[0];
      
      while(cur != m){
         if(cur < m){
            if(freq[1] == 0) break;
            cur++;
            freq[1]--;
            freq[0]+=2;
         } else {
            //cur > m
            //find first thing with freq >= 2;
            int index = 1;
            while(index < 49 && freq[index] < 2) index++;
            if(freq[index] < 2) break;
            
            cur--;
            freq[index + 1]++;
            freq[index]-=2;
         }
      }
      
      if(cur == m){
         out.println("YES");
         for(int k = 0; k < freq.length; k++){
            for(int j = 0; j < freq[k]; j++){
               out.print(pow2[k] + " ");
            }
         }
      } else {
         out.println("NO");
      }
  
            
            
            
         
      
      
      
      
      out.close();
   }
   
}