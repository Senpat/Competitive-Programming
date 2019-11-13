//make sure to make new file!
import java.io.*;
import java.util.*;

public class C135{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] a = new long[n+1];
      for(int k = 0; k < n+1; k++){
         a[k] = Long.parseLong(st.nextToken());
      }
      
      st = new StringTokenizer(f.readLine());
      long[] b = new long[n];
      for(int k = 0; k < n; k++){
         b[k] = Long.parseLong(st.nextToken());
      }
      
      long answer = 0L;
      
      for(int k = 0; k < n; k++){
         answer += Math.min(a[k],b[k]);
         b[k] -= Math.min(a[k],b[k]);
         answer += Math.min(a[k+1],b[k]);
         a[k+1] -= Math.min(a[k+1],b[k]);
      }
      
      out.println(answer);
      
      
      
      out.close();
   }
   
      
}