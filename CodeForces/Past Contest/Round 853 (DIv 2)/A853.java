//make sure to make new file!
import java.io.*;
import java.util.*;

public class A853{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int mingcd = Integer.MAX_VALUE;
         for(int k = 0; k < n; k++){
            for(int j = k+1; j < n; j++){
               mingcd = Math.min(mingcd,gcd(array[k],array[j]));
            }
         }
         
         if(mingcd <= 2){
            out.println("YES");
         } else {
            out.println("NO");
         }
             
      
      

      }
      
      
      
      
      out.close();
   }
   
   public static int gcd(int a, int b){
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