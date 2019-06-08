//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class E560{
   
   public static long MOD = 998244353;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      long[] a = new long[n];
      long[] b = new long[n];
      
      Num[] an = new Num[n];
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         a[k] = Long.parseLong(st.nextToken());
         an[k] = new Num(a[k],k);
      }
      
      Arrays.sort(an);
         
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++) b[k] = Long.parseLong(st.nextToken());
      
      shuffleArray(b);
      Arrays.sort(b);
      
      long answer = 0;
      for(int k = 0; k < n; k++){
         long cursum = 1;
         cursum = (cursum * an[k].n     + MOD)%MOD;
         cursum = (cursum * b[n-k-1]    + MOD)%MOD;
         cursum = (cursum * (n-an[k].i) + MOD)%MOD;
         cursum = (cursum * (an[k].i+1) + MOD)%MOD;
         answer = (answer + cursum + MOD) % MOD;
      }
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static void shuffleArray(long[] arr){
        int n = arr.length;
        Random rnd = new Random();
        for(int i=0; i<n; ++i){
            long tmp = arr[i];
            int randomPos = i + rnd.nextInt(n-i);
            arr[i] = arr[randomPos];
            arr[randomPos] = tmp;
        }   
   }
   
   public static class Num implements Comparable<Num>{
      long n;
      int i;
      public Num(long a, int b){
         n = a;
         i = b;
      }
      
      public int compareTo(Num N){
         if(n>N.n) return 1;
         else if(n==N.n) return 0;
         return -1;
      }
   }
      
}