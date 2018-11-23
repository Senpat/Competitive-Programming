//make sure to make new file!
import java.io.*;
import java.util.*;

public class B523{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      if(n == 1){
         out.println(0);
         out.close();
         System.exit(0);
      }
      
      st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      int sum = 0;
      int max = 0;
      int index = 0;
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         if(max < array[k]){
            max = array[k];
            index = k;
         }
         sum += array[k];
      }
      
      //find 2nd highest
      
      int max2 = 0;
      for(int k = 0; k < n; k++){
         max2 = Math.max(max2,array[k]);
      }
      
      int answer = sum - (n-1 + Math.max(1,max-max2));
      
      
      out.println(answer);
      out.close();
   }
   
}