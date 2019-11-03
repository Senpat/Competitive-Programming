//make sure to make new file!
import java.io.*;
import java.util.*;
//didnt account for empty rows in contest
public class D577{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int t = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      int[] mins = new int[n];       //stores max and min column for every row
      int[] maxs = new int[n];
      Arrays.fill(mins,Integer.MAX_VALUE);
     
      for(int k = 0; k < t; k++){
         st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken())-1;
         int b = Integer.parseInt(st.nextToken())-1;  
         mins[a] = Math.min(mins[a],b);
         maxs[a] = Math.max(maxs[a],b);
      }
      
      long[] safes = new long[q];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < q; k++){
         safes[k] = Long.parseLong(st.nextToken())-1;
      }
      shuffleArray(safes);
      Arrays.sort(safes);
      
      long[] nextl = new long[m];
      long[] nextr = new long[m];
      
      int last = -1;
      int safesi = 0;
      for(int k = 0; k < m; k++){
         if(safesi < q && safes[safesi] == k){
            last = k;
            safesi++;
         }
         nextl[k] = last;
      }
      
      last = -1;
      safesi = q-1;
      for(int k = m-1; k >= 0; k--){
         if(safesi >= 0 && safes[safesi] == k){
            last = k;
            safesi--;
         }
         nextr[k] = last;
      }
      
      long[][] dp = new long[n][2];
      for(int k = 0; k < n; k++){
         for(int j = 0; j <= 1; j++){
            dp[k][j] = Long.MAX_VALUE;
         }
      }
      
      dp[0][1] = (long)maxs[0];
      
      for(int k = 0; k < n-1; k++){
         for(int j = 0; j <= 1; j++){
            if(dp[k][j] == Long.MAX_VALUE) continue;
            int curcol = -1;
            if(j == 0) curcol = mins[k];
            else curcol = maxs[k];
            int next = k+1;
            while(next < n-1 && maxs[next] == 0 && mins[next] == Integer.MAX_VALUE){
               next++;
            }
            
            if(next >= n){
               dp[n-1][j] = Math.min(dp[n-1][j],dp[k][j]);
               continue;
            }
            long INC = (long)(next-k);
            
            //find closest distance to max of next row
            long closestmax = Long.MAX_VALUE;
            
            //go left
            if(nextl[curcol] != -1) closestmax = curcol - nextl[curcol] + INC + Math.abs(maxs[k+1] - nextl[curcol]);
            //go right
            if(nextr[curcol] != -1) closestmax = Math.min(closestmax, nextr[curcol]-curcol + INC + Math.abs(maxs[k+1] - nextr[curcol]));
            
            dp[k+1][0] = Math.min(dp[k+1][0],dp[k][j] + (long)(closestmax + maxs[k+1]-mins[k+1]));
            
            //find closest distance to min of next row
            long closestmin = Long.MAX_VALUE;
            
            //go left
            if(nextl[curcol] != -1) closestmin = curcol - nextl[curcol] + INC + Math.abs(mins[k+1] - nextl[curcol]);
            //go right
            if(nextr[curcol] != -1) closestmin = Math.min(closestmin, nextr[curcol]-curcol + INC + Math.abs(mins[k+1] - nextr[curcol]));
      
            dp[k+1][1] = Math.min(dp[k+1][1],dp[k][j] + (long)(closestmin + maxs[k+1]-mins[k+1]));
         }
      }
      
      long answer = Math.min(dp[n-1][0],dp[n-1][1]);
      out.println(answer);
      
      out.close();
   }
   
   public static void shuffleArray(long[] arr){
      int n = arr.length;
      Random rnd = new Random();
      for(int i=0; i<n; ++i){
         long tmp = arr[i];
         int randomPos = i + rnd.nextInt(n-i);
         arr[i] = arr[randomPos];
         arr[randomPos] = tmp;
      }   
   }
   
      
}