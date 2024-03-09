//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1095{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      StringJoiner sj = new StringJoiner("\n");
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         
         long answer = exp(a,b);
         sj.add("" + answer);
      }
      out.println(sj.toString());
      
      
      
      out.close();
   }
   
   public static long exp(long base, long p){
      if(p == 0L) return 1L;
      if(p == 1L) return base;
      if(base == 0L) return 0L;
      
      long ret = exp(base,p/2);
      ret = (ret*ret + MOD)%MOD;
      if(p%2 == 1L) ret = (ret*base + MOD)%MOD;
      return ret;
   }
      
}