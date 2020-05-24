//make sure to make new file!
import java.io.*;
import java.util.*;

public class D627{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st1 = new StringTokenizer(f.readLine());
      StringTokenizer st2 = new StringTokenizer(f.readLine());
      
      int[] a = new int[n];
      int[] b = new int[n];
      Integer[] difs = new Integer[n];
      
      for(int k = 0; k < n; k++){
         a[k] = Integer.parseInt(st1.nextToken());
         b[k] = Integer.parseInt(st2.nextToken());
         difs[k] = a[k]-b[k];
      }
      
      Arrays.sort(difs);
      
      int start = 0;
      int end = n-1;
      long answer = 0L;
      
      while(difs[end] > 0 && end > start){
         
         while(difs[start] <= difs[end]*-1){
            start++;
         }
         
         answer += (long)(end-start);
         end--;
      }
      
      out.println(answer);
      
      
      
      

      
      
      
      
      
      out.close();
   }
   
      
}