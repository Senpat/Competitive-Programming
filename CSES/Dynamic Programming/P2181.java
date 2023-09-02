//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2181{
   
   public static long MOD = 1000000007L;
   public static ArrayList<Integer> masks;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int pn = 1 << n;
      
      masks = new ArrayList<Integer>();
      getmasks(0,0,n);
      
      
      
      long[][] dp = new long[m+1][pn];
      dp[0][0] = 1L;
      
      for(int k = 0; k < m; k++){
         for(int mask = 0; mask < pn; mask++){
            if(dp[k][mask] == 0L) continue;
            
            for(int col : masks){
               //for every 1 in col, masks also needs to have a 1
               if((col | mask) == col){
                  dp[k+1][col ^ mask] = (dp[k+1][col ^ mask] + dp[k][mask] + MOD)%MOD;
               }
            }
         }
      }
      
      out.println(dp[m][0]);
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void getmasks(int curmask, int i, int n){
      if(i == n){
         masks.add(curmask);
      } else {
         getmasks(curmask + (1 << i),i+1,n);
         if(i+2 <= n){
            getmasks(curmask,i+2,n);
         }
      }
   }
   
      
}