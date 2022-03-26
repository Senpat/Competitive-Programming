//make sure to make new file!
import java.io.*;
import java.util.*;

public class C774{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      long[] fac = new long[16];
      fac[0] = 1L;
      for(int k = 1; k < 16; k++){
         fac[k] = (long)k * fac[k-1];
      }
      
      
      for(int q = 1; q <= t; q++){
      
         long n = Long.parseLong(f.readLine());
      
         int answer = Integer.MAX_VALUE;
         for(int k = 0; k < (1<<15); k++){
            long cur = n;
            int count = 0;
            boolean fail = false;
            for(int j = 3; j <= 14; j++){
               if((k & (1<<j)) != 0){
               //subtract that factorial
                  count++;
                  cur -= fac[j];
               }
               
            }
            if(cur < 0) 
               break;
               
            for(int j = 0; j < 41; j++){
               if((cur & (1L<<j)) != 0){
                  count++;
               }
            }
            
            answer = Math.min(answer,count);
            
         }
      
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
      
}