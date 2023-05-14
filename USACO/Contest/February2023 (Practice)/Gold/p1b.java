//make sure to make new file!
import java.io.*;
import java.util.*;
//O(n^2logn)
public class p1b{
   
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
      
      for(int r = 1; r < ranges.size(); r++){
         long dif = ranges.get(r).sum - ranges.get(r-1).sum;
      
         //get non-overlaps
         int l1 = ranges.get(r-1).l;
         int r1 = ranges.get(r-1).r;
         int l2 = ranges.get(r).l;
         int r2 = ranges.get(r).r;
         
         if(l1 > l2){
            //flip them
            int ltemp = l1;
            l1 = l2;
            l2 = ltemp;
            int rtemp = r1;
            r1 = r2;
            r2 = rtemp;
         }
         
         if(l1 == l2){
            //left end point same
            start.get(Math.min(r1,r2)+1).add(dif);
            end.get(Math.max(r1,r2)).add(dif);
         } else {
            if(r1 < l2){
               //non overlapping
               start.get(l1).add(dif);
               end.get(r1).add(dif);
               start.get(l2).add(dif);
               end.get(r2).add(dif);
            } else if(r1 < r2){
               //partially overlapping
               start.get(l1).add(dif);
               end.get(l2-1).add(dif);
               start.get(r1+1).add(dif);
               end.get(r2).add(dif);
            } else if(r1 == r2){
               //right end point same
               start.get(l1).add(dif);
               end.get(l2-1).add(dif);
            } else {
               //r1 > r2
               start.get(l1).add(dif);
               end.get(l2-1).add(dif);
               start.get(r2+1).add(dif);
               end.get(r1).add(dif);
            }
         }
      }
      
      tmap = new TreeMap<Long,Integer>();
      long[] answer = new long[n+1];
      for(int k = 1; k <= n; k++){
         for(long x : start.get(k)) add(x);
         
         answer[k] = tmap.firstKey();
         
         for(long x : end.get(k)) remove(x);
      }
      
      for(int k = 1; k <= n; k++){
         out.println(answer[k]);
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