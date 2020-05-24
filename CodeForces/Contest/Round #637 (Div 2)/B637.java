//make sure to make new file!
import java.io.*;
import java.util.*;

public class B637{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         st = new StringTokenizer(f.readLine());
         
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int[] peak = new int[n];
         for(int k = 1; k < n-1; k++){
            if(array[k] > array[k-1] && array[k] > array[k+1]){
               peak[k] = 1;
            }
         }
         
         int[] psums = new int[n+1];
         psums[0] = 0;
         for(int k = 1; k <= n; k++){
            psums[k] = psums[k-1] + peak[k-1];
         }
         
         int max = -1;
         int maxindex = 0;
         
         for(int k = 0; k <= n-m; k++){
            if(psums[k+m-1]-psums[k+1] > max){
               max = psums[k+m-1] - psums[k+1];
               maxindex = k;
            }
         }
         
         out.println((max+1) + " " + (maxindex+1));

      }
      
      
      
      
      out.close();
   }
   
      
}