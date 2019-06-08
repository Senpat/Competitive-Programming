
import java.util.*;
import java.io.*;

public class A66b
{
   public static void main(String args[]) throws Exception
   {
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));  
      StringTokenizer st = new StringTokenizer(f.readLine());
      //long N = Long.parseLong(st.nextToken());
      int T = Integer.parseInt(st.nextToken());
      for(int qw=0; qw < T; qw++)
      {
         st = new StringTokenizer(f.readLine());
         long N = Long.parseLong(st.nextToken());
         long K = Long.parseLong(st.nextToken());
         long res = 0L;
         if(N < K)
         {
            System.out.println(N);
            continue;
         }
         while(N >= K)
         {
            res += N%K;
            res++;
            N /= K;
         }
         System.out.println(res+N);
      }
   }
}