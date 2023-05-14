//make sure to make new file!
import java.io.*;
import java.util.*;
//O(N^3)
public class p1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n+1];
      long[] psum = new long[n+1];
      for(int k = 1; k <= n; k++){
         array[k] = Long.parseLong(st.nextToken());
         psum[k] = psum[k-1] + array[k];
      }
      
      ArrayList<Range> ranges = new ArrayList<Range>();
      for(int l = 1; l <= n; l++){
         for(int r = l; r <= n; r++){
            ranges.add(new Range(l,r,psum[r]-psum[l-1]));
         }
      }
      
      Collections.sort(ranges);
      
      long[] answer = new long[n+1];
      Arrays.fill(answer, Long.MAX_VALUE);
      
      for(int k = 1; k <= n; k++){
         for(int r = 1; r < ranges.size(); r++){
            if(ranges.get(r-1).in(k) ^ ranges.get(r).in(k)){
               answer[k] = Math.min(answer[k],ranges.get(r).sum - ranges.get(r-1).sum);
            }
         }
      } 
     
      for(int k = 1; k <= n; k++){
         out.println(answer[k]);
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Range implements Comparable<Range>{
      int l;
      int r;
      long sum;
      public Range(int a, int b, long c){
         l = a;
         r = b;
         sum = c;
      }
      
      public boolean in(int x){
         return l <= x && x <= r;
      }
      
      public int compareTo(Range r){
         if(sum < r.sum) return -1;
         if(sum > r.sum) return 1;
         return 0;
      }
   }
   
      
}