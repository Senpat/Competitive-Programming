//make sure to make new file!
import java.io.*;
import java.util.*;

public class B783{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n+1];
         for(int k = 1; k <= n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         long[] psum = new long[n+1];
         psum[0] = 0L;
         HashMap<Long,ArrayList<Integer>> hmap = new HashMap<Long,ArrayList<Integer>>();
         ArrayList<Integer> temp0 = new ArrayList<Integer>();
         temp0.add(0);
         hmap.put(0L,temp0);
         
         for(int k = 1; k <= n; k++){
            psum[k] = psum[k-1] + array[k];
            
            if(!hmap.containsKey(psum[k])){
               ArrayList<Integer> temp = new ArrayList<Integer>();
               temp.add(k);
               hmap.put(psum[k],temp);
            } else {
               hmap.get(psum[k]).add(k);
            }
         }
         
         long[] sufmax = new long[n+1];
         sufmax[n] = psum[n];
         for(int k = n-1; k >= 0; k--){
            sufmax[k] = Math.max(sufmax[k+1],psum[k]);
         }
         
         //dp[i] = max you can get when you end up at i
         long[] dp = new long[n+1];
         Arrays.fill(dp,Long.MIN_VALUE);
         dp[0] = 0L;
         
         for(int k = 1; k <= n; k++){
            //find farthest positive
            long thresh = psum[k]-array[k]+1;
            int l = k;
            int r = n;
            int farpos = -1;
            while(l <= r){
               int mid = l + (r-l)/2;
               
               if(sufmax[mid] >= thresh){
                  l = mid+1;
                  farpos = mid;
               } else {
                  r = mid-1;
               }
            }
            
            //find nearest 0
            int near0 = -1;
            if(hmap.containsKey(psum[k-1])){
               l = 0;
               r = hmap.get(psum[k-1]).size()-1;
               
               while(l <= r){
                  int mid = l + (r-l)/2;
                  
                  if(hmap.get(psum[k-1]).get(mid) > k){
                     near0 = hmap.get(psum[k-1]).get(mid);
                     r = mid-1;
                  } else {
                     l = mid+1;
                  }
               }
            }
            
            
            if(array[k] > 0){
               dp[k] = Math.max(dp[k],dp[k-1]+1);
            } else if(array[k] < 0){
               dp[k] = Math.max(dp[k],dp[k-1]-1);
            } else {
               dp[k] = Math.max(dp[k],dp[k-1]);
            }
            
            if(near0 != -1){
               dp[near0] = Math.max(dp[near0],dp[k-1]);
            }
            
            if(farpos != -1){
               dp[farpos] = Math.max(dp[farpos],dp[k-1]+(farpos-k+1));
            }
         
            
            
            
                
            
         }
      
         out.println(dp[n]);
      
      }
      
      
      
      
      out.close();
   }
   
      
}