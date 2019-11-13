//make sure to make new file!
import java.io.*;
import java.util.*;

public class P3{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      long[] arr = new long[n];
      
      for(int i = 0; i < n; i++){
         arr[i] = Long.parseLong(st.nextToken());
      }
      
      Arrays.sort(arr);
      
      long[] prefs = new long[n+1];
      
      prefs[0] = 0;
      
      for(int i = 1; i <= n; i++) prefs[i] = prefs[i-1]+arr[i-1];
      
      long minSum = Long.MAX_VALUE;
      
      for(int i = 0; i < n-1; i++){
         long sum = -1*a*(prefs[i]-i*arr[i]) + b*((prefs[n]-prefs[i+1])-((n-i-1)*arr[i]));
         minSum = Math.min(sum, minSum);
      }
      
      out.println(minSum);
      
      
      
      
      

      
      
      
      
      out.close();
   }
   
      
}