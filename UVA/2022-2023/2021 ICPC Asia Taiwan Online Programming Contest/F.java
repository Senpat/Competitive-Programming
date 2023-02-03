//make sure to make new file!
import java.io.*;
import java.util.*;

public class F{
   
   public static long[] bits;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      bits = new long[n+1];
      
      int[] array = new int[n+1];
      st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] diff = new int[n];
      for(int k = 1; k < n; k++){
         diff[k] = Math.abs(array[k]-array[k+1]);
      }
      
      TreeSet<Integer> starts = new TreeSet<Integer>();
      int[] ends = new int[n+1];             //ends[x] stores the end of the range starting at x
      
      //initial ranges
      int start = -1;
      for(int k = 1; k < n; k++){
         if(diff[k] == 0 && start != -1){
            //end a streak
            starts.add(start);
            ends[start] = k;
            update(start,c2(ends[start]-start+1));
            start = -1;
         } else if(diff[k] == 1 && start == -1){
            start = k;
         }
      }
      
      if(start != -1){
         starts.add(start);
         ends[start] = n;
         update(start,c2(ends[start]-start+1));
      }
      
      for(int qq = 0; qq < q; qq++){
         //System.out.println(qq + ": ");
         //printarray(diff);
      
         st = new StringTokenizer(f.readLine());
         int i = Integer.parseInt(st.nextToken());
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         
         if(i == 1){
            //update
            if(l > 1){
               //flip diffs[l-1]
               flip(l-1,diff,starts,ends);
            }
            
            if(r < n){
               //flip diffs[r]
               flip(r,diff,starts,ends);
            }
         
         } else {
            //query
         
            long answer = (long)(r-l+1);           //size 1
            answer += psum(r)-psum(l-1);
            
            //check left end
            Integer leftI = starts.lower(l);
            if(leftI != null){
               int left = (int)leftI;
               if(ends[left] >= r){
                  //encompasses entire range
                  answer = c2(r-l+2);
                  out.println(answer);
                  continue;
               }
               
               if(ends[left] >= l){
                  answer += c2(ends[left]-l+1);
               }
            }
            
            //check right end
            int right = starts.floor(r);
            if(ends[right] > r){
               answer -= c2(ends[right]-right+1);
               answer += c2(r-right+1);
            }
            
            out.println(answer);
         
         }
      
      
      
      }
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void flip(int x,int[] diffs, TreeSet<Integer> starts, int[] ends){
      
      if(x > 1 && diffs[x] == 0 && diffs[x-1] == 1){
         //special case: you are in a range and you can potentially join right
         int curstart = starts.lower(x);
         
         if(starts.contains(x+1)){
            update(x+1,-1L*c2(ends[x+1]-(x+1)+1));
            update(curstart,-1L*c2(ends[curstart]-curstart+1));
            
            
            ends[curstart] = ends[x+1];
            ends[x+1] = 0;
            starts.remove(x+1);
            
            update(curstart,c2(ends[curstart]-curstart+1));
         } else {
            //extend by 1
            update(curstart,-1L*c2(ends[curstart]-curstart+1));
            ends[curstart]++;
            update(curstart,c2(ends[curstart]-curstart+1));
            
         }
         
      } else if(diffs[x] == 1){
         //split range
         //see the start of what range you're in
         int curstart = starts.floor(x);
         int curend = ends[curstart];
         
         update(curstart,-1L*c2(curend-curstart+1));
         starts.remove(curstart);
         ends[curstart] = 0;
         
         //split left part
         if(x > curstart){
            starts.add(curstart);
            ends[curstart] = x;
            update(curstart,c2(ends[curstart]-curstart+1));
         }
         
         //split right part
         if(x < curend-1){
            starts.add(x+1);
            ends[x+1] = curend;
            update(x+1,c2(ends[x+1]-(x+1)+1));
         }
         
         
      } else {
         //diffs[x] = 0, potentially join range
         
         boolean joined = false;
         int rightstart = x;
         //can't join left
         
         //potentially join right
         if(starts.contains(x+1)){
            update(x+1,-1L*c2(ends[x+1]-(x+1)+1));
            if(rightstart != x){
               update(rightstart,-1L*c2(ends[rightstart]-rightstart+1));
            }
            
            ends[rightstart] = ends[x+1];
            ends[x+1] = 0;
            starts.remove(x+1);
            starts.add(rightstart);
            
            update(rightstart,c2(ends[rightstart]-rightstart+1));
            
            joined = true;
         }
         
         if(!joined){
            starts.add(x);
            ends[x] = x+1;
            update(x,c2(ends[x]-x+1));
         }
      }
      
      diffs[x] = 1-diffs[x];
   
   }
   
   public static long c2(long x){
      return x * (x-1)/2L;
   }
   
   public static void update(int i, long x){
      for(; i <= n; i += i&-i){
         bits[i] += x;
      }
   }
   
   public static long psum(int i){
      long sum = 0L;
      for(; i > 0; i -= i&-i){
         sum += bits[i];
      }
      
      return sum;
   }
   
   public static void printarray(int[] array){
      for(int k = 1; k < array.length; k++){
         System.out.print(array[k] + " ");
      }
      System.out.println();
   }
}