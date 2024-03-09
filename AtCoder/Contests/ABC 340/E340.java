//make sure to make new file!
import java.io.*;
import java.util.*;

public class E340{

   public static int n;
   public static long[] bits;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      long[] a = new long[n+1];
      
      bits = new long[n+1];
      for(int k = 1; k <= n; k++){
         a[k] = Long.parseLong(st.nextToken());
         update(k,k,a[k]);
      }
      
      st = new StringTokenizer(f.readLine());
      int[] b = new int[m];
      for(int k = 0; k < m; k++){
         b[k] = Integer.parseInt(st.nextToken()) + 1;
      }
      
      for(int k = 0; k < m; k++){
         long val = psum(b[k]);
         update(b[k],b[k],-val);
         
         int right = n-b[k];
         if((long)right >= val){
            update(b[k]+1,b[k]+(int)val,1L);
         } else {
            update(b[k]+1,n,1L);
            val -= (long)right;
            
            update(1,n,val/(long)n);
            val %= (long)n;
            
            update(1,(int)val,1L);
         }
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 1; k <= n; k++){
         sj.add("" + psum(k));
      }
      
      out.println(sj.toString());
      
      
      
      
      out.close();
   }
   
   
   public static void update(int l, int r, long x){
      if(r < l) return;
      update(l,x);
      if(r+1 <= n) update(r+1,-x);
   }
   
   public static void update(int i, long x){
      for(; i <= n; i += i&-i){
         bits[i] += x;
      }
   }
   
   public static long psum(int i){
      long sum = 0L;
      for(; i > 0; i -= i&-i){
         sum += bits[i];
      }
      return sum;
   }
   
      
}