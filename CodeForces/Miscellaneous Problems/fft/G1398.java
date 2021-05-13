//Running Competition
import java.io.*;
import java.util.*;

public class G1398{
   
   public static void main(String[] args)throws IOException{
      //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      FastScanner fs = new FastScanner();
      PrintWriter out = new PrintWriter(System.out);
      
      //StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = fs.nextInt();
      int x = fs.nextInt();
      long y = fs.nextLong();
      
      long[] array = new long[x+1];
      long[] array2 = new long[x+1];
      
      //st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n+1; k++){
         int i = fs.nextInt();
         array[i] = 1L;
         array2[x-i] = 1L;
      }
      
      long[] answer = mul(array,array2);
      
      HashSet<Long> hset = new HashSet<Long>();
      
      for(int k = 1; k <= x; k++){
         if(answer[k+x] > 0){
            hset.add(((long)k + y)*2L);
         }
      }
      
      StringJoiner sj = new StringJoiner(" ");
      int t = fs.nextInt();
      //st = new StringTokenizer(f.readLine());
      for(int q = 0; q < t; q++){
         long num = fs.nextLong();
         
         long max = -1;
         long i = 1;
         while(i*i <= num){
            if(num%i == 0){
               if(hset.contains(i)) max = Math.max(max,i);
               if(hset.contains(num/i)){
                  max = Math.max(max,num/i);
                  break;
               }
            }
            i++;
         }
         
         sj.add("" + max);
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
   
   static class FastScanner
   {
      private int BS = 1 << 16;
      private char NC = (char) 0;
      private byte[] buf = new byte[BS];
      private int bId = 0, size = 0;
      private char c = NC;
      private double cnt = 1;
      private BufferedInputStream in;
   
      public FastScanner() {
         in = new BufferedInputStream(System.in, BS);
      }
   
      public FastScanner(String s) {
         try {
            in = new BufferedInputStream(new FileInputStream(new File(s)), BS);
         } catch (Exception e) {
            in = new BufferedInputStream(System.in, BS);
         }
      }
   
      private char getChar() {
         while (bId == size) {
            try {
               size = in.read(buf);
            } catch (Exception e) {
               return NC;
            }
            if (size == -1) 
               return NC;
            bId = 0;
         }
         return (char) buf[bId++];
      }
   
      public int nextInt() {
         return (int) nextLong();
      }
   
      public int[] nextInts(int N) {
         int[] res = new int[N];
         for (int i = 0; i < N; i++) {
            res[i] = (int) nextLong();
         }
         return res;
      }
   
      public long[] nextLongs(int N) {
         long[] res = new long[N];
         for (int i = 0; i < N; i++) {
            res[i] = nextLong();
         }
         return res;
      }
   
      public long nextLong() {
         cnt = 1;
         boolean neg = false;
         if (c == NC) c = getChar();
         for (; (c < '0' || c > '9'); c = getChar()) {
            if (c == '-') neg = true;
         }
         long res = 0;
         for (; c >= '0' && c <= '9'; c = getChar()) {
            res = (res << 3) + (res << 1) + c - '0';
            cnt *= 10;
         }
         return neg ? -res : res;
      }
   
      public double nextDouble() {
         double cur = nextLong();
         return c != '.' ? cur : cur + nextLong() / cnt;
      }
   
      public double[] nextDoubles(int N) {
         double[] res = new double[N];
         for (int i = 0; i < N; i++) {
            res[i] = nextDouble();
         }
         return res;
      }
   
      public String next() {
         StringBuilder res = new StringBuilder();
         while (c <= 32) c = getChar();
         while (c > 32) {
            res.append(c);
            c = getChar();
         }
         return res.toString();
      }
   
      public String nextLine() {
         StringBuilder res = new StringBuilder();
         while (c <= 32) c = getChar();
         while (c != '\n') {
            res.append(c);
            c = getChar();
         }
         return res.toString();
      }
   
      public boolean hasNext() {
         if (c > 32) 
            return true;
         while (true) {
            c = getChar();
            if (c == NC) 
               return false;
            else if (c > 32) 
               return true;
         }
      }
   }
   
   
}