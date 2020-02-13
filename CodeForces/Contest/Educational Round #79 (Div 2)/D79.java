//make sure to make new file!
import java.io.*;
import java.util.*;

public class D79{
   
   public static long MOD = 998244353;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      ArrayList<ArrayList<Integer>> kids = new ArrayList<ArrayList<Integer>>(n);
      for(int k = 0; k < n; k++) kids.add(new ArrayList<Integer>());
      
      HashMap<Integer,Long> hmap = new HashMap<Integer,Long>();
      int[] ki = new int[n];
      long[] kimodi = new long[n];
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int nn = Integer.parseInt(st.nextToken());
         ki[k] = nn;
         kimodi[k] = modInverse((long)ki[k],MOD);
         for(int j = 0; j < nn; j++){
            int x = Integer.parseInt(st.nextToken());
            kids.get(k).add(x);
            
            if(hmap.containsKey(x)){
               hmap.put(x,hmap.get(x)+1L);
            } else {
               hmap.put(x,1L);
            }
         }
      
      }
      
      
      long answer = modInverse(((long)n * (long)n + MOD) % MOD,MOD);
      long answer2 = 0L;
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < kids.get(k).size(); j++){
            answer2 = (hmap.get(kids.get(k).get(j)) * kimodi[k] + answer2 + MOD) % MOD;
         }
      }
      
      answer = (answer * answer2 + MOD) % MOD;
      out.println(answer);
   
      
      
      
      
      
      out.close();
   }
   
      //from geeksforgeeks
   public static long modInverse(long a, long m) 
   { 
       long m0 = m; 
       long y = 0, x = 1; 
     
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