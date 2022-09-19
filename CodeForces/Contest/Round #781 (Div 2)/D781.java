//make sure to make new file!
import java.io.*;
import java.util.*;

public class D781{

   public static BufferedReader f;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int answer = 0;
         for(int i = 1; i <= 29; i++){
            //check (1 << i)
            int a = (1 << i) - answer;
            int b = (1 << i)*2 - answer;
            
            int gcd = query(a,b);
            
            if(gcd != (1 << i)){
               answer += (1 << (i-1));
            }
         }
         
         //check if you should add 2^29
         int a = 3-(answer%3);
         int b = a + 3;
         int last = query(a,b);
         if(last != 3){
            answer += (1 << 29);
         }
         
         out.println("! " + answer);
         out.flush();
      

      }
      
      
      
      
      out.close();
   }
   
   public static int query(int a, int b) throws IOException{
      out.println("? " + a + " " + b);
      out.flush();
      
      return Integer.parseInt(f.readLine());
   }
   
      
}