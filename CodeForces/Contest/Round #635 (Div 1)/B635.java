//make sure to make new file!
import java.io.*;
import java.util.*;

public class B635{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int rn = Integer.parseInt(st.nextToken());
         int gn = Integer.parseInt(st.nextToken());
         int bn = Integer.parseInt(st.nextToken());
         
         Long[] r = new Long[rn];
         Long[] g = new Long[gn];
         Long[] b = new Long[bn];
         
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < rn; k++){
            r[k] = Long.parseLong(st.nextToken());
         }
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < gn; k++){
            g[k] = Long.parseLong(st.nextToken());
         }
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < bn; k++){
            b[k] = Long.parseLong(st.nextToken());
         }
         
         Arrays.sort(r);
         Arrays.sort(g);
         Arrays.sort(b);
         
         long answer = Long.MAX_VALUE;
         
         //loop through every number and set that as the middle
         long ri;
         long gi;
         long bi;
         for(int k = 0; k < rn; k++){
            gi = g[smallestgreaterbs(g,r[k])];
            bi = b[biggestsmallerbs(b,r[k])];
            answer = Math.min(answer,calc(r[k],gi,bi));
            gi = g[biggestsmallerbs(g,r[k])];
            bi = b[smallestgreaterbs(b,r[k])];
            answer = Math.min(answer,calc(r[k],gi,bi));
         }
         
         for(int k = 0; k < gn; k++){
            ri = r[smallestgreaterbs(r,g[k])];
            bi = b[biggestsmallerbs(b,g[k])];
            answer = Math.min(answer,calc(g[k],ri,bi));
            ri = r[biggestsmallerbs(r,g[k])];
            bi = b[smallestgreaterbs(b,g[k])];
            answer = Math.min(answer,calc(g[k],ri,bi));
         }
         
         for(int k = 0; k < bn; k++){
            
            gi = g[smallestgreaterbs(g,b[k])];
            ri = r[biggestsmallerbs(r,b[k])];
            answer = Math.min(answer,calc(b[k],gi,ri));
            gi = g[biggestsmallerbs(g,b[k])];
            ri = r[smallestgreaterbs(r,b[k])];
            answer = Math.min(answer,calc(b[k],gi,ri));
         }
               
         
         out.println(answer);
         
         
      
      }
      
      
      
      
      out.close();
   }
   
   public static long calc(long a, long b, long c){
      return (a-b)*(a-b)+(b-c)*(b-c)+(a-c)*(a-c);
   }
   
   public static int smallestgreaterbs(Long[] array, long i){
      int l= 0, r = array.length-1;
      int ans = array.length-1;
      while (l <= r) {
         int mid = l + (r-l)/2;
         if (array[mid] >= i) {
            ans = mid;
            r = mid-1;
         }
         else {
            l = mid+1;
         }
      }
      
      return ans;
   }
   
   public static int biggestsmallerbs(Long[] array, long i){
      int l= 0, r = array.length-1;
      int ans = 0;
      while (l <= r) {
         int mid = l + (r-l)/2;
         if (array[mid] >= i) {
            ans = mid;
            r = mid-1;
         }
         else {
            l = mid+1;
         }
      }
      
      while(ans > 0 && array[ans] > i){
         ans--;
      }
      
      return ans;
   }
      
}