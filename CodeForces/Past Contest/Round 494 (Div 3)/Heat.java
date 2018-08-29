//Round #494 (Div 3) C
//Intense Heat

import java.io.*;
import java.util.*;
import java.math.*;

public class Heat{
   
   public static void main(String[] args)throws IOException{
      Scanner sc = new Scanner(System.in);
      
      StringTokenizer st = new StringTokenizer(sc.nextLine());
      
      int n = Integer.parseInt(st.nextToken());
      double m = Double.parseDouble(st.nextToken());
      
      st = new StringTokenizer(sc.nextLine());
      
      int[] array = new int[n];
      
      int max1 = 0;
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         max1 = Math.max(array[k],max1);
      }
      
      if(m==1){
         System.out.println(max1);
         System.exit(0);
      }
      
      int[] prefix = new int[n+1];
      
      prefix[0] = 0;
      for(int k = 0; k < n; k++){
         prefix[k+1]=prefix[k] + array[k];
      }
      
      double max = 0.0;
      
      for(double k = m; k <= n; k++){      //k is how far to go
         for(int j = 0; j <= n-k; j++){ //j is starting point
            double sum = prefix[(int)k+j]-prefix[j];
            max = Math.max(max, sum/k);
         }
      }
      
      System.out.println(max);
   }
}