//make sure to make new file!
import java.io.*;
import java.util.*;
import java.math.*;

public class C533{
   
   public static long MOD = 1000000007;
   //public static BigInteger[] fac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int l = Integer.parseInt(st.nextToken());
      int r = Integer.parseInt(st.nextToken());
      
      //find number of multiples of 3 in range
      
      int first = l;
      while(first % 3 != 0){
         first++;
      }
         
      int last = r;
      while(last % 3 != 0){
         last--;
      }
   
      long num3;
      
      if(last >= first){
         num3 = (long)((last-first)/n + 1);
      } else {
         num3 = 0L;
      }
      
      
      
      /*
      fac = new BigInteger[100001];
      
      fac[0] = BigInteger.ONE;
      
      for(int k = 1; k < fac.length; k++){
         fac[k] = (fac[k-1].multiply(BigInteger.valueOf(k)));
      }*/
      
      
      
      
      
      
      if(n==1){
         out.println(num3);
      
      } else if(l==r){
         if(n % 3 == 0){
            out.println("1");
         } else {
            out.println("0");
         }
      } else if( r-l < 2){/*
         long answer = 0L;
         
         if(l % 3 == 0){                  //0 1
            BigInteger add = BigInteger.ZERO;
            for (int k = 0; k <= num3; k++){
               add = add.add(choose(n,k*3+2));
            }
            
            answer += add.mod(BigInteger.valueOf(MOD)).longValue();
         } else if( l % 3 == 1){          //1 2
            BigInteger add = BigInteger.ZERO;
            for (int k = 0; k <= num3; k++){
               add = add.add(choose(n,k*3));
            }
            
            answer += add.mod(BigInteger.valueOf(MOD)).longValue();
         }  else if( l % 3 == 1){          //2 3
            BigInteger add = BigInteger.ZERO;
            for (int k = 0; k <= num3; k++){
               add = add.add(choose(n,k*3+1));
            }
            
            answer += add.mod(BigInteger.valueOf(MOD)).longValue();  
         }
         
         out.println((answer + MOD) % MOD);
         
         */
      } else {
         long answer = 1L;
         
         for(int k = 1; k < n; k++){
            answer = (answer * (r-l+1) + MOD) % MOD;
         }
         
         answer = (answer * num3 + MOD) % MOD;
         
         out.println(answer);
      }
            
      
      
      
      
      out.close();
   }
   
   /*
   public static BigInteger choose(int a, int b){
      return fac[a].divide(fac[b].multiply(fac[a-b]));
   }*/
   
}