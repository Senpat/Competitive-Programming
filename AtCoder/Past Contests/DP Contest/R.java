//make sure to make new file!
import java.io.*;
import java.util.*;

public class R{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      long[][] matrix = new long[n][n];
      long[][] answer = new long[n][n];
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < n; j++){
            matrix[k][j] = Long.parseLong(st.nextToken());
         }
         answer[k][k] = 1L;
      }
      
      //calculate matrix ^ m
      int P = 62;
      for(int p = 0; p < P; p++){
         if((m & (1L << p)) > 0){
            answer = mul(matrix,answer);  
         }
         matrix = mul(matrix,matrix);
      }
      
      long sum = 0L;
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            sum = (sum + answer[k][j] + MOD)%MOD;
         }
      }
      out.println(sum);
      
       
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static long[][] mul(long[][] a, long[][] b){
      int n = a.length;
      long[][] ret = new long[n][n];
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            for(int h = 0; h < n; h++){
               ret[k][j] = (ret[k][j] + a[k][h] * b[h][j] + MOD)%MOD;
            }
         }
      }
      return ret;
   }
   
      
}