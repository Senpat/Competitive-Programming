//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, semi-t
public class E643{
   
   public static int n;
   public static long a,b,m;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      a = Long.parseLong(st.nextToken());
      b = Long.parseLong(st.nextToken());
      m = Long.parseLong(st.nextToken());
      
      m = Math.min(m,a+b);
      
      Long[] array = new Long[n];
      st = new StringTokenizer(f.readLine());
      long sum = 0L;
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         sum += array[k];
      }
      
      Arrays.sort(array);
      
      long[] psum = new long[n+1];
      psum[0] = 0L;
      
      for(int k = 1; k <= n; k++){
         psum[k] = psum[k-1]+array[k-1];
      }
      
      //first two breakpoints, floor of sum/n and ceiling of sum/n
      long answer = Math.min(bs(array,psum,sum/n),bs(array,psum,sum/n+1));
      
      for(int k = 0; k < n; k++){
         answer = Math.min(answer,bs(array,psum,array[k]));
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
   public static long bs(Long[] array, long[] psum, long h){
      //get increase and decrease
      
      int l,r,ans;
      
      l = 0;
      r = n-1;
      ans = n-1;        //ans is the index of the first number >= h, also the number of numbers < h
      
      while(l <= r){
         int mid = l + (r-l)/2;
         
         if(array[mid] >= h){
            ans = mid;
            r = mid-1;
         } else {
            l = mid+1;
         }
      }
      
      long in = h*ans - psum[ans];
      
      l = 0;
      r = n-1;
      ans = 0;          //ans is the index of last number <= h
      
      while(l <= r){
         int mid = l + (r-l)/2;
         
         if(array[mid] <= h){
            ans = mid;
            l = mid+1;
         } else {
            r = mid-1;
         }
      }
      
      long de = psum[n] - psum[ans+1] - h*(n-ans-1);
      if(in >= de){
         return m*de + a*(in-de);
      } else {
         return m*in + b*(de-in);
      }
   }
   
      
}