//make sure to make new file!
import java.io.*;
import java.util.*;

public class B642{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] a = new int[n];
         int[] b = new int[n];
         
         int sum = 0;
         
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            a[k] = Integer.parseInt(st1.nextToken());
            b[k] = Integer.parseInt(st2.nextToken());
            sum += a[k];
            
         }
         
         Arrays.sort(a);
         Arrays.sort(b);
         
         for(int k = 0; k < m; k++){
            if(a[k] >= b[n-k-1]) break;
            sum += b[n-k-1]-a[k];
         }
         
         out.println(sum);
      

      }
      
      
      
      
      out.close();
   }
   
      
}