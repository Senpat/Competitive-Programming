//Yaroslav and Sequence
import java.io.*;
import java.util.*;

public class A301{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[2*n-1];
      int sum = 0;
      int numneg = 0;
      for(int k = 0; k < 2*n-1; k++){
         array[k] = Integer.parseInt(st.nextToken());
         sum += Math.abs(array[k]);
         if(array[k] < 0) numneg++;
      }
      
      if(numneg%2 == 0 || n%2 == 1) out.println(sum);
      else{
      
         int min = 2000;
         for(int k = 0; k < 2*n-1; k++){
            if(Math.abs(array[k]) < min){
               min = Math.abs(array[k]);
            }
         }
         sum -= 2*min;
         out.println(sum);
      
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}