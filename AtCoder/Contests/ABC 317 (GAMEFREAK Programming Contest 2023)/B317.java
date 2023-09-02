//make sure to make new file!
import java.io.*;
import java.util.*;

public class B317{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      int min = 100000;
      int max = -1;
      int sum = 0;
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         min = Math.min(min,array[k]);
         max = Math.max(max,array[k]);
         sum += array[k];
      }
      
      int t = (max+min)*(max-min+1)/2;
      out.println(t-sum);
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}