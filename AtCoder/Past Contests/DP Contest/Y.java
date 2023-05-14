//make sure to make new file!
import java.io.*;
import java.util.*;

public class Y{

   public static long MOD = 1000000007L;
   
   public static long[] fac;
   public static long[] ifac;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 200005;
      fac = new long[N];
      ifac = new long[N];
      fac[0] = 1L;
      for(int k = 1; k < N; k++){
         fac[k] = (fac[k-1] * k + MOD)%MOD;
      }
      
      ifac[N-1] = modInverse(fac[N-1],MOD);
      
      for(int k = N-2; k >= 0; k--){
         ifac[k] = (ifac[k+1] * (k+1) + MOD)%MOD;
      }
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int h = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());
      
      Wall[] walls = new Wall[n];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
      
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         
         walls[k] = new Wall(x,y);
      }
      
      Arrays.sort(walls);
      
      long[] dp = new long[n];         //dp[x] = # of ways to go from 1,1 to that wall without hitting any other walls
      
      for(int k = 0; k < n; k++){
         long path = choose(walls[k].x-1 + walls[k].y-1,walls[k].x-1);
         for(int j = 0; j < k; j++){
            if(walls[k].x >= walls[j].x && walls[k].y >= walls[j].y){
               int dx = walls[k].x - walls[j].x;
               int dy = walls[k].y - walls[j].y;
               long prod = (choose(dx+dy,dx) * dp[j] + MOD)%MOD;
               path = ((path - prod)%MOD + MOD)%MOD;
            }  
         }
         
         dp[k] = path;
      }
      
      long answer = choose(h-1 + w-1,h-1);
      for(int k = 0; k < n; k++){
         int dx = h-walls[k].x;
         int dy = w-walls[k].y;
         long prod = (choose(dx+dy,dx) * dp[k] + MOD)%MOD;
         answer = ((answer - prod)%MOD + MOD)%MOD;
      }
      
      out.println(answer);
      
      
      
      out.close();
   }
   
   public static long choose(int n, int r){
      long prod = (fac[n] * ifac[r] + MOD)%MOD;
      return (prod * ifac[n-r] + MOD)%MOD;
   }
   
   public static class Wall implements Comparable<Wall>{
      int x;
      int y;
      public Wall(int a, int b){
         x = a;
         y = b;
      }
      
      public int compareTo(Wall w){
         if(x == w.x) return y-w.y;
         return x-w.x;
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