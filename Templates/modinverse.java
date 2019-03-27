//make sure to make new file!
import java.io.*;
import java.util.*;

public class modinverse{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      

      
      
      
      
      out.close();
   }
   
   //from geeksforgeeks
   public static int modInverse(int a, int m) 
   { 
       int m0 = m; 
       int y = 0, x = 1; 
     
       if (m == 1) 
         return 0; 
     
       while (a > 1) 
       { 
           // q is quotient 
           int q = a / m; 
           int t = m; 
     
           // m is remainder now, process same as 
           // Euclid's algo 
           m = a % m, a = t; 
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