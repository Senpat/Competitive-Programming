//make sure to make new file!
import java.io.*;
import java.util.*;
//trying to be cleaner
public class C169b{

   public static long MOD = 998244353L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n+1];
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[][] maxjump = new int[n+1][n+1];
      for(int k = 1; k <= n; k++){
         if(k == array[n] || array[n] == -1){
            maxjump[n][k] = 1;
         } else {
            maxjump[n][k] = 0;
         }
      }
      
      for(int k = n-1; k >= 1; k--){
         for(int j = 1; j <= n; j++){
            if(j == array[k] || array[k] == -1){
               maxjump[k][j] = maxjump[k+1][j]+1;
            } else {
               maxjump[k][j] = 0;
            }
         }
      }
      
      long[][] diff = new long[n+2][n+1];
      long[] psum = new long[n+1];
      //set initial conditions
      psum[0] = 1L;
      diff[1][0] = sub(0,1L);
      
      
      for(int k = 1; k <= n; k++){
         long sum = 0L;
         for(int j = 0; j <= n; j++){
            sum = add(sum,psum[j]);
         }
         
         //update diff
         for(int j = 1; j <= n; j++){
            if(maxjump[k][j] > 0){
               long tot = sub(sum,psum[j]);
               diff[k][j] = add(diff[k][j],tot);
               
               int jump = k + Math.min(j,maxjump[k][j]);
               diff[jump][j] = sub(diff[jump][j],tot);
            }
         }
         
         //update psum
         for(int j = 0; j <= n; j++){
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