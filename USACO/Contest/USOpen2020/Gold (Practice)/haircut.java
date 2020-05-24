/*
TASK: haircut
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class haircut{
   
   public static int n;
   public static long[] bits;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("haircut.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haircut.out")));
      
      
      n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      bits = new long[n+2];
      
      long[] answer = new long[n+1];
      
      update(array[0]+1,1L);
      
      for(int k = 1; k < n; k++){
         answer[array[k]] += psum(n+1)-psum(array[k]+1);
         update(array[k]+1,1L);
      }
      
      long[] psum = new long[n];
      psum[0] = 0L;
      for(int k = 1; k < n; k++){
         psum[k] = psum[k-1]+answer[k-1];
      }
      
      for(int k = 0; k < n; k++){
         System.out.println(psum[k]);
         out.println(psum[k]);
      }
      
      
      
      
        
      out.close();
   }
   
   
   public static void update(int i, long x){
      for(; i <= n+1; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static long psum(int i){
      long sum = 0L;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   
   }

      
}