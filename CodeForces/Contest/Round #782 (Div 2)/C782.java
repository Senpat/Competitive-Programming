//make sure to make new file!
import java.io.*;
import java.util.*;

public class C782{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         long a = Long.parseLong(st.nextToken());
      
         long[] array = new long[n+1];
         array[0] = 0;
         st = new StringTokenizer(f.readLine());
         for(int k = 1; k <= n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         long[] sufsum = new long[n+1];
         sufsum[n] = 0;
         for(int k = n-1; k >= 0; k--){
            sufsum[k] = sufsum[k+1] + array[k+1];
         }
         
         //cost to get the capital to point array[k]
         long curcost = 0;
         long answer = Long.MAX_VALUE;
         for(int k = 0; k < n; k++){
            answer = Math.min(answer,a*(sufsum[k]-array[k]*(long)(n-k))+curcost);
            
            if(k < n-1) curcost += (a+b)*(array[k+1]-array[k]);
         }
         if(n > 1) answer = Math.min(answer,curcost+a*(array[n]-array[n-1]));
         out.println(answer);
            
      }  
      
      
      
      
      out.close();
   }
   
      
}