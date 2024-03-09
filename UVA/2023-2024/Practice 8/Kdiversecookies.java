//make sure to make new file!
import java.io.*;
import java.util.*;

public class Kdiversecookies{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long n = Long.parseLong(st.nextToken());
      long[] a = new long[3];
      a[0] = Long.parseLong(st.nextToken());
      a[1] = Long.parseLong(st.nextToken());
      a[2] = Long.parseLong(st.nextToken());
      
      Arrays.sort(a);
      
      long answer = a[0] + a[1] + Math.min(a[2],a[0]+a[1]+n);
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}