//make sure to make new file!
import java.io.*;
import java.util.*;
//semi-editorial
public class F317{

   public static long MOD = 998244353L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());
      int a1 = Integer.parseInt(st.nextToken());
      int a2 = Integer.parseInt(st.nextToken());
      int a3 = Integer.parseInt(st.nextToken());
      
      long a1l = (long)a1;
      long a2l = (long)a2;
      long a3l = (long)a3;
      
      int[][] tran = new int[][]{{0,0,0},{1,1,0},{1,0,1},{0,1,1}};
      
      int P = 60;
      //dp[p][f1][f2][f3][a1][a2][a3] ->
      //# of ways to do first p bits,
      //fx is if xth value is < n,
      //ax is mod of xth value with xth value in a
      long[][][][][][][] dp = new long[P+2][2][2][2][a1][a2][a3];
      
      dp[P+1][0][0][0][0][0][0] = 1L;
      
      for(int p = P+1; p >= 1; p--){
         long pow = (1L << (p-1));
         boolean bitset = (n & pow) != 0;
         //out.println(bitset);
         for(int f1 = 0; f1 <= 1; f1++){ for(int f2 = 0; f2 <= 1; f2++){ for(int f3 = 0; f3 <= 1; f3++){
         for(int r1 = 0; r1 < a1; r1++){ for(int r2 = 0; r2 < a2; r2++){ for(int r3 = 0; r3 < a3; r3++){
            if(dp[p][f1][f2][f3][r1][r2][r3] == 0L) continue;
            for(int t = 0; t < tran.length; t++){
               if(!bitset){
                  //bit can't be 1 if you are not < n
                  if((f1 == 0 && tran[t][0] == 1) || (f2 == 0 && tran[t][1] == 1) || (f3 == 0 && tran[t][2] == 1)){
                     continue;
                  }
               }
               int newf1 = (f1 == 0 && (tran[t][0] == 1) == bitset) ? 0 : 1;
               int newf2 = (f2 == 0 && (tran[t][1] == 1) == bitset) ? 0 : 1;
               int newf3 = (f3 == 0 && (tran[t][2] == 1) == bitset) ? 0 : 1;
               int newr1 = (int)((pow*(long)tran[t][0] + (long)r1)%a1l);
               int newr2 = (int)((pow*(long)tran[t][1] + (long)r2)%a2l);
               int newr3 = (int)((pow*(long)tran[t][2] + (long)r3)%a3l);
               
               dp[p-1][newf1][newf2][newf3][newr1][newr2][newr3] = (dp[p-1][newf1][newf2][newf3][newr1][newr2][newr3] + dp[p][f1][f2][f3][r1][r2][r3] + MOD)%MOD;
               
            }
         }}}
         }}}
      }
      
      long answer = 0L;
      for(int f1 = 0; f1 <= 1; f1++){ for(int f2 = 0; f2 <= 1; f2++){ for(int f3 = 0; f3 <= 1; f3++){
         answer = (answer + dp[0][f1][f2][f3][0][0][0] + MOD)%MOD;
      }}}
      
      //subtract answers where some values are 0
      long sub = 1L;          //0 0 0 is a valid answer
      sub = (sub + n / lcm(a1l,a2l) + MOD)%MOD;       //x1 == x2, x3 == 0
      sub = (sub + n / lcm(a1l,a3l) + MOD)%MOD;       //etc
      sub = (sub + n / lcm(a2l,a3l) + MOD)%MOD;
      
      answer = ((answer-sub)%MOD + MOD)%MOD;
      
      out.println(answer);
      
      
      
      out.close();
   }
   
   public static long lcm(long a, long b){
      return a*b/gcd(a,b);
   }
   
   public static long gcd(long a, long b){
      if(a > b){
         if(b == 0) return a;
         return gcd(a%b,b);
      } else if(a < b){
         if(a == 0) return b;
         return gcd(b%a,a);
      }
      return a;
   }
   
      
}