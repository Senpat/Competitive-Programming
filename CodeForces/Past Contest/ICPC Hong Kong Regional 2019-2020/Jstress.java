//make sure to make new file!
import java.io.*;
import java.util.*;

public class Jstress{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      /*
      for(int min = 10; min <= 1000; min++){
         for(int max = min; max <= 1000; max++){
            for(int mod = 2; mod <= 60; mod++){
               //System.out.println(mod + ": ");
               bf1(min,max,mod);
            }
         }
      }*/
      
      //bf1(10,50,2);
      
      for(int k = 10; k <= 50; k++){
         bf1(k,k,2);
      }
      
      
      
      out.close();
   }
   
   public static void bf1(int min, int max, int mod){
      
      long ans = 0L;
      for(int num = min; num <= max; num++){
         long calc = calc(num);
         if(calc%mod == num%mod) ans++;
      }
      
      long solve = solve(""+min,""+max,mod);
      System.out.println(ans);
      if(ans != solve){
         System.out.println(min + " " + max + " " + mod);
         System.out.println(ans + " " + solve);
      }
   }
   
   public static long calc(int x){
      ArrayList<Integer> digits = new ArrayList<Integer>();
      while(x > 0){
         digits.add(x%10);
         x /= 10;
      }
      int d = digits.size();
      
      long answer = 0L;
      for(int k = 0; k < d; k++){
         for(int j = k+1; j < d; j++){
            answer += (long)(digits.get(k) * digits.get(j));
         }
      }
      
      return answer;
   }
   
   public static long solve(String ls, String rs, int mod){
      char[] lin = ls.toCharArray();
      char[] rin = rs.toCharArray();
         
      int n = rin.length;
         
      int[] l = new int[n];
      int[] r = new int[n];
      for(int k = 0; k < n; k++){
         if(k+lin.length < n) l[k] = 0;
         else l[k] = lin[k-(n-lin.length)]-'0';
         //out.println(l[k]);
         r[k] = rin[k]-'0';
      }
         
      //int mod = Integer.parseInt(f.readLine());
      
      int[] pow10 = new int[n];
      pow10[n-1] = 1;
      for(int k = n-2; k >= 0; k--){
         pow10[k] = mul(10,pow10[k+1],mod);
      }
      
      //# digit, sum of digits mod m, f(x)-x mod m, is min, is max
      long[][][][][] dp = new long[n][mod][mod][2][2];
         
      if(r[0] == l[0]){
         dp[0][r[0]%mod][sub(0,mul(pow10[0],r[0],mod),mod)][1][1] = 1L;
      } else {
         dp[0][l[0]%mod][sub(0,mul(pow10[0],l[0],mod),mod)][1][0] = 1L;
         dp[0][r[0]%mod][sub(0,mul(pow10[0],r[0],mod),mod)][0][1] = 1L;
         for(int k = l[0]+1; k <= r[0]-1; k++){
            dp[0][k%mod][sub(0,mul(pow10[0],k,mod),mod)][0][0] = 1L;
         }
      }
         
      for(int d = 0; d < n-1; d++){
         for(int s = 0; s < mod; s++){
            for(int m = 0; m < mod; m++){
               for(int x = 0; x <= 9; x++){
                  int ns = (s+x)%mod;
                  int nm = ((m+s*x - pow10[d+1]*x)%mod + mod)%mod;
                  dp[d+1][ns][nm][0][0] = add(dp[d+1][ns][nm][0][0],dp[d][s][m][0][0]);
                     
                  //is min
                  if(x == l[d+1]){
                     dp[d+1][ns][nm][1][0] = add(dp[d+1][ns][nm][1][0],dp[d][s][m][1][0]);
                     if(l[d+1] != r[d+1]){
                        dp[d+1][ns][nm][1][0] = add(dp[d+1][ns][nm][1][0],dp[d][s][m][1][1]);
                     }
                  } else if(x > l[d+1]){
                     dp[d+1][ns][nm][0][0] = add(dp[d+1][ns][nm][0][0],dp[d][s][m][1][0]);   
                  }
                     
                  //is max
                  if(x == r[d+1]){
                     dp[d+1][ns][nm][0][1] = add(dp[d+1][ns][nm][0][1],dp[d][s][m][0][1]);
                     if(l[d+1] != r[d+1]){
                        dp[d+1][ns][nm][0][1] = add(dp[d+1][ns][nm][0][1],dp[d][s][m][1][1]);
                     }
                  } else if(x < r[d+1]){
                     dp[d+1][ns][nm][0][0] = add(dp[d+1][ns][nm][0][0],dp[d][s][m][0][1]);
                  }
                     
                  if(x > l[d+1] && x < r[d+1]){
                     dp[d+1][ns][nm][0][0] = add(dp[d+1][ns][nm][0][0],dp[d][s][m][1][1]);
                  }
                     
                  if(l[d+1] == r[d+1] && x == l[d+1]){
                     dp[d+1][ns][nm][1][1] = add(dp[d+1][ns][nm][1][1],dp[d][s][m][1][1]);
                  }
               }
            }
         }
            
            
      }
         
      long answer = 0L;
      for(int s = 0; s < mod; s++){
         answer = add(answer,dp[n-1][s][0][0][0]);
         answer = add(answer,dp[n-1][s][0][1][0]);
         answer = add(answer,dp[n-1][s][0][0][1]);
         answer = add(answer,dp[n-1][s][0][1][1]);
      }
      
      return answer;
   
   }
   
   
   public static int add(int a, int b, int mod){
      int ret = a+b;
      if(ret >= mod) ret -= mod;
      return ret;
   }
   
   public static long add(long a, long b){
      long ret = a+b;
      if(ret >= MOD) ret -= MOD;
      return ret;
   }
   
   public static int sub(int a, int b, int mod){
      int ret = a-b;
      if(ret < 0) ret += mod;
      return ret;
   }
   
   public static int mul(int a, int b, int mod){
      return (a*b + mod)%mod;
   }
      
}