//Walking Between Houses
import java.io.*;
import java.util.*;

public class D501{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      long d = Long.parseLong(st.nextToken());
      
      if((n-1)*m < d || d < m){
         System.out.println("NO");
         System.exit(0);
      }
      
      System.out.println("YES");
      
      long cur = 1L;
      
      while(m>0){
         long i = Math.min(n-1,d-m+1);
         cur = step(cur,i);
         m-=1;
         d-=i;
         System.out.print(cur + " ");
      }
      
   }
   public static long step(long c, long x){
      return c - x > 0 ? c-x : c+x;
   }
}
      
      