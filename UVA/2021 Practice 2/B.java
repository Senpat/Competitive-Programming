//Windblume Ode
import java.io.*;
import java.util.*;

public class B{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         int sum = 0;
         int oddindex = -1;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            sum += array[k];
            if(array[k]%2 == 1) oddindex = k+1;
         }
         
         if(isprime(sum)){
            out.println(n-1);
            for(int k = 1; k <= n; k++){
               if(k != oddindex) out.print(k + " ");
            }
            out.println();
         } else {
            out.println(n);
            for(int k = 1; k <= n; k++){
               out.print(k + " ");
            }
            out.println();
         }
      
         
         
      

      }
      
      
      
      
      out.close();
   }
   
   public static boolean isprime(int x){
      for(int k = 2; k < x; k++) if(x%k == 0) return false;
      return true;
   }  
      
}