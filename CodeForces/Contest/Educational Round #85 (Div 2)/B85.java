//make sure to make new file!
import java.io.*;
import java.util.*;

public class B85{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long m = Long.parseLong(st.nextToken());
         
         Long[] array = new Long[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         Arrays.sort(array);
         
         int i = 0;
         
         long sum = 0L;
         
         while(i < n){
            if((sum + array[n-i-1])/(i+1) >= m){
               sum += array[n-i-1];
               i++;
            } else {
               break;
            }
         
         }
         
         out.println(i); 

      }
      
      
      
      
      out.close();
   }
   
      
}