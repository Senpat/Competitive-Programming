//make sure to make new file!
import java.io.*;
import java.util.*;

public class C169{

   public static long MOD = 998244353L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[][] maxjump = new int[n][n+1];
      for(int k = 1; k <= n; k++){
         if(k == array[n-1] || array[n-1] == -1){
            maxjump[n-1][k] = 1;
         } else {
            maxjump[n-1][k] = 0;
         }
      }
      for(int k = n-2; k >= 0; k--){
         for(int j = 1; j <= n; j++){
            if(j == array[k] || array[k] == -1){
               maxjump[k][j] = maxjump[k+1][j]+1;
            } else {
               maxjump[k][j] = 0;
            }
         }
      }
      
      long[][] diff = new long[n+1][n+1];
      long[] psum = new long[n+1];
      
      //initial states
      for(int k = 1; k <= n; k++){
         //add k
         if(maxjump[0][k] > 0){
            diff[0][k] = add(diff[0][k],1L);
            int jump = Math.min(maxjump[0][k],k);
            
            diff[jump][k] = sub(diff[jump][k],1L);
         }
         
         psum[k] += diff[0][k];
      }
      
      for(int k = 1; k < n; k++){
         //make streaks starting at k
         long sum = 0L;
         for(int j = 1; j <= n; j++){
            sum = add(sum,psum[j]);
         }
         
         //update diff
         for(int j = 1; j <= n; j++){
            if(maxjump[k][j] == -1) continue;
            
            long tot = sub(sum,psum[j]);
            diff[k][j] = add(diff[k][j],tot);
            
            int jump = k + Math.min(j,maxjump[k][j]);
            diff[jump][j] = sub(diff[jump][j],tot);
         }
         
         //update psum
         for(int j = 1; j <= n; j++){
            psum[j] = add(psum[j],diff[k][j]);
         }
         
      }
      
      long answer = 0L;
      for(int k = 1; k <= n; k++){
         answer = add(answer,psum[k]);
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static long add(long a, long b){
      long ret = a+b;
      if(ret >= MOD) ret -= MOD;
      return ret;
   }
   
   public static long sub(long a, long b){
      long ret = a-b;
      if(ret < 0) ret += MOD;
      return ret;
   }
      
}