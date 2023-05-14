//Rusty String
import java.io.*;
import java.util.*;
//Um episode 11
public class E827{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         f.readLine();

         int n = Integer.parseInt(f.readLine());
         char[] array = f.readLine().toCharArray();
         
         long[] ks = new long[n+1];
         long[] vs = new long[n+1];
         
         for(int k = 1; k <= n; k++){
            if(array[k-1] == 'K') ks[k] = 1L;
            if(array[k-1] == 'V') vs[n-k] = 1L;        //multiple v polynomial by x^n
         }
         
         long[] mul = mul(ks,vs);
         
         boolean[] diffs = new boolean[n];
         for(int k = 1; k <= 2*n; k++){
            if(mul[k] > 0){
               diffs[Math.abs(k-n)] = true;
            }
         }
         
         ArrayList<Integer> answer = new ArrayList<Integer>();
         for(int k = 1; k < n; k++){
            boolean fail = false;
            for(int j = k; j < n; j += k){
               if(diffs[j]){
                  fail = true;
                  break;
               }
            }
            if(!fail){
               answer.add(k);
            }
         }
         answer.add(n);
         
         out.println(answer.size());
         StringJoiner sj = new StringJoiner(" ");
         for(int i : answer){
            sj.add("" + i);
         }
         out.println(sj.toString());
      
      

      }
      
      
      
      
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