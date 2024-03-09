//make sure to make new file!
import java.io.*;
import java.util.*;

public class Ccoltype{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      char[] array = f.readLine().toCharArray();
      int n = array.length;
      boolean fail = array[n-1] == 'E';
      for(int k = 1; k < n; k++){
         if(array[k] == 'O' && array[k-1] == 'O'){
            fail = true;
         }
      }
      
      if(fail){
         out.println("INVALID");
         out.close();
         return;
      }
      
      HashSet<Long> pow2 = new HashSet<Long>();
      for(int k = 0; k <= 62; k++){
         pow2.add(1L << k);
      }
      
      long answer = Long.MAX_VALUE;
      for(int p = 4; p <= 62; p+=2){
         long x = (1L << p);
         
         boolean curfail = false;
         for(int k = n-1; k >= 0; k--){
            if(array[k] == 'O'){
               if((x-1L)%3L != 0L){
                  curfail = true;
                  break;
               }
               
               x = (x-1L)/3L;
            } else {
               x <<= 1;
               //overflow
               if(x < 0L){
                  curfail = true;
                  break;
               }
            }
            
            if(pow2.contains(x)){
               curfail = true;
               break;
            }
         }
         
         if(curfail) continue;
         answer = Math.min(answer,x);
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}