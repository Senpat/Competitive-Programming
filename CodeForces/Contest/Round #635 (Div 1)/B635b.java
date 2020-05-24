//make sure to make new file!
import java.io.*;
import java.util.*;

public class B635b{
   
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
         
         int ri;
         int gi;
         int bi;
         
         gi = 0;
         bi = 0;
         //g r b
         for(int k = 0; k < rn; k++){
            
            while(gi < gn-1 && g[gi+1] <= r[k]){
               gi++;
            }
            
            while(bi < bn-1 && b[bi] < r[k]){
               bi++;
            }
            
            answer = Math.min(answer,calc(r[k],g[gi],b[bi]));
         }
         gi = 0;
         bi = 0;
         //b r g
         for(int k = 0; k < rn; k++){
            
            while(bi < bn-1 && b[bi+1] <= r[k]){
               bi++;
            }
            
            while(gi < gn-1 && g[gi] < r[k]){
               gi++;
            }
            
            answer = Math.min(answer,calc(r[k],g[gi],b[bi]));
         }
         ri = 0;
         bi = 0;
         //r g b
         for(int k = 0; k < gn; k++){
            
            while(ri < rn-1 && r[ri+1] <= g[k]){
               ri++;
            }
            
            while(bi < bn-1 && b[bi] < g[k]){
               bi++;
            }
            
            answer = Math.min(answer,calc(r[ri],g[k],b[bi]));
         }
         ri = 0;
         bi = 0;
         //b g r
         for(int k = 0; k < gn; k++){
            
            while(bi < bn-1 && b[bi+1] <= g[k]){
               bi++;
            }
            
            while(ri < rn-1 && r[ri] < g[k]){
               ri++;
            }
            
            answer = Math.min(answer,calc(r[ri],g[k],b[bi]));
         }
         ri = 0;
         gi = 0;
         //r b g
         for(int k = 0; k < bn; k++){
            
            while(ri < rn-1 && r[ri+1] <= b[k]){
               ri++;
            }
            
            while(gi < gn-1 && g[gi] < b[k]){
               gi++;
            }
            
            answer = Math.min(answer,calc(r[ri],g[gi],b[k]));
         }
         gi = 0;
         ri = 0;
         //g b r
         for(int k = 0; k < bn; k++){
            
            while(gi < gn-1 && g[gi+1] <= b[k]){
               gi++;
            }
            
            while(ri < rn-1 && r[ri] < b[k]){
               ri++;
            }
            
            answer = Math.min(answer,calc(r[ri],g[gi],b[k]));
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