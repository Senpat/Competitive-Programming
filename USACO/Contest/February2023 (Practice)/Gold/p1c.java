//make sure to make new file!
import java.io.*;
import java.util.*;
//O(n^2logn), better implementation (danny tutorial)
public class p1c{
   
   public static TreeMap<Long,Integer> tmap;
   
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
      
      //each will hold O(N^2) elements
      ArrayList<ArrayList<Long>> start = new ArrayList<ArrayList<Long>>(n+1);
      ArrayList<ArrayList<Long>> end = new ArrayList<ArrayList<Long>>(n+1);
      for(int k = 0; k <= n; k++){
         start.add(new ArrayList<Long>());
         end.add(new ArrayList<Long>());
      }
      
      long[][] min = new long[n+1][n+1];        //min[l][r] = x -> i = min(i,x) for all values in [l,r]
      for(int k = 0; k <= n; k++) Arrays.fill(min[k],Long.MAX_VALUE);
      
      for(int r = 1; r < ranges.size(); r++){
         long dif = ranges.get(r).sum - ranges.get(r-1).sum;
      
         //get non-overlaps
         int l1 = ranges.get(r-1).l;
         int r1 = ranges.get(r-1).r;
         int l2 = ranges.get(r).l;
         int r2 = ranges.get(r).r;
         
         int L,R;
         
         L = Math.min(l1,l2);
         R = Math.min(Math.max(l1,l2)-1,Math.min(r1,r2));
         
         if(L <= R){
            min[L][R] = Math.min(min[L][R],dif);
         }
         
         L = Math.max(Math.max(l1,l2), Math.min(r1,r2)+1);
         R = Math.max(r1,r2);
         
         if(L <= R){
            min[L][R] = Math.min(min[L][R],dif);
         }
      }
      
      //fill in min
      for(int l = 1; l <= n; l++){
         for(int r = n; r > l; r--){
            min[l+1][r] = Math.min(min[l+1][r],min[l][r]);
            min[l][r-1] = Math.min(min[l][r-1],min[l][r]);
         }
      }
      
      for(int k = 1; k <= n; k++){
         out.println(min[k][k]);
      }
      
      
      
      
      out.close();
   }
   
   public static void add(long x){
      if(tmap.containsKey(x)){
         tmap.put(x,tmap.get(x)+1);
      } else {
         tmap.put(x,1);
      }
   }
   
   public static void remove(long x){
      if(tmap.get(x) == 1){
         tmap.remove(x);
      } else {
         tmap.put(x,tmap.get(x)-1);
      }
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
      
      public int compareTo(Range r){
         if(sum < r.sum) return -1;
         if(sum > r.sum) return 1;
         return 0;
      }
   }
      
}