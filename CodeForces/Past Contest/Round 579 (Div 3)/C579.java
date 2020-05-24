//make sure to make new file!
import java.io.*;
import java.util.*;

public class C579{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      Long[] array = new Long[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      Arrays.sort(array);
      
      long num = 0;
      if(n == 1){
         num = array[0];
      } else {
         num = gcd(array[1],array[0]);
         for(int k = 2; k < n; k++){
            num = gcd(array[k],num);
         }
      
      }
      
      
      
      int answer = numdiv(num);
      out.println(answer);
      

      
      
      
      
      
      out.close();
   }
   
   public static int numdiv(long i){
      int ret = 0;
      for(long k = 1L; k*k <= i; k++){
         if(k*k==i) ret++;
         else if(i%k==0) ret+=2;
      }
      
      return ret;
   }
   
   public static long gcd(long a, long b){
      if(b==0) return a;
      return gcd(b,a%b);
   }
   
      
}