//make sure to make new file!
import java.io.*;
import java.util.*;

public class DTON1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         long n = Long.parseLong(f.readLine());
         
         if(n%2 == 1){
            out.println(2);
         } else{
            long i = n;
            
            int num2 = 0;
            while(i%2 == 0){
               num2++;
               i >>= 1;
            }
            
            //check the odd number
            if(i != 1 && i <= 2000000000 && i*(i+1)/2L <= n){
               out.println(i);
               //check the even number (check if it is 1 << (num2+1))
            } else {
               long check = 1L << (num2+1);
               if(check <= 2000000000 && (check*(check+1)/2L <= n)){
                  out.println(check);
               } else {
                  out.println(-1);
               }
            }
         }

      }
      
      
      
      
      out.close();
   }
   
      
}