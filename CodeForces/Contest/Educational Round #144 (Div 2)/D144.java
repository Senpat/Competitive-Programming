//make sure to make new file!
import java.io.*;
import java.util.*;
//in contests attempt (didn't finish, overcomplicated)
public class D144{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         long x = Long.parseLong(st.nextToken());
         
         long[] psum = new long[n+1];
         psum[0] = 0L;
         long[] array = new long[n+1];
         st = new StringTokenizer(f.readLine());
         for(int k = 1; k <= n; k++){
            array[k] = Long.parseLong(st.nextToken());
            psum[k] = psum[k-1] + array[k];
         }
         
         if(x == 0){
            //get maximum sum subarray
            long answer = maxsum(array,psum);
            out.println(answer);
         } else if(m == 0){
            for(int k = 1; k <= n; k++){
               array[k] -= x;
               psum[k] = psum[k-1] + array[k];
            }
            long answer = maxsum(array,psum);
            out.println(answer);
         } else if(x > 0 || true){
            long answer = 0L;
            //try lengths of <= m;
            
            for(int len = 1; len <= m; len++){
               //get initial length
               long curwindow = x*len;
               for(int k = 1; k <= len; k++){
                  curwindow += array[k];
               }
               answer = Math.max(answer,curwindow);
               for(int k = len+1; k <= n; k++){
                  curwindow -= array[k-len];
                  curwindow += array[k];
                  answer = Math.max(answer,curwindow);
               }
            }
            
            //lengths > m
            long curmin = Long.MAX_VALUE;          //minimum value of psum[i]-i*x
            for(int k = 1; k < n; k++){
               if(curmin != Long.MAX_VALUE){
                  long curanswer = psum[k] + x*m*2 - x*k - curmin;
                  answer = Math.max(answer,curanswer);
               }
               if(k-m >= 1){
                  curmin = Math.min(curmin,psum[k-m] - (k-m)*x);
               }
            }
            
            out.println(answer);
            
         } else if(x < 0){
         
            
         }
      
      }
      
      
      
      
      out.close();
   }
   
   public static long maxsum(long[] array, long[] psum){
      int n = array.length-1;
      long max = 0;
      long minpsum = 0;
      for(int k = 1; k <= n; k++){
         max = Math.max(max,psum[k]-minpsum);
         minpsum = Math.min(minpsum,psum[k]);
      }
      return max;
   }
      
   
      
}