//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, http://serjudging.vanb.org/wp-content/uploads/circle_of_friends.pdf
public class D{

   public static long MOD = 998244353L;
   
   public static int[] log;
   public static long[][] spt;
   public static int N = 200005;
   public static int M = 19;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      long[] array = new long[n+1];
      array[0] = 1L;
      long and = (1L << 60)-1L;
      for(int k = 1; k <= n; k++){
         array[k] = Long.parseLong(f.readLine());
         and &= array[k];
      }
      
      if(and > 0L){
         //answer is 2^n
         long pow2 = 1L;
         for(int k = 0; k < n; k++){
            pow2 = (pow2*2L + MOD)%MOD;
         }
         pow2 = ((pow2-(long)n)%MOD + MOD)%MOD;
         out.println(pow2);
         out.close();
         return;
      }
      
      long answer = 0L;
      
      spt = new long[M][N];
      log = new int[N];
      log[1] = 0;
      for(int k = 2; k < N; k++){
         log[k] = log[k/2]+1;
      }
      
      long[] curdp = calc(array,n);
      answer += curdp[n];
      
      int last = n;
      long curand = array[1];
      
      while(curand > 0 && last > 0){
         curand &= array[last];
         if(curand == 0) break;
         
         if(curand != array[1]){
            array[1] = curand;
            curdp = calc(array,last-1);
         }
         
         last--;
         
         answer = (answer + curdp[last] + MOD)%MOD;
         
      }
      /*
      for(int k = n; k >= 1; k--){
         if(k < n){
            array[1] &= array[k+1];
            if(array[1] == 0) break;
         }
         
         long[] curdp = calc(array,k);
         answer = (answer + curdp[k] + MOD)%MOD;
      }*/
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static long[] calc(long[] array, int n){
      
      initspt(array,n);
      
      //precompute jumps
      //jump[x] stores the earliest index i such that and of [i,x] is not zero
      int[] jump = new int[n+1];
      jump[1] = 1;
      int l = 1;
      for(int k = 2; k <= n; k++){
         while(getand(l,k) == 0L){
            l++;
         }
         jump[k] = l;
      }
      
      long[] dp = new long[n+1];
      long[] psum = new long[n+1];
      
      dp[0] = 1L;
      psum[0] = 0L;
      
      for(int k = 1; k <= n; k++){
         long cur = psum[k-1];
         
         if(jump[k] > 1){
            cur = ((cur-psum[jump[k]-2])%MOD + MOD)%MOD;
         } else {
            cur = (cur + 1L + MOD)%MOD;
         }
         
         dp[k] = cur;
         psum[k] = (psum[k-1] + dp[k] + MOD)%MOD;
         
      }
      
      return dp;
      
      
   }
   
   //bitwise and sparse table
   public static void initspt(long[] array, int n){
      for(int k = 1; k <= n; k++){
         spt[0][k] = array[k];
      }
      
      for(int j = 1; j < M; j++){
         for(int k = 1; k + (1 << j)-1 <= n; k++){
            spt[j][k] = spt[j-1][k] & spt[j-1][k+(1 << (j-1))];
         }
      }
   }
   
   public static long getand(int l, int r){
      int lg = log[r-l+1];
      return spt[lg][l] & spt[lg][r-(1 << lg)+1];
   }
      
}