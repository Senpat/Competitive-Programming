//Golf Gophers
import java.io.*;
import java.util.*;

public class Solution{
   
   public static int MAXN = 1000005;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int q = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] iton = {17,16,15,13,11,7};
      String[] queries = {"17 17 17 17 17 17 17 17 17 17 17 17 17 17 17 17 17 17","16 16 16 16 16 16 16 16 16 16 16 16 16 16 16 16 16 16","15 15 15 15 15 15 15 15 15 15 15 15 15 15 15 15 15 15","13 13 13 13 13 13 13 13 13 13 13 13 13 13 13 13 13 13","11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11 11","7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7 7"};
      
      int prod = 4084080;
      int[] pp = new int[6];
      int[] inv = new int[6];
      for(int k = 0; k < 6; k++){
         pp[k] = prod/iton[k];
         inv[k] = modInverse(pp[k],iton[k]);
      }
      
      for(int t = 1; t <= q; t++){
         int[][] inputs = new int[6][18];
         for(int k = 0; k < 6; k++){
            out.println(queries[k]);
            out.flush();
            String s = f.readLine();
            if(s.equals("-1")) System.exit(0);
            st = new StringTokenizer(s);
            for(int j = 0; j < 18; j++){
               inputs[k][j] = Integer.parseInt(st.nextToken());
            }
         }
         
         //crt for all 18
         int ans = 0;
         for(int k = 0; k < 18; k++){
            int cur = 0;
            for(int j = 0; j < 6; j++){
               cur += inputs[j][k] * pp[j] * inv[j];
            }
            ans += cur % prod;
            
         }
         out.println(ans); 
         out.flush();
         
      }
      
      
      out.close();
   }
   
   public static int modInverse(int a, int m) 
   { 
      int m0 = m; 
      int y = 0, x = 1; 
     
      if (m == 1) 
         return 0; 
     
      while (a > 1) 
      { 
           // q is quotient 
         int q = a / m; 
         int t = m; 
      
           // m is remainder now, process same as 
           // Euclid's algo 
         m = a % m;
         a = t; 
         t = y; 
      
           // Update y and x 
         y = x - q * y; 
         x = t; 
      } 
     
       // Make x positive 
      if (x < 0) 
         x += m0; 
     
      return x; 
   } 

      
}