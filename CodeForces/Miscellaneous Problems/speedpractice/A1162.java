//make sure to make new file!
import java.io.*;
import java.util.*;

public class A1162{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n+1];
      
      Arrays.fill(array,h*h);
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
         
         for(int j = a; j <= b; j++){
            array[j] = Math.min(array[j],c*c);
         }
      }
      
      int answer = 0;
      for(int k = 1; k <= n; k++){
         answer += array[k];
      }
      
      out.println(answer);
      
      
      
      out.close();
   }
   
      
}