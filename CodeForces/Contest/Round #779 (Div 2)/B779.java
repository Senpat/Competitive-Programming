//make sure to make new file!
import java.io.*;
import java.util.*;

public class B779{

   public static long MOD = 998244353L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         long n = Long.parseLong(f.readLine());
         
         if(n%2 == 1){
            out.println(0);
            continue;
         }
         
         long fac = 1L;
         
         for(long k = 1L; k <= n/2; k++){
            fac = (fac*k + MOD)%MOD;
         }
         
         long answer = (fac*fac + MOD)%MOD;
         out.println(answer);
         

      }
      
      
      
      
      out.close();
   }
   
      
}