//Sonya and Informatics
import java.io.*;
import java.util.*;

public class F1151{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      long nl = (long)n;
      
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      long num0 = 0;
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         if(array[k] == 0) num0++;
      }
      
      long[][] tran = new long[(int)num0+1][(int)num0+1];       //tran[x][y] is the probability that you go to y sorted 0s from x sorted 0s
      
      long num1 = nl-num0;
      
      long denom = modInverse(nl*(nl-1)/2L, MOD);
      //nxy is number of x in y's area
      for(long n00 = 0; n00 <= num0; n00++){
         long n10 = num0-n00;
         
         long n01 = n10;
         long n11 = num1 - n10;
         
         long down = n11 * n00;
         long same = n00 * n01 + n10 * n11 + num0*(num0-1)/2L + num1*(num1-1)/2L;
         long up = n10 * n01;
         
         int n00i = (int)n00;
         if(down > 0L){
            tran[n00i][n00i-1] = (down * denom + MOD)%MOD;
         }
         tran[n00i][n00i] = (same * denom + MOD)%MOD;
         if(up > 0L){
            tran[n00i][n00i+1] = (up * denom + MOD)%MOD;
         }
         
         //out.println((((down+same+up)*denom)%MOD));
         /*
         for(int k = 0; k <= num0; k++){
            out.print(tran[n00i][k] + " ");
         }
         out.println();
         */
      }
      
      long[][] ret = exp(tran,m);
      
      int orig0 = 0;
      for(int k = 0; k < num0; k++){
         if(array[k] == 0) orig0++;
      }
      
      out.println(ret[orig0][(int)num0]);
      
      
      
      out.close();
   }
   
   public static long[][] matmul(long[][] a, long[][] b){
      int len = a.length;
      long[][] ret = new long[len][len];
   
      for(int k = 0; k < len; k++){
         for(int j = 0; j < len; j++){
            for(int h = 0; h < len; h++){
            	//ret[k][j] = (ret[k][j] + a[h][k]*b[j][h] + MOD)%MOD;            //from kotlin template, wrong
               ret[k][j] = (ret[k][j] + a[k][h]*b[h][j] + MOD)%MOD;
            }
         }
      }
      return ret;
   }

   public static long[][] exp(long[][] base, long power){
      if(power == 0){ 
         //IDENTITY MATRIX OF THE RIGHT SIZE
         long[][] ret = new long[base.length][base[0].length];
         for(int k = 0; k < base.length; k++){
            ret[k][k] = 1L;
         }
         return ret;
      }
      if(power == 1) 
         return base;
      long[][] ans = exp(base,power/2);
      ans = matmul(ans,ans);
      if(power%2 == 1) ans = matmul(ans, base);
      return ans;
   }
   
   //from geeksforgeeks
   public static long modInverse(long a, long m) 
   { 
      long m0 = m; 
      long y = 0;
      long x = 1; 
     
      if (m == 1) 
         return 0; 
     
      while (a > 1) 
      { 
           // q is quotient 
         long q = a / m; 
         long t = m; 
      
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