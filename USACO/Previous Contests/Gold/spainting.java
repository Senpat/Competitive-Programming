/*
TASK: spainting
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class spainting{

   public static final long MOD = 1000000007L;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("spainting.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spainting.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      int i = Integer.parseInt(st.nextToken());
      
      long[] good = new long[n+1];
      long[] bad = new long[n+1];
      
      bad[0] = 1L;
      
      long mpow = 1L;
      
      for(int k = 1; k < i; k++){
         bad[k] = (bad[k-1]*m)%MOD;
         mpow *= m;
         mpow %= MOD;
      }
      
      mpow = (mpow*m)%MOD;
      good[i] = m;
      bad[i] = (mpow-m)%MOD;
      
      for(int k = i+1; k <= n; k++){
         good[k] = (good[k-1]*m + bad[k-i]*(m-1)+MOD)%MOD;
         
         mpow = (mpow*m+MOD)%MOD;
         bad[k] = (mpow - good[k]+MOD)%MOD;
      }
      
      System.out.println(good[n]);
      out.println(good[n]);
      out.close();
      
        
   }
}