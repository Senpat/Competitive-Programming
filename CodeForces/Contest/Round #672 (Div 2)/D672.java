//make sure to make new file!
import java.io.*;
import java.util.*;

public class D672{

   public static long MOD = 998244353L;
   public static int MAX = 300005;
   public static long[] fac;
   public static long[] ifac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      fac = new long[MAX];
      fac[0] = 1L;
      for(int k = 1; k < MAX; k++){
         fac[k] = (fac[k-1]*(long)k +MOD)%MOD;
      }
      
      ifac = new long[MAX];
      for(int k = 0; k < MAX; k++){
         ifac[k] = modInverse(fac[k],MOD);
      }
      
      
      Lamp[] lamps = new Lamp[n];
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         
         lamps[k] = new Lamp(l,r);
      }
      
      Arrays.sort(lamps);
      PriorityQueue<Lamp> pq = new PriorityQueue<Lamp>(
         new Comparator<Lamp>(){
            public int compare(Lamp l1, Lamp l2){
               return l1.r-l2.r;
            }
         });                  //sort by right
      long answer = 0L;
      for(int k = 0; k < n; k++){
      
         //subtract
         while(!pq.isEmpty() && pq.peek().r < lamps[k].l){
            pq.poll();
         }
      
            //add
         answer = (answer + choose(pq.size(),m-1) + MOD)%MOD;
         pq.add(lamps[k]);
         
         
      }
      
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static long choose(int n, int r){
      if(n < r) 
         return 0L;
      long prod = (fac[n] * ifac[n-r] + MOD)%MOD;
      return (prod * ifac[r] + MOD)%MOD;
   }
   
      //from geeksforgeeks
   public static long modInverse(long a, long m) 
   { 
      long m0 = m; 
      long y = 0, x = 1; 
     
      if (m == 1L) 
         return 0L; 
     
      while (a > 1L) 
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

   
   public static class Lamp implements Comparable<Lamp>{
      int l;
      int r;
      public Lamp(int a, int b){
         l = a;
         r = b;
      }
      public int compareTo(Lamp la){
         return l-la.l;
      }
   }
   
      
}