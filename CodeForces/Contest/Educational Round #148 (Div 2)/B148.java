//make sure to make new file!
import java.io.*;
import java.util.*;

public class B148{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         st = new StringTokenizer(f.readLine());
         
         long sum = 0L;
         Long[] array = new Long[n];
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            sum += array[k];
         }
         
         Arrays.sort(array);
         
         long cursub = 0L;
         for(int k = 0; k < m; k++){
            cursub += array[n-k-1];
         }
         
         long minsub = cursub;
         
         for(int k = 0; k < m; k++){
            cursub -= array[n-m+k];
            cursub += array[2*k] + array[2*k+1];
            
            minsub = Math.min(minsub,cursub);
         }
         
         out.println(sum-minsub);
         
         
      }
      
      
      
      
      out.close();
   }
   
      
}