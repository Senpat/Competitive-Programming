//A Times B
import java.io.*;
import java.util.*;

public class AtimesB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String ain = f.readLine();
      String bin = f.readLine();
      
      if(ain.equals("0") || bin.equals("0")){
         out.println(0);
         out.close();
         return;
      }
      
      int an = ain.length();
      int bn = bin.length();
      
      long[] a = new long[an];
      long[] b = new long[bn];
      
      for(int k = 0; k < an; k++){
         a[k] = (long)Character.getNumericValue(ain.charAt(an-k-1));
      }
      
      for(int k = 0; k < bn; k++){
         b[k] = (long)Character.getNumericValue(bin.charAt(bn-k-1));
      }
      
      
      long[] c = mul(a,b);
      
      long[] c2 = new long[c.length + 15];
      
      for(int k = 0; k < c.length; k++){
         c2[k] = c[k];
      }
      
      for(int k = 0; k < c2.length; k++){
         long x = c2[k];
         c2[k] = x % 10;
         x /= 10;
         int i = 1;
         while(x > 0){
            c2[k+i] += x % 10;
            x/=10;
            i++;
         }
      }
      
      int last = c2.length-1;
      while(c2[last] == 0) last--;
      
      StringJoiner sj = new StringJoiner("");
      for(int k = last; k >= 0; k--){
         sj.add("" + c2[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      out.close();
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