//make sure to make new file!
import java.io.*;
import java.util.*;
//bug: multiplying by numbers as big as n (10^18) without modding
public class C896{

   public static int L = 130;
   public static int L2 = 61;
   public static long MOD = 998244353L;
   public static long i2 = 499122177L;
   
   public static long[] pow2;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int PN = 1000;
      pow2 = new long[PN];
      pow2[0] = 1L;
      for(int k = 1; k < PN; k++){
         pow2[k] = (pow2[k-1] * 2L + MOD)%MOD;
      }
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long n = Long.parseLong(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         long[] numpath = new long[L];         //numpaths[x] stores the # of paths of length x
         
         int d = 0;
         while((1L << (d+1))-1 <= n) d++;
         
         //calculate numpaths for between nodes of the complete graph
         
         for(int p = 1; p < L; p++){                     //pathlen
            for(int start = 0; start < d; start++){      //starting depth
               for(int d1 = 0; d1 <= p-1; d1++){
                  int d2 = p-1-d1;
                  
                  if(start+d1 >= d || start+d2 >= d) continue;
                  
                  long prod = pow2[Math.max(0,d1-1) + Math.max(0,d2-1) + start];
                  numpath[p] += prod;
                  if(numpath[p] >= MOD) numpath[p] -= MOD;
                  //out.println(p + " " + start + " " + d1 + " " + d2 + " " + prod*freq);
               }
            }
         }
         //out.println("done complete");
         //out.flush();
         
         //calculate for the extra nodes
         //extra nodes are at depth d
         long extra = n-((1L << d)-1);
         
         for(int i = L2; i >= 0; i--){
            if((extra & (1L << i)) == 0L) continue;
            long cur = (1L << i);
            long curmod = cur%MOD;
            long rem = extra - cur;
            long remmod = rem%MOD;
            //out.println("cur: " + cur + ", rem: " + rem);
            
            //cur to nodes in complete graph
            for(int dan = 0; dan < d; dan++){
               for(int j = dan; j < d; j++){
                  int pathlen = d+1-dan + (j-dan);
                  long prod = (curmod * pow2[Math.max(0,j-dan-1)] + MOD)%MOD;
                  numpath[pathlen] += prod;
                  if(numpath[pathlen] >= MOD) numpath[pathlen] -= MOD;
                  //out.println("cur to complete: " + pathlen + " " + dan + " " + j + " " + pow2[Math.max(0,j-dan-1)]);
               }
            }
            
            //cur to other cur
            int curp = 0;
            while((1L << curp) <= cur){
               long prod = (curmod * pow2[Math.max(0,curp-1)] + MOD)%MOD;
               if(curp > 0){
                  prod = (prod * i2 + MOD)%MOD;
               }
               numpath[2*curp+1] += prod;
               if(numpath[2*curp+1] >= MOD) numpath[2*curp+1] -= MOD;
               //out.println("cur to other cur: " + (2*curp+1) + " " + pow2[Math.max(0,curp-1)]);
               curp++;
            }
            
            //cur to rem
            long prod = (curmod * remmod + MOD)%MOD;
            numpath[2*curp+1] += prod;
            if(numpath[2*curp+1] >= MOD) numpath[2*curp+1] -= MOD;
            //out.println("cur to rem: " + (2*curp+1) + " " + rem);
            
            extra = rem;
         }
         //out.println("done extra");
         //out.flush();
         
         /*
         for(int k = 0; k < L; k++){
            out.print(numpath[k] + " ");
         }
         */
         
         long answer = 0L;
         long mexp = exp((long)m,n);
         long mi = exp((long)m,MOD-2);
         
         long[][] jexp = new long[L][m+1];
         for(int j = 1; j <= m; j++){
            jexp[0][j] = 1L;
            for(int k = 1; k < L; k++){
               jexp[k][j] = (jexp[k-1][j]*(long)j + MOD)%MOD;
            }
         }
         
         //out.println("done calc jexp");
         //out.flush();
         for(int k = 1; k < L; k++){
            mexp = (mexp * mi + MOD)%MOD;
            for(long j = 1; j <= (long)m; j++){
               //# of ways that max is j
               //nodes on path <= j
               long inpath = jexp[k][(int)j];      //exp(j,k)
               //at least one value is j
               inpath -= jexp[k][(int)(j-1)];            //exp(j-1,k)
               if(inpath < 0) inpath += MOD;
               
               //ways to fill other nodes
               long outpath = mexp;
               
               long prod = (inpath * outpath + MOD)%MOD;
               prod = (prod * numpath[k] + MOD)%MOD;
               prod = (prod * j + MOD)%MOD;
               answer += prod;
               if(answer >= MOD) answer -= MOD;
            }
         }
         
         out.println(answer);
         //out.flush();
      }
      
      
      
      
      
      out.close();
   }
   
   public static long exp(long base, long power){
      if(power == 0) return 1;
      if(power == 1) return (base + MOD) % MOD;
      long ans = exp(base,power/2);
      ans = (ans*ans + MOD) % MOD;
      if(power%2 == 1) ans = (ans*base + MOD) % MOD;
      return (ans + MOD) % MOD;
   }
   
      
}