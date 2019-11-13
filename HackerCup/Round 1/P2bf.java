
import java.io.*;
import java.util.*;

class P2bf{

   public static int MAX = 1000005;
   public static long MOD = 1000000007;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("p2.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("p2.out")));
      
      
      int t = Integer.parseInt(f.readLine());
      
      long[] pow2 = new long[MAX];
      pow2[0] = 1;
      
      for(int k = 1; k < MAX; k++){
         pow2[k] = (pow2[k-1] * 2 + MOD)%MOD;
      }
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         char[] array = f.readLine().toCharArray();
         
               //loop through every possible way from cheapest possible
         char[] c = new char[array.length];
         for(int ii = 0; ii < pow2[n]; ii++){
            int i = ii;
         
            boolean cont = false;
            long answer = 0L;
         
            for(int k = 0; k < n; k++){
               c[k] = array[k];
            }
         
            for(int p = n; p >= 0; p--){
               if(pow2[p] <= i){
               
                  
                  answer = (pow2[p+1] + MOD) % MOD;
                  c[p] = 'A';
                  
                  if(p==1) 
                     break;
                  i-=pow2[p];
               }
            }
            if(cont) 
               continue;
         
            if(check(n,m,c)){
               out.println(answer);
               out.flush();
               break;
            }
         }
      
         
            
            
      
      }
        
        
      out.close();
   }
   public static boolean check(int n, int m, char[] array){
      for(int l = 0; l < n; l++){
         for(int r = l+m; r < n; r++){
            int acount = 0;
            int bcount = 0;
            for(int k = l; k <= r; k++){
               if(array[k] == 'B') bcount++;
               else acount++;
            }
            if(bcount - acount > m) return false;
         }
      }
      
      return true;
   }
   
   
}