//Little Pony and Harmony Chest
import java.io.*;
import java.util.*;

public class B453{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] primes = new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53};
      int P = 16;
      int pp2 = (1 << P);
      int M = 58;                //max value
      
      //precompute valid transitions
      int[] transitions = new int[M+1];      //stores mask of transition
      Arrays.fill(transitions,0);
      for(int k = 1; k <= M; k++){
         for(int p = 0; p < P; p++){
            if(k % primes[p] == 0){
               transitions[k] += (1 << p);
            }
         }
      }
      
      int[][] dp = new int[n][pp2];
      for(int k = 0; k < n; k++) Arrays.fill(dp[k],Integer.MAX_VALUE);
      int[][] par = new int[n][pp2];
      
      int allmask = pp2-1;
      //initial states
      for(int t = 1; t <= M; t++){
         int newmask = allmask ^ transitions[t];
         int curcost = Math.abs(array[0]-t);
         if(dp[0][newmask] > curcost){
            dp[0][newmask] = curcost;
            par[0][newmask] = t;
         }
      }
      
      for(int k = 0; k < n-1; k++){
         for(int mask = 0; mask < pp2; mask++){
            if(dp[k][mask] == Integer.MAX_VALUE) continue;
            for(int t = 1; t <= M; t++){
               if((mask & transitions[t]) != transitions[t]) continue;
               int newmask = mask ^ transitions[t];
               int curcost = Math.abs(array[k+1]-t);
               if(dp[k][mask] + curcost < dp[k+1][newmask]){
                  dp[k+1][newmask] = dp[k][mask] + curcost;
                  par[k+1][newmask] = t;
               }
            }
         }
      }
      
      int minmask = 0;
      for(int k = 1; k < pp2; k++){
         if(dp[n-1][minmask] > dp[n-1][k]){
            minmask = k;  
         }
      }
      
      int[] answer = new int[n];
      int curmask = minmask;
      for(int k = n-1; k >= 0; k--){
         answer[k] = par[k][curmask];
         curmask ^= transitions[par[k][curmask]];
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      out.close();
   }
   
   public static class Transition{
      int mask;
      int val;
      public Transition(int a, int b){
         mask = a;
         val = b;
      }
   }
}