//make sure to make new file!
import java.io.*;
import java.util.*;

public class A533{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] array = new int[n];
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int min = Integer.MAX_VALUE;
      int minind = 0;
      
      for(int k = 1; k <= 100; k++){
         int count = 0;
         for(int j = 0; j < n; j++){
            if(array[j] < k-1){
               count += Math.abs(array[j]-(k-1));
            }
            if(array[j] > k+1){
               count += Math.abs(array[j]-(k+1));
            }
         }
         if(min > count){
            minind = k;
            min = count;
         }
      }
      
      out.println(minind + " " + min);
         
      
      
      out.close();
   }
   
}