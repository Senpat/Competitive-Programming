//make sure to make new file!
import java.io.*;
import java.util.*;

public class D833{
   
   public static long p30 = 1073741824L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         long d = Long.parseLong(st.nextToken());
         
         long A = p30;
         long B = a|b;
         
         boolean fail = false;
         while(d % 2 == 0){
            if(A % 2 == 1 || B % 2 == 1){
               fail = true;
               break;
            }
            A >>= 1;
            B >>= 1;
            d >>= 1;
         }
         
         if(fail){
            out.println(-1);
            continue;
         }
         
         if(d == 1){
            out.println(0);
            continue;
         }
         
         A %= d;
         B %= d;
         
         if(A == 0){
            out.println(-1);
            continue;
         }
         
         //x * A + B = 0 mod d
         long x = ((d-B) * modInverse(A,d) + d) % d;
         
         long answer = x * p30 + a|b;
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
        //from geeksforgeeks
   public static long modInverse(long a, long m) 
   { 
       long m0 = m; 
       long y = 0;
       long x = 1; 
     
       if (m == 1) 
         return 0; 
     
       while (a > 1) 
       { 
           // q is quotient 
           long q = a / m; 
           long t = m; 
     
           // m is remainder now, process same as 
           // Euclid's algo 
           m = a % m;
           a = t; 
           t = y; 
     
           // Update y and x 
           y = x - q * y; 
           x = t; 
       } 
     
       // Make x positive 
       if (x < 0) 
          x += m0; 
       return x; 
   }  

   
      
}