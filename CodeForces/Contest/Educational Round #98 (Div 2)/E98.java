//make sure to make new file!
import java.io.*;
import java.util.*;

public class E98{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      
      Interval[] intervals = new Interval[m];
      int[] intp = new int[n+2];
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         intervals[k] = new Interval(a,b);
         
         intp[a]++;
         intp[b+1]--;
      }
      
      int max = 0;
      //loop through possible starting coordinates of first author
      for(int k = 1; k <= n-x+1; k++){
         //copy intp
         int[] curintp = new int[n+2];
         for(int j = 0; j < n+2; j++){
            curintp[j] = intp[j];
         }
         
         int[] bonusaddp = new int[n+2];
         
         int curanswer = 0;
         //loop through all intervals
         for(int j = 0; j < m; j++){
            int maxoverlap = Math.min(x,intervals[j].r-intervals[j].l+1);
            //calc overlap
            int overlap = Math.max(0,Math.min(k+x-1,intervals[j].r) - Math.max(k,intervals[j].l) + 1);
            curanswer += overlap;
            if(overlap == maxoverlap || intervals[j].r <= k+x-1){
               //remove from curintp
               curintp[intervals[j].l]--;
               curintp[intervals[j].r+1]++;
            } else if(overlap < maxoverlap && overlap > 0){
               //partial cover, need to do bonus
               curintp[intervals[j].r+1]++;
               curintp[intervals[j].r+1-overlap]--;
               
               bonusaddp[intervals[j].r+1-overlap] -= overlap;
               bonusaddp[intervals[j].r+1-overlap-x+1]++;
               bonusaddp[intervals[j].r+1-overlap-x+1+overlap]--;
               
               
            }
         }
         
         //get sum up to k
         int sumadd = 0;
         int sum = 0;
         
         int bonusadd = 0;
         int bonus = 0;
         for(int j = 1; j < k+x; j++){
            sumadd += curintp[j];
            sum += sumadd;
            
            bonusadd += bonusaddp[j];
            bonus += bonusadd;
         }
         
         for(int j = k+x; j <= n; j++){
            
            sumadd += curintp[j];
            sum += sumadd;
            
            bonusadd += bonusaddp[j];
            bonus += bonusadd;
         
               
            
         
         
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Interval{
      int l;
      int r;
      public Interval(int a, int b){
         l = a;
         r = b;
      }
   }
   
      
}