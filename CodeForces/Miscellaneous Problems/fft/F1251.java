//Red White Fence
import java.io.*;
import java.util.*;

public class F1251{

   public static long MOD = 998244353;

   public static long[] fac;
   public static long[] ifac;

   public static void main(String[] args)throws IOException{
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 300005;
      
      fac = new long[N];
      ifac = new long[N];
      fac[0] = 1L;
      ifac[0] = 1L;
      
      long[] pow2 = new long[N];
      pow2[0] = 1L;
      
      for(int k = 1; k < N; k++){
         fac[k] = ((long)k * fac[k-1] + MOD)%MOD;
         pow2[k] = (2L * pow2[k-1] + MOD)%MOD;
      }
      
      ifac[N-1] = exp(fac[N-1],MOD-2);
      
      for(int k = N-2; k >= 1; k--){
         ifac[k] = ((long)(k+1) * ifac[k+1] + MOD)%MOD;
      }
      
      int n = fs.nextInt();
      int rn = fs.nextInt();
      
      int[] whites = fs.nextInts(n);
      int[] reds = fs.nextInts(rn);
      
      int qn = fs.nextInt();
      int[] q = fs.nextInts(qn);
      
      int[] wfreq = new int[N];
      for(int k = 0; k < n; k++){
         wfreq[whites[k]]++;
      }
      
      long[] answer = new long[qn];
      
      //for each red
      for(int r = 0; r < rn; r++){
         int x1 = 0;                //num of freq==1 -> (1+2x)
         int x2 = 0;                //num of freq>=2 -> (1+x)^2
         
         for(int k = 1; k < reds[r]; k++){
            if(wfreq[k] == 1) x1++;
            if(wfreq[k] >= 2) x2++;
         }
         
         //make generating functions
         //(1+2x)^x1
         long[] a = new long[x1+1];
         for(int k = 0; k <= x1; k++){
            a[k] = (pow2[k] * choose(x1,k) + MOD)%MOD; 
         }
         
         //(1+2x+x^2)^x2 = (1+x)^(2*x2)
         long[] b = new long[2*x2+1];
         for(int k = 0; k <= 2*x2; k++){
            b[k] = choose(2*x2,k);
         }
         
         long[] c = mul(a,b);
         
         for(int qi = 0; qi < qn; qi++){
            int numboards = q[qi]/2 - reds[r] - 1;
            if(numboards >= 0 && numboards < c.length){
               answer[qi] = (answer[qi] + c[numboards] + MOD)%MOD;
            }
         }
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < qn; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      out.close();
   }
   
   //fft
   public static final long p=998_244_353;
   public static final long g=3;
   
   public static long ADD(long a, long b){
      return a+b>=p?a+b-p:a+b;
   }
   
   public static long SUB(long a, long b){
      return ADD(a,p-b);
   }
   
   public static long pow(long a, long n){
      long ret=1;
      for(;n>0;n/=2,a=a*a%p){
         if(n%2 == 1) ret = ret*a%p;
      }
      return ret;
   }
   
   public static long inv(long a){
      return pow(a,p-2);
   }
   
   public static void fft(long[] a, long root){
      int n = a.length;
      {
         int cur = 0;
         for(int i = 0; i < n; i++){
            if(i < cur){
               a[i]^=a[cur];a[cur]^=a[i];a[i]^=a[cur];
            }
            for(int k = (n>>1);k > (cur^=k); k >>= 1);
         }
      }
      
      for(int s = 1; s <= n/2; s *= 2){
         long mul = pow(root,n/(2*s));
         for(int i = 0; i < n; i += 2*s){
            long x = 1;
            for(int j = 0; j < s; j++){
               long A = a[i+j];
               long B = a[i+j+s]*x%p;
               x = x*mul%p;
               a[i+j]=ADD(A,B);
               a[i+j+s]=SUB(A,B);
            }
         }
      }
   }
   
   public static long[] mul(long[] a, long[] b){
      int n = 1;
      while(n < a.length+b.length-1) n*=2;
      a = Arrays.copyOf(a,n);
      b = Arrays.copyOf(b,n);
      long root = pow(g,(p-1)/n);
      fft(a,root);
      fft(b,root);
      long in = inv(n);
      for(int i = 0; i < n; i++) a[i] = a[i]*b[i]%p * in%p;
      fft(a,inv(root));
      return a;
   }
   
   
   public static long choose(int n, int r){
      long prod = (fac[n] * ifac[r] + MOD)%MOD;
      return (prod * ifac[n-r] + MOD)%MOD;
   }
   
   public static long exp(long base, long power){
      if(power == 0L) return 1L;
      if(power == 1L) return base;
      long ans = exp(base,power/2L);
      ans = (ans*ans+MOD)%MOD;
      if(power%2 == 1L) ans = (ans*base + MOD)%MOD;
      return ans;
   }



}

class FastScanner{

   private int BS = 1<<16;
   private char NC = (char)0;
   private byte[] buf = new byte[BS];
   private int bId = 0, size = 0;
   private char c = NC;
   private double cnt = 1;
   private BufferedInputStream in;
   
   public FastScanner(){
      in = new BufferedInputStream(System.in,BS);
   }
   
   private char getChar(){
      while(bId == size){
         try{
            size = in.read(buf);
         } catch (Exception e){
            return NC;
         }
         if(size == -1) return NC;
         bId = 0;
      }
      
      return (char)buf[bId++];
   }
   
   public int nextInt(){
      return (int)nextLong();
   }
   
   public int[] nextInts(int N){
      int[] res = new int[N];
      for(int i = 0; i < N; i++){
         res[i] = (int)nextLong();
      }
      return res;
   }
   
   public long nextLong(){
      cnt = 1;
      boolean neg = false;
      if(c == NC) c = getChar();
      for(; (c < '0' || c > '9'); c = getChar()){
         if (c == '-') neg = true;
      }
      long res = 0;
      for(; (c >= '0' && c <= '9'); c = getChar()){
         res = (res << 3) + (res << 1) + c - '0';
         cnt *= 10;
      }
      return neg ? -res : res;
   }


}