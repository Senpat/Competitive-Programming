//make sure to make new file!
import java.io.*;
import java.util.*;

public class CGB22{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 205;
      /*int[][] gcd = new int[N][N];
      for(int k = 2; k < N; k++){
         gcd[k][k] = k;
         for(int j = k+1; j < N; j++){
            gcd[k][j] = gcd(k,j);
            gcd[j][k] = gcd[k][j];
         }
      }*/
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[n];
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
         }
         
         boolean[][] mods = new boolean[N][N];
         boolean fail = false;
         
         for(int k = 0; k < n; k++){
            for(int j = k+1; j < n; j++){
               int diff = (int)Math.abs(array[k]-array[j]);
               if(diff == 0){
                  fail = true;
                  break;
               }
               if(diff >= N) continue;
               mods[diff][(int)array[k]%diff] = true;
            }
            
            if(fail) break;
         }
         
         for(int k = 2; k < N; k++){
            boolean found = false;
            for(int j = 0; j < k; j++){
               if(!mods[k][j]){
                  //all divisors also need to be open
                  boolean alldivisors = true;
                  for(int h = 2; h < k; h++){
                     if(k%h != 0) continue;
                     if(mods[h][j%h]){
                        alldivisors = false;
                        break;
                     }
                  }
                  
                  if(alldivisors) found = true;
                  
               }
               
               
            }
            
            if(!found){
               fail = true;
               break;
            }
         } 
      
         if(fail){
            out.println("NO");
         } else {
            out.println("YES");
         }

      }
      
      
      
      
      out.close();
   }
   
   public static int gcd(int a, int b){
      if(a > b){
         if(b == 0) return a;
         return gcd(a%b,b);
      }
      if(a < b){
         if(a == 0) return b;
         return gcd(b%a,a);
      }
      return a;
   }
      
}