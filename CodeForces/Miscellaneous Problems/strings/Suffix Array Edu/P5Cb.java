//make sure to make new file!
import java.io.*;
import java.util.*;
//uses hashing
public class P5Cb{

   public static long[] base = new long[]{29L,31L};
   public static long[] MOD = new long[]{1000000007L,1000000009};
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      String s = f.readLine();
      int n = s.length();
      
      int H = 2;
      
      int N = 400005;
      long[][] pow = new long[N][H];
      long[][] ipow = new long[N][H];
      long[][] hash = new long[n+1][H];
      for(int h = 0; h < H; h++){
         pow[0][h] = 1L;
         ipow[0][h] = 1L;
         for(int k = 1; k < N; k++){
            pow[k][h] = (base[h]*pow[k-1][h] + MOD[h])%MOD[h];
            ipow[k][h] = modInverse(pow[k][h],MOD[h]);
         }
         
         //1 indexed
         hash[0][h] = 0L;
         for(int k = 0; k < n; k++){
            long prod = (((long)s.charAt(k)-'a'+1)*pow[k][h] + MOD[h])%MOD[h];
            hash[k+1][h] = hash[k][h] + prod;
            if(hash[k+1][h] >= MOD[h]) hash[k+1][h] -= MOD[h];
         }
      }
      
      
      
      int t = Integer.parseInt(f.readLine());
      Sub[] array = new Sub[t];
      for(int q = 0; q < t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         array[q] = new Sub(a,b);
      }
      
      Arrays.sort(array,new Comparator<Sub>(){
         public int compare(Sub a, Sub b){
            int an = a.r-a.l+1;
            int bn = b.r-b.l+1;
            //binary search for first character that is not equal
            int l = 0;
            int r = Math.min(an,bn)-1;
            int ans = -1;
            
            while(l <= r){
               int mid = l + (r-l)/2;
               
               boolean fail = false;
               for(int h = 0; h < H; h++){
                  long hasha = hash[a.l+mid][h]-hash[a.l-1][h];
                  if(hasha < 0) hasha += MOD[h];
                  hasha = (hasha * ipow[a.l-1][h])%MOD[h];
                  long hashb = hash[b.l+mid][h]-hash[b.l-1][h];
                  if(hashb < 0) hashb += MOD[h];
                  hashb = (hashb * ipow[b.l-1][h])%MOD[h];
                  
                  if(hasha != hashb){
                     fail = true;
                     break;
                  }
                    
               }
               if(!fail){
                  l = mid+1;
               } else {
                  ans = mid;
                  r = mid-1;
               }
            }
            
            //out.println(a.toString() + " and " + b.toString() + ": " + ans); 
            if(ans == -1){
               if(an == bn) return a.cmp(b);
               return an - bn;  
            } else {
               return s.charAt(a.l + ans -1)-s.charAt(b.l + ans -1);
            }
         }
      });
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < t; k++){
         sj.add((array[k].l) + " " + (array[k].r));
      }
      out.println(sj.toString());
      
      
      out.close();
   }
   
   
   
   public static class Sub{
      int l;
      int r;
      public Sub(int a, int b){
         l = a;
         r = b;
      }
      public int cmp(Sub s){
         if(l == s.l) return r-s.r;
         return l-s.l;
      }
      public String toString(){
         return l + " " + r;
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