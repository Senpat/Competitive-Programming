//3 Parts of an Array

import java.io.*;
import java.util.*;

public class C498{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      int n = Integer.parseInt(f.readLine());
      
      long[] array = new long[n];
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++) array[k] = Long.parseLong(st.nextToken());
      
      int front = 0;
      int end = 0;
      
      long max = 0L;
      
      long sumf = 0L;
      long sume = 0L;
      
      while(front+end < n && front != n-end+1){
         if(sumf<=sume){
            front++;
            sumf+=array[front-1];
         } else if(sume<=sumf){
            end++;
            sume+=array[n-end];
         }
         if(sume==sumf){
            max = Math.max(max,sume);
         }
      }
      
      System.out.println(max);
   }
} 
         