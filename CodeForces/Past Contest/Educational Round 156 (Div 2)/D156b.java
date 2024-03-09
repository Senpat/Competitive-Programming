//make sure to make new file!
import java.io.*;
import java.util.*;

public class D156b{
   
   public static long MOD = 998244353L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      char[] array = f.readLine().toCharArray();
      
      long[] answer = new long[q+1];
      
      long cur = 1L;
      for(int k = 1; k < n-1; k++){
         if(array[k] == '?'){
            cur = (cur * (long)k + MOD)%MOD;
         }
      }
      
      if(array[0] == '?') answer[0] = 0L;
      else answer[0] = cur;
      
      for(int t = 1; t <= q; t++){
         st = new StringTokenizer(f.readLine());
         
         int i = Integer.parseInt(st.nextToken())-1;
         long il = (long)i;
         char c = st.nextToken().charAt(0);
         
         if(i != 0){
            if(array[i] == '?') cur = (cur * modInverse(il,MOD) + MOD)%MOD;
            if(c == '?') cur = (cur * il + MOD)%MOD; 
         }
         
         array[i] = c;
         
         if(array[0] == '?') answer[t] = 0L;
         else answer[t] = cur;
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k <= q; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
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