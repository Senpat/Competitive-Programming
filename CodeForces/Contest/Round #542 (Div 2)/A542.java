//make sure to make new file!
import java.io.*;
import java.util.*;

public class A542{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int pcount = 0;
      int ncount = 0;
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         if(array[k] > 0) pcount++;
         if(array[k] < 0) ncount++;
      }
      
      int thresh = 0;
      if(n % 2 == 0){
         thresh = n/2;
      } else {
         thresh = (n+1)/2;
      }
      
      if(pcount >= thresh){
         out.println(1);
      } else if(ncount >= thresh){
         out.println(-1);
      } else {
         out.println(0);
      }
      
      
      
      
      
      out.close();
   }
   
      
}