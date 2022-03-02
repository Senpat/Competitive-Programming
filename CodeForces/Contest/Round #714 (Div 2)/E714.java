//make sure to make new file!
import java.io.*;
import java.util.*;

public class E714{

   public static long MOD = 1000000007L;
   public static int MAX = 100005;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      long[] fac = new long[MAX];
      long[] ifac = new long[MAX];
      fac[0] = 1L;
      ifac[0] = 1L;
      
      for(int k = 1; k < MAX; k++){
         fac[k] = ((long)k * fac[k-1] + MOD)%MOD;
         ifac[k] = modInverse(fac[k],MOD);
      }
      
      
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      long sum = 0;
      
      HashMap<Long,Integer> freqs = new HashMap<Long,Integer>();
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
         sum += array[k];
         if(freqs.containsKey(array[k])){
            freqs.put(array[k],freqs.get(array[k])+1);
         } else {
            freqs.put(array[k],1);
         }
      }
      
      if(sum%n != 0){
         out.println(0);
         out.close();
         return;
      }
      
      long average = sum/n;
      
      int b = 0;                 //bigger than average
      int s = 0;                 //smaller than average
      int same = 0;              //same as average
      
      for(int k = 0; k < n; k++){
         if(array[k] > average){
            b++;
         } else if(array[k] < average){
            s++;
         } else {
            same++;
         }
      }
      
      if(b <= 1 || s <= 1){
         //any permutation works
         long answer = fac[n];
         for(long i : freqs.keySet()){
            if(freqs.get(i) > 1){
               answer = (answer * ifac[freqs.get(i)] + MOD)%MOD;
            }
         }
         out.println(answer);
      } else {
         //b on one side, s on the other, same anywhere
         long answer = (fac[b] * fac[s] + MOD)%MOD;
         for(long i : freqs.keySet()){
            if(freqs.get(i) > 1 && i != average){
               answer = (answer * ifac[freqs.get(i)] + MOD)%MOD;
            }
         }
         
         //same: (same-1) choose b+s
         long choose = (fac[b+s+same] * ifac[b+s] + MOD)%MOD;
         choose = (choose * ifac[same] + MOD)%MOD;
         
         answer = (answer * choose + MOD)%MOD;
         answer = (answer * 2L + MOD)%MOD;
         
         out.println(answer);
         
         
      
      }
         
         
      
      
      
      
      
      
      
      
      out.close();
   }
   
   //from geeksforgeeks
   public static long modInverse(long a, long m) 
   { 
       long m0 = m; 
       long y = 0L;
       long x = 1L; 
     
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