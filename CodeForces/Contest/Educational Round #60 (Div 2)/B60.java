//make sure to make new file!
import java.io.*;
import java.util.*;

public class B60{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int i = Integer.parseInt(st.nextToken());
      
      long[] a = new long[n];
      
      st = new StringTokenizer(f.readLine());
      
      long max1 = 0L;
      long max2 = 0L;
      
      for(int k = 0; k < n; k++){
         a[k] = Long.parseLong(st.nextToken());
         if(a[k] > max1){
            max2 = max1;
            max1 = a[k];
         } else if(a[k] > max2){
            max2 = a[k];
         }
      }
      
      long per = max1*i + max2;
      
      long answer = (m/(i+1)) * per + (m%(i+1)) * max1;
      out.println(answer);

      
      
      
      
      out.close();
   }
   
      
}