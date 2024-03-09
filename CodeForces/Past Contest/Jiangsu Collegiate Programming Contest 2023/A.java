//make sure to make new file!
import java.io.*;
import java.util.*;

public class A{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      //get 10^100 mod 26
      int m26 = 1;
      for(int k = 0; k < 100; k++){
         m26 = (m26 * 10 + 26) % 26;
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      char[] s = f.readLine().toCharArray();
      
      while(s.length <= 200000){
         //simulate
         int sn = s.length;
         char[] ns = new char[2*sn];
         for(int k = 0; k < sn/2; k++){
            ns[k] = s[k];
         }
         for(int k = 0; k < sn; k++){
            ns[k+(sn>>1)] = s[k];
         }
         for(int k = sn/2; k < sn; k++){
            ns[k+sn] = next(s[k]);
         }
         
         s = ns;
         
         m26--;
         if(m26 < 0) m26 += 26;
      }
      
      StringJoiner sj = new StringJoiner("");
      for(int k = s.length-m; k < s.length; k++){
         sj.add("" + next(s[k],m26));
      }
      
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static char next(char c){
      return next(c,1);
   }
 
   public static char next(char c, int x){
      c+=x;
      if(c > 'z') c -= 26;
      return c;
   }
}