//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1085{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      long[] array = new long[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      
      long l = 1L;
      long r = 200000000000000L;
      long ans = -1L;
      
      while(l <= r){
         long mid = l + (r-l)/2;
         
         if(check(array,mid) <= m){
            ans = mid;
            r = mid-1;
         } else {
            l = mid+1;
         }
      }
      
      out.println(ans);
      
      
      
      
      
      
      out.close();
   }
   
   //returns # of segments needed for all segments to be below the threshold
   public static int check(long[] array, long thresh){
      int seg = 1;
      
      long sum = 0L;
      for(int k = 0; k < array.length; k++){
         if(array[k] > thresh) return Integer.MAX_VALUE;
         
         if(sum + array[k] > thresh){
            seg++;
            sum = array[k];
         } else {
            sum += array[k];
         }
      }
      
      return seg;
      
   }
      
}