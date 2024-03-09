//make sure to make new file!
import java.io.*;
import java.util.*;

public class E319{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long x = Long.parseLong(st.nextToken());
      long y = Long.parseLong(st.nextToken());
      
      long[] p = new long[n-1];
      long[] t = new long[n-1];
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         
         p[k] = Long.parseLong(st.nextToken());
         t[k] = Long.parseLong(st.nextToken());
      }
      
      int N = 840;
      long[] add = new long[N];
      for(int start = 0; start < N; start++){
         long startl = start;
         
         startl += x;
         for(int k = 0; k < n-1; k++){
            startl = (startl+p[k]-1)/p[k]*p[k];
            startl += t[k];
         }
         startl += y;
         
         add[start] = startl-(long)start;
      }
      
      int q = Integer.parseInt(f.readLine());
      for(int qq = 0; qq < q; qq++){
         long i = Long.parseLong(f.readLine());
         
         long answer = i + add[(int)i%N];
         out.println(answer);
      }
      
      out.close();
   }
   
      
}