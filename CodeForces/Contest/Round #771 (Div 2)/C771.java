//make sure to make new file!
import java.io.*;
import java.util.*;

public class C771{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n+1];
         for(int k = 1; k <= n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int max = 0;
         int answer = 0;
         for(int k = 1; k <= n; k++){
            max = Math.max(max,array[k]);
            if(max == k) answer++;
         }
         
         out.println(answer); 
      

      }
      
      
      
      
      out.close();
   }
   
      
}