//Party
import java.io.*;
import java.util.*;

public class A115{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] array = new int[n+1];
      
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(f.readLine());
      }
      
      int max = 0;
      
      for(int k = 1; k <= n; k++){
         int count = 1;
         int i = k;
         while(array[i]!=-1){
            i = array[i];
            count++;
         }
         max = Math.max(max,count);
      }
      
      out.println(max);
      
      out.close();
   }
   
}