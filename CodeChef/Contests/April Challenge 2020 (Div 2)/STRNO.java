//make sure to make new file!
import java.io.*;
import java.util.*;

public class STRNO{
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int n = Integer.parseInt(st.nextToken());
         int p = Integer.parseInt(st.nextToken());
      
         if(check(n,p)){
            out.println("1");
         } else {
            out.println("0");
         }
         
      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(int n, int p){
      return numfactors(n) >= p;
   }
   
   public static int numfactors(int n){
      if(n == 1) return 0;
      
      int i = 0;
      for(int k = 2; k*k <= n; k++){
         while(n%k == 0){
            i++;
            n/=k;
         }
      }
      
      if(n > 1) i++;
      
      
      return i;
   }
   
}