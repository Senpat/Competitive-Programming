//make sure to make new file!
import java.io.*;
import java.util.*;

public class E95{

   public static long MOD = 998244353L;
   public static long i2 = 499122177L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      long nl = (long)n;
      
      Long[] array = new Long[n];
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      Arrays.sort(array);
      
      long[] psums = new long[n+1];
      psums[0] = 0L;
      for(int k = 1; k <= n; k++){
         psums[k] = (psums[k-1] + array[k-1] + MOD)%MOD;
      }
      
      
      long[] fac = new long[n+1];
      fac[0] = 1L;
      for(int k = 1; k <= n; k++){
         fac[k] = (fac[k-1] * (long)k + MOD)%MOD;
      }
      
      long nfac = fac[n];
      long nfaci = modInverse(nfac,MOD);
      
      long[] answer = new long[m];
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         
         //get g, number of numbers >= b
         int l = 0;
         int r = n-1;
         int ans = -1;           //holds index of last number < b
         while(l <= r){
            int mid = l + (r-l)/2;
            
            if(array[mid] < b){
               ans = mid;
               l = mid+1;
            } else {
               r = mid-1;
            }
         }
         
         long g = (long)(n-ans-1);
         //out.println(g);
         if(a > g){
            answer[k] = 0L;
            continue;
         }
         
         long sumless = psums[ans+1];
         long sumgreater = (psums[n]-sumless + MOD)%MOD;
         
         long add1 = ((g-a)*sumgreater + MOD)%MOD;
         add1 = (add1 * modInverse(g,MOD) + MOD)%MOD;
         
         long add2 = ((g+1-a)*sumless + MOD)%MOD;
         add2 = (add2 * modInverse(g+1,MOD) + MOD)%MOD;
         
         answer[k] = (add1 + add2 + MOD)%MOD;
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < m; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj);
      
      
      
      
      
      out.close();
   }
   
   public static long sum(long l, long r){
      if(l > r) return 1L;
      long prod1 = ((r+l)*(r-l+1) + MOD)%MOD;
      return (prod1 * i2 + MOD)%MOD;
   }
   
   //from geeksforgeeks
   public static long modInverse(long a, long m) 
   { 
       long m0 = m; 
       long y = 0L;
       long x = 1L; 
     
       if (m == 1L) 
         return 0L; 
     
       while (a > 1L) 
       { 
           long q = a / m; 
           long t = m; 
     
           m = a % m;
           a = t; 
           t = y; 
     
           y = x - q * y; 
           x = t; 
       } 
     
       if (x < 0L) 
          x += m0; 
     
       return x; 
   } 

      
}