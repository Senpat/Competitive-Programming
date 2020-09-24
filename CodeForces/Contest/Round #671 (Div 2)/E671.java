//make sure to make new file!
import java.io.*;
import java.util.*;

public class E671{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      ArrayList<Integer> primes = new ArrayList<Integer>();
      
      int MAXP = 100005;
      //sieve from geeksforgeeks
      boolean prime[] = new boolean[MAXP+1]; 
      for(int i=0;i<MAXP;i++) 
         prime[i] = true; 
          
      for(int p = 2; p*p <= MAXP; p++) 
      { 
            // If prime[p] is not changed, then it is a prime 
         if(prime[p] == true) 
         { 
                // Update all multiples of p 
            for(int i = p*p; i <= MAXP; i += p) 
               prime[i] = false; 
         } 
      } 
          
        // Print all prime numbers 
      for(int i = 2; i <= MAXP; i++) 
      { 
         if(prime[i] == true) 
            primes.add(i);
      } 
      
      StringJoiner sb = new StringJoiner("\n");
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         int i = n;
         
         //get prime fac
         ArrayList<Integer> fac = new ArrayList<Integer>();
         ArrayList<Integer> nums = new ArrayList<Integer>();
         
         for(int p : primes){
            int num = 0;
            while(i % p == 0){
               //out.println(p);
               num++;
               i /= p;
            }
            
            if(num > 0){
               fac.add(p);
               nums.add(num);
            }
         }
         
         if(i > 1){
            fac.add(i);
            nums.add(1);
         }
         
         if(fac.size() == 2 && fac.get(0) != fac.get(1) && nums.get(0) == 1 && nums.get(1) == 1){
            sb.add(fac.get(0) + " " + fac.get(1) + " " + n);
            sb.add("1");
            continue;
         }
         
         ArrayList<Integer> answer = new ArrayList<Integer>();
         
         
         int mid = fac.get(0)*fac.get(fac.size()-1);
         
         for(int k = fac.size()-1; k >= 0; k--){
            int curi = exp(fac.get(k),nums.get(k));
            for(int j = nums.get(k); j >= 1; j--){
               int cursize = answer.size();
               for(int h = 0; h < cursize; h++){
                  if(curi*answer.get(h) == mid) continue;
                  answer.add(curi*answer.get(h));
               }
               if(curi != mid)
                  answer.add(curi);     
               curi/=fac.get(k);
            }
         }
         
         answer.add(mid);
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < answer.size(); k++){
            sj.add("" + answer.get(k));
         }
         sb.add(sj.toString());
         sb.add("0");
         
      
      
      }
      
      out.println(sb.toString());
      
      
      
      
      out.close();
   }
   
   public static int exp(int a, int b){
      int ret = 1;
      for(int k = 0; k < b; k++){
         ret *= a;
      }
      return ret;
   }
   
      
}