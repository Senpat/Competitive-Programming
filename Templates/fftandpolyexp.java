//make sure to make new file!
import java.io.*;
import java.util.*;

public class fftandpolyexp{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      long[] a = new long[n];
      long[] b = new long[m];
      
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         a[k] = Long.parseLong(st.nextToken());
      }
      
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < m; k++){
         b[k] = Long.parseLong(st.nextToken());
      }
      
      long[] c = mul(a,b);
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n+m-1; k++){
         sj.add(""+c[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static long[] polyexp(long[] array, int power){
      if(power == 0) 
         return new long[]{1L};
      if(power == 1) 
         return array;
      
      long[] ans = new long[]{1L};
      int p = power;
      while(p > 0){
         if(p%2 == 1) ans = mul(ans,array);
         p >>= 1;
         array = mul(array,array);
      }
      
      
      return ans;
   }
   
   //FFT TEMPLATE
   //from https://judge.yosupo.jp/submission/8499
   public static final long p=998_244_353;                       //mod
   public static final long g=3;                                 //primitive root
	
   public static long ADD(long a,long b) {
      return a+b>=p?a+b-p:a+b;
   }
	
   public static long SUB(long a,long b) {
      return ADD(a,p-b);
   }
	
   public static long pow(long a,long n) {
      long ret=1;
      for (;n>0;n/=2,a=a*a%p) 
         if(n%2==1) ret=ret*a%p;
      return ret;
   }
	
   public static long inv(long a) {
      return pow(a,p-2);
   }
	
   public static void fft(long[] a,long root) {
      int n=a.length;
      {
         int cur=0;
         for (int i=0;i<n;++i) {
            if (i<cur) {
               a[i]^=a[cur];a[cur]^=a[i];a[i]^=a[cur];
            }
            for (int k=n>>1;k>(cur^=k);k>>=1) ;
         }
      }
   	
      for (int s=1;s<=n/2;s*=2) {
         long mul=pow(root,n/(2*s));
         for (int i=0;i<n;i+=2*s) {
            long x=1;
            for (int j=0;j<s;++j) {
               long A=a[i+j];
               long B=a[i+j+s]*x%p;
               x=x*mul%p;
               a[i+j]=ADD(A,B);
               a[i+j+s]=SUB(A,B);
            }
         }
      }
   }
	
   public static long[] mul(long[] a,long[] b) {
      int n=1;
      while (n<a.length+b.length-1) n*=2;
      a=Arrays.copyOf(a, n);
      b=Arrays.copyOf(b, n);
      long root=pow(g,(p-1)/n);
      fft(a,root);
      fft(b,root);
      long in=inv(n);
      for (int i=0;i<n;++i) a[i]=a[i]*b[i]%p*in%p;
      fft(a,inv(root));
      return a;
   }
      
}