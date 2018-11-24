//make sure to make new file!
import java.io.*;
import java.util.*;

public class Blyft{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
      
         if(Math.abs(a-b)!=1){
            out.println("NO");
         } else {
            if(isprime(a+b)){
               out.println("YES");
            } else {
               out.println("NO");
            }
            
         }
      
      }
      
      out.close();
   }
   
   public static boolean isprime(long x){
      if(x%2==0) return false;
      for(int k = 3; k < (int)Math.sqrt(x)+1; k+=2){
         if(x%k==0) return false;
      }
      return true;
   }
   
}