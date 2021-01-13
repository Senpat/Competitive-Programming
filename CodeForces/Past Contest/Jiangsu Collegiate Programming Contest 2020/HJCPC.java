//make sure to make new file!
import java.io.*;
import java.util.*;

public class HJCPC{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      int MOD = 128;
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         String[] codes = new String[m];
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
            
            String s = st.nextToken();
            codes[k] = st.nextToken();
         }
         
         char[] array = f.readLine().toCharArray();
         
         boolean[] modded = new boolean[n];
         int[] dp = new int[n];
         for(int k = 0; k < m; k++){
            if(check(codes[k],array,0)){
               dp[codes[k].length()-1]++;
            }
         }
         
         for(int k = 1; k < n; k++){
            //if(dp[k-1] == 0) continue;
            for(int j = 0; j < m; j++){
               if(check(codes[j],array,k)){
                  int i = k+codes[j].length()-1;
                  dp[i] += dp[k-1];
                  modded[i] = modded[i] | modded[k-1];
                  if(dp[i] >= MOD){
                     modded[i] = true;
                     dp[i] = (dp[i] + MOD)%MOD;
                  }
               }
            }
         }
         
         if(!modded[n-1] && dp[n-1] == 1){
            out.println("happymorsecode");
         } else if(!modded[n-1] && dp[n-1] == 0){
            out.println("nonono");
         } else {
            out.println("puppymousecat " + dp[n-1]);
         }
         

      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(String s, char[] array, int i){
      if(i + s.length()-1 >= array.length) return false;
      for(int k = 0; k < s.length(); k++){
         if(array[k+i] != s.charAt(k)) return false;
      }
      return true;
   }
   
      
}