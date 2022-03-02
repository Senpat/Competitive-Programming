//make sure to make new file!
import java.io.*;
import java.util.*;

public class A698{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long m = Long.parseLong(st.nextToken());
         
         Long[] array = new Long[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         Arrays.sort(array);
         
         long curgcd = array[1]-array[0];
         for(int k = 2; k < n; k++){
            curgcd = gcd(curgcd,array[k]-array[k-1]);
         }
         
         if((m-array[0])%curgcd == 0){
            out.println("YES");
         } else {
            out.println("NO");
         }

      }
      
      
      
      
      out.close();
   }
   
   public static long gcd(long a, long b){
      if(a > b){
         if(b == 0) return a;
         return gcd(a%b,b);
      } else if(a < b){
         if(a == 0) return b;
         return gcd(b%a,a);
      }
      return a;
   }
   
      
}