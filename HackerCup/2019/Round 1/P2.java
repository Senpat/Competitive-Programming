
import java.io.*;
import java.util.*;

class P2{

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
         
         //find when there are m more B's than A's
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
         
         out.println("Case #" + q + ": " + answer);
            
            
      
      }
        
        
      out.close();
   }
      
}