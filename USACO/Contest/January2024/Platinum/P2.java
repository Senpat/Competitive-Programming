//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, hint (think backwards)
//tle O(n^2 logn)
public class P2{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n+1];
      long[] psum = new long[n+1];
      for(int k = 1; k <= n; k++){
         array[k] = Long.parseLong(st.nextToken());
         psum[k] = array[k] + psum[k-1];
      }
      
      FenwickTree[] h = new FenwickTree[n+1];
      FenwickTree[] v = new FenwickTree[n+1];
      for(int k = 1; k <= n; k++){
         h[k] = new FenwickTree(n);
         v[k] = new FenwickTree(n);
      }
      
      //split[l][r] = last index x such that sum of [x,r] is more than half of the sum of [l,r]
      int[][] split = new int[n+1][n+1];
      for(int r = 2; r <= n; r++){
         long lsum = array[r];
         long xsum = 0L;
         int l = r;
         for(int x = r; x >= 1; x--){
            xsum += array[x];
            
            while(l >= 1 && xsum*2L >= lsum){
               split[l][r] = x;
               l--;
               if(l >= 1) lsum += array[l];
            }
         }
      }
      
      //initial state
      h[1].update(n,n,1L);
      
      for(int len = n; len >= 2; len--){
         //number of splits is len-1
         long invsplits = modInverse(len-1,MOD);
         
         for(int l = 1; l+len-1 <= n; l++){
            int r = l+len-1;
            
            long pin = (h[l].psum(r) + v[r].psum(l) + MOD)%MOD;
            //if(pin >= MOD) pin -= MOD;
            
            long pnext = (pin * invsplits + MOD)%MOD;
            
            long rangesum = psum[r] - psum[l-1];
            
            h[l].update(split[l][r],r-1,pnext);
            v[r].update(l+1,split[l][r],pnext);
         }
      }
      
      long[] answer = new long[n+1];
      for(int k = 1; k <= n; k++){
         answer[k] = (h[k].psum(k) + v[k].psum(k) + MOD)%MOD;
         //if(answer[k] >= MOD) answer[k] -= MOD;
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 1; k <= n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      out.close();
   }
   
   public static long neg(long x){
      if(x == 0L) return 0L;
      return MOD-x;
   }
   
   public static class FenwickTree{
      int n;
      long[] bits;
      public FenwickTree(int x){
         n = x;
         bits = new long[n+1];
      }
      
      public void update(int l, int r, long x){
         if(r < l) return;
         update(l,x);
         if(r+1 <= n) update(r+1,neg(x));
      }
      
      public void update(int i, long x){
         for(; i <= n; i += i&-i){
            bits[i] += x;
            //if(bits[i] >= MOD) bits[i] -= MOD;
         }
      }
      
      public long psum(int i){
         long sum = 0L;
         for(; i > 0; i -= i&-i){
            sum += bits[i];
            //if(sum >= MOD) sum -= MOD;
         }
         return sum;
      }
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