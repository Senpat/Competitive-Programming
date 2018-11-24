//make sure to make new file!
import java.io.*;
import java.util.*;

public class A519{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      int sum = 0;
      int max = 0;
      
      for(int k = 0; k< n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         sum+=array[k];
         max = Math.max(max,array[k]);
      }
      
      sum*=2;
      
      int answer = sum/n+1;
      
      answer = Math.max(answer,max);
      
      out.println(answer);
   
      
      
      
      
      
      out.close();
   }
   
}