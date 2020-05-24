//make sure to make new file!
import java.io.*;
import java.util.*;

public class E{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      long[] array = new long[n];
      StringTokenizer st = new StringTokenizer(f.readLine());
      PriorityQueue<Num> pq = new PriorityQueue<Num>(n,Collections.reverseOrder());
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         pq.add(new Num(array[k],(long)k));
      }
      
      long[][] dp = new long[n][n+1];
      for(int k = 0; k < n; k++) Arrays.fill(dp[k],-1);      
      
      long ln = (long)n;
      
      Num first = pq.poll();
      dp[0][0] = first.v*(ln-1-first.ind);
      dp[0][1] = first.v*first.ind;
      
      
      for(int k = 0; k < n-1; k++){
         Num next = pq.poll();
         for(int j = 0; j <= n; j++){
            if(dp[k][j] == -1) continue;
            
            //add left
            dp[k+1][j+1] = Math.max(dp[k+1][j+1],dp[k][j]+next.v*Math.abs(next.ind-j));
            
            //add right
            dp[k+1][j] = Math.max(dp[k+1][j],dp[k][j]+next.v*Math.abs(ln-2-next.ind-(k-j)));
         }
      }
      
      long answer = 0L;
      
      for(int k = 0; k <= n; k++){
         answer = Math.max(answer,dp[n-1][k]);
      }
      
      out.println(answer);
         
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Num implements Comparable<Num>{
      long v;
      long ind;
      
      public Num(long a, long b){
         v = a;
         ind = b;
      }
      public int compareTo(Num n){
         if(v > n.v) return 1;
         if(v < n.v) return -1;
         return 0;
      }
   }
   
      
}