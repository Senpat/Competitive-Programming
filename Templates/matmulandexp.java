//make sure to make new file!
import java.io.*;
import java.util.*;

public class matmulandexp{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static long[][] matmul(long[][] a, long[][] b){
      int len = a.length;
      long[][] ret = new long[len][len];
   
      for(int k = 0; k < len; k++){
         for(int j = 0; j < len; j++){
            for(int h = 0; h < len; h++){
            	//ret[k][j] = (ret[k][j] + a[h][k]*b[j][h] + MOD)%MOD;            //from kotlin template, wrong
               ret[k][j] = (ret[k][j] + a[k][h]*b[h][j] + MOD)%MOD;
            }
         }
      }
      return ret;
   }

   public static long[][] exp(long[][] base, long power){
      if(power == 0){
         //IDENTITY MATRIX OF THE RIGHT SIZE
         return new long[][]{new long[]{1,0},new long[]{0,1}};                
      }
      if(power == 1) 
         return base;
      long[][] ans = exp(base,power/2);
      ans = matmul(ans,ans);
      if(power%2 == 1) ans = matmul(ans, base);
      return ans;
   }
   
      
}