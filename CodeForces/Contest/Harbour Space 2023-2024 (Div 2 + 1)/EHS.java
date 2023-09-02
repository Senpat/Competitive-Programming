//make sure to make new file!
import java.io.*;
import java.util.*;

public class EHS{
   
   public static long MOD = 998244353L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         Integer[] array = new Integer[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         Arrays.sort(array);
         
         Trie head = new Trie();
         
         long answer = 0L;
         for(int k = 0; k < n; k++){
            Trie cur = head;
            
            long ones = 0L;
            for(int i = 30; i >= 0; i--){
               if((array[k] & (1 << i)) != 0){
                  if(cur.children[0] != null){
                     answer = (answer + (cur.children[0].num * (ones+1)) + MOD)%MOD;
                     answer = (answer + (cur.children[0].num * (ones+2)) + MOD)%MOD;
                  }
                  if(cur.children[1] == null){
                     cur.children[1] = new Trie();
                  }
                  cur = cur.children[1];
                  
                  ones++;
               } else {
                  if(cur.children[1] != null){
                     answer = (answer + (cur.children[1].num * (ones+1)) + MOD)%MOD;
                     answer = (answer + (cur.children[1].num * (ones+2)) + MOD)%MOD;
                  }
                  if(cur.children[0] == null){
                     cur.children[0] = new Trie();
                  }
                  cur = cur.children[0];
               }
               
               cur.num++;
            }
            
            //pick itself twice   
            answer = (answer + ones+1 + MOD)%MOD;
            
            //equal
            answer = (answer + 2L * (cur.num-1) * (ones+1) + MOD)%MOD;
         
            
            //out.println(answer);
         }
         
         
         long nl = (long)n;
         long n2 = (nl*nl + MOD)%MOD;
         answer = (answer * modInverse(n2,MOD) + MOD)%MOD;
         out.println(answer);
         
      

      }
      
      
      
      
      out.close();
   }
   
   public static class Trie{
      Trie[] children;
      long num;
      
      public Trie(){
         children = new Trie[2];
         num = 0L;
      }
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