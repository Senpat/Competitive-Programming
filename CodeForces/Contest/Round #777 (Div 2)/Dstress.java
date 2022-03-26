//make sure to make new file!
import java.io.*;
import java.util.*;

public class Dstress{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      long N = 1000;
      
      for(long d = 2; d <= N; d++){
         for(long x = d; x <= N; x += d){
            boolean calcbf = bf(x,d);
            boolean calcsol = sol(x,d);
            
            if(calcbf != calcsol){
               out.println("ERROR FOUND");
               out.println(x + " " + d + " " + calcbf + " " + calcsol);
            }
         }
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean bf(long x, long d){
      long[][] dp = new long[(int)x+1][(int)x+1];
      
      dp[1][1] = 1;
      
      for(int k = 1; k <= x; k++){
         for(int j = 1; j <= x; j++){
            if(dp[k][j] == 0) continue;
            for(int newd = j; k*(int)d * newd <= x; newd++){
               if(newd%d == 0) continue;
               dp[k*(int)d*newd][newd] += dp[k][j];
            }
         }
      }
      
      int sum = 0;
      for(int j = 1; j <= x; j++){
         sum += dp[(int)x][j];
         if(sum >= 2) return true;
      }
      
      return false;
   }
   
   
   
   public static boolean sol(long x, long d){
      if(x % (d*d) != 0){
         return false;
      } else {
         long div = x/(d*d);
            
         int found = 0;
            
         while(found < 2){
               //find two factors of div that aren't divisible by d
               
            for(long k = 1; k*k <= div; k++){
               if(div % k == 0){
                  if(k % d == 0 || (div/k)%d == 0) 
                     continue;
                     
                  found++;
                  if(found >= 2) 
                     break;
               }
            }
               
               
               
            if(div % d == 0){
               div/=d;
            } else {
               break;
            }
         }
            
         if(found >= 2){
            return true;
         } else {
            return false;
         }
      }
   }
   
      
}