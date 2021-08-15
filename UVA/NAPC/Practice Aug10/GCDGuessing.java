//GCD Guessing Game
import java.io.*;
import java.util.*;

public class GCDGuessing{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      //get biggest multiple <= n of every prime <= n
      
      HashSet<Integer> hset = new HashSet<Integer>();
      
      for(int k = 2; k <= n; k++){
         if(isPrime(k)){
            int i = k;
            while(i*k <= n){
               i *= k;
            }
            hset.add(i);
         }
      }
      
      out.println(hset.size());
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean isPrime(int x){
      for(int i = 2; i*i <= x; i++){
         if(x % i == 0) return false;
      }
      return true;
   }
}