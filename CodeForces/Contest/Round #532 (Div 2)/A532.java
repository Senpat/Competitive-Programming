//make sure to make new file!
import java.io.*;
import java.util.*;

public class A532{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int max = 0;
      for(int k = 0; k < m; k++){
         int a = 0;
         int b = 0;
         for(int j = 0; j < n; j++){
            if(j == k || (j-k)%m == 0){
               continue;
            }
            if(array[j] == 1) a++;
            if(array[j] == -1) b++;
         }
         
         max = Math.max(max,Math.abs(a-b));
      }
      
      out.println(max);
      
      
      out.close();
   }
   
}