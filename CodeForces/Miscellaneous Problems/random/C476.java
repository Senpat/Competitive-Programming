//make sure to make new file!
import java.io.*;
import java.util.*;

public class C476{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      long a = Long.parseLong(st.nextToken());
      long b = Long.parseLong(st.nextToken());
      
      long MOD = 1000000007L;
      
      long a1 = (b*sum(b-1)+MOD)%MOD;
      long a2 = (a1*sum(a)+MOD)%MOD;
      long a3 = (a*sum(b-1)+MOD)%MOD;
      long answer = (a2+a3+MOD)%MOD;
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static long sum(long x){
      long prod1 = (x*(x+1) +  MOD)%MOD;
      return (prod1*500000004L+MOD)%MOD;
   }
      
}