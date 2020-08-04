//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2test{
   
   

   public static int MAX = 1000005;
   public static long MOD = 1000000007;
   public static long[] pow2;
   public static int N = 20;
   
   public static void main(String[] args)throws IOException{
      PrintWriter out = new PrintWriter(System.out);
      pow2 = new long[MAX];
      pow2[0] = 1;
      
      for(int k = 1; k < MAX; k++){
         pow2[k] = (pow2[k-1] * 2 + MOD)%MOD;
      }
      
      
      //loop through every possible value configuration, and value of K
      for(int nn = 0; nn <= N; nn++){
         System.out.println(nn);
         for(int ii = 0; ii < (int)pow2[nn]; ii++){
            char[] c = new char[(int)pow2[nn]];
            Arrays.fill(c,'B');
            int i = ii;
            for(int p = N; p >= 0; p--){
               if((int)pow2[p] <= i){
                  
                  c[p] = 'A';
                  if(p==1) 
                     break;
                  i-=pow2[p];
               }
            }
            int n = (int)pow2[nn];
            for(int m = 0; m < n; m++){
               long myc = mycode(n,m,c);
               long bfc = bf(n,m,c);
               if(myc != bfc){
                  out.println("DIFFERING ANSWER");
                  out.println(n + " " + m);
                  out.println(c);
               }
            }
         }
      }
   
      
      
      
      
      out.close();
   }
   
   
   public static long bf(int n, int m, char[] array){
      
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
            return answer;
         }
      }
      return -1;
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
   
   
   
   public static long mycode(int n, int m, char[] array){
      int count = 0;
      long answer = 0L;
      for(int k = n-1; k >= 0; k--){
            
         if(array[k] == 'B') count++;
         else count--;
            
         if(count > m){
            answer = (answer + pow2[k+1] + MOD)%MOD;
            count -= 2;
         }
            
         count = Math.max(0,count);
            
      }
      
      return answer;
   }
      
   
}