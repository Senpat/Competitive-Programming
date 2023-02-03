/*
TASK: kimbits
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class kimbits{

   public static long[][] count;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("kimbits.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("kimbits.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      long i = Long.parseLong(st.nextToken());
      
      count = new long[n+1][m+1];
      
      for(int k = 0; k <= m; k++){
         count[0][k] = 1;
      }
      
      for(int k = 1; k <= n; k++){
         for(int j = 0; j <= m; j++){
            count[k][j] += count[k-1][j];
            if(j > 0) count[k][j] += count[k-1][j-1];
         }
      }
      
      out.println(dothing(n,m,i));
        
        
      out.close();
   }
   
   
   
   public static String dothing(int n, int m, long i){
      if(n == m){
         String s = "";
         long x = i-1;
         for(int k = n-1; k >= 0; k--){
            if(x >= (1L << k)){
               s += "1";
               x -= (1L << k);
            } else {
               s += "0";
            }
         }
      
         return s;
      }
      
      if(count[n-1][m] < i){
         //add 1
         return "1" + dothing(n-1,m-1,i-count[n-1][m]);
      } else {
         //add 0
         return "0" + dothing(n-1,m,i);
      }
      
      
   }
      
}