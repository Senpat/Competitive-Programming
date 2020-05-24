//make sure to make new file!
import java.io.*;
import java.util.*;

public class E166{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      long[] zfreq = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         if(k-array[k] > 0){
            zfreq[k-array[k]]++;
         }
      }
      
      long answer = 0L;
      
      for(int k = 0; k < n; k++){
         int z = k+array[k];
         if(z < n){
            answer += zfreq[z];
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}