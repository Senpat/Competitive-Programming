//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1162{
   
   public static long[] bits;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n+1];
      int[] indexof = new int[n+1];
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         indexof[array[k]] = k;
      }
      
      bits = new long[n+1];
      
      //1
      //# of inversions
      long a1 = 0L;
      for(int k = n; k >= 1; k--){
         a1 += psum(indexof[k]);
         update(indexof[k],1L);
      }
      
      //2
      //n-(# cycles)
      boolean[] seen = new boolean[n+1];
      int cycles = 0;
      for(int k = 1; k <= n; k++){
         if(seen[k]) continue;
         
         cycles++;
         int i = array[k];
         seen[k] = true;
         while(!seen[i]){
            seen[i] = true;
            i = array[i];
         }
      }
      
      int a2 = n-cycles;
      
      //3
      //n - LIS
      
      ArrayList<Integer> lis = new ArrayList<Integer>();
      lis.add(array[1]);
      for(int k = 2; k <= n; k++){
         //get smallest index whose value is > array[k]
         int l = 0;
         int r = lis.size()-1;
         int ans = -1;
         
         while(l <= r){
            int mid = l+(r-l)/2;
            
            if(lis.get(mid) > array[k]){
               ans = mid;
               r = mid-1;
            } else {
               l = mid+1;
            }
         }
         
         if(ans == -1) lis.add(array[k]);
         else lis.set(ans,array[k]);
      }
      
      int a3 = n-lis.size();
      
      //4
      //n - (longest chain x, ..., x+1, ... n-1, ... n)
      int i = 0;
      for(int k = n; k >= 1; k--){
         if(array[k] == n-i){
            i++;
         }
      }
      int a4 = n-i;
      
      out.println(a1 + " " + a2 + " " + a3 + " " + a4);
         
      
      
      
      
      
      
      
      out.close();
   }
   
      
   public static void update(int i, long x){
      for(; i <= n; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static long psum(int i){
      long sum = 0L;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   
   }
  
      
}