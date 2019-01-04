//make sure to make new file!
import java.io.*;
import java.util.*;

public class B57{

   public static long MOD = 998244353;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String s = f.readLine();
      
      long pre = 0L;
      long sub = 0L;
      while(pre < n && s.charAt((int)pre) == s.charAt(0)){

         pre++;
      }
      long answer = 0;
      if(pre >= n){
         answer = (n*(n+1)/2 + MOD) % MOD;
         
         out.println(answer);
         out.close();
         System.exit(0);
         
      } else {
         int index = n-1;
         while(index >= 0 && s.charAt(index) == s.charAt(n-1)){
            sub++;
            index--;
         }
         
         if(s.charAt(0) == s.charAt(n-1)){
            answer = ((pre + 1) * (sub + 1) + MOD) % MOD;
         } else {
            answer = (1 + pre + sub + MOD) % MOD;
         }
      }
      
      out.println(answer);
      
      
      out.close();
   }
   
}