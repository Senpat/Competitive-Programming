//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, semi-t
public class E137b{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st;
      
      st = new StringTokenizer(f.readLine());
      int p1 = Integer.parseInt(st.nextToken());
      long t1 = Long.parseLong(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int p2 = Integer.parseInt(st.nextToken());
      long t2 = Long.parseLong(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int h = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      
      long answer = Long.MAX_VALUE;
      
      //dp[k] = minimum time to get k health where both players are at 0 recharge
      long[] dp = new long[h+1];
      Arrays.fill(dp,Long.MAX_VALUE);
      
      dp[h] = 0L;
      
      for(int k = h; k >= 1; k--){
         if(dp[k] == Long.MAX_VALUE) continue;
         
         int i = k;
         long curt1 = t1;
         long curt2 = t2;
         
         long total = 0;
         
         while(i > 0){
            //next step do both
            //if doing both finishes
            if(i-(p1+p2-s) <= 0){
               answer = Math.min(answer,dp[k]+total+Math.max(curt1,curt2));
            } else {
               dp[i-(p1+p2-s)] = Math.min(dp[i-(p1+p2-s)],dp[k]+total+Math.max(curt1,curt2));
            }
            
            //only optimal to do both at the same time
            if(curt1 == curt2) break;
            
            //do one
            if(curt1 < curt2){
               if(i-(p1-s) <= 0){
                  answer = Math.min(answer,dp[k]+total+curt1);
               }
               i -= p1-s;
               curt2 -= curt1;
               total += curt1;
               curt1 = t1;
            } else if(curt2 < curt1){
               if(i-(p2-s) <= 0){
                  answer = Math.min(answer,dp[k]+total+curt2);
               }
               i -= p2-s;
               curt1 -= curt2;
               total += curt2;
               curt2 = t2;
            }
         }
      }
      
      out.println(answer);
      
      
      
      out.close();
   }
   
      
}