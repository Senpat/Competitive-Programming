//make sure to make new file!
import java.io.*;
import java.util.*;

public class B672{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         long[] log2 = new long[50];
         for(int k = 0; k < n; k++){
            int i = array[k];
            int num = 0;
            while(i > 0){
               i >>= 1;
               num++;
            }
            log2[num]++;
         }
         
         long answer = 0L;
         for(int k = 0; k < 50; k++){
            answer += log2[k]*(log2[k]-1)/2;
         }
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}