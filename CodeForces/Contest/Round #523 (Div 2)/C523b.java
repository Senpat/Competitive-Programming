//make sure to make new file!
import java.io.*;
import java.util.*;

public class C523b{

   public static long[] array;
   public static long MOD = 1000000007;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      array = new long[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      long answer = 0L;
      answer+=n;
      
      long[][] matrix = new long[n][2];
      
      for(int k = 0; k < n; k++){
         matrix[k][0] = 1L;
      }
      
      int cur = 0;
      
      boolean bool = true;
      int d = 2;
      while(bool){
         bool = false;
         
         long cursum = matrix[0][cur];
         for(int k = 0; k < n; k++){
            matrix[k][1-cur] = 0L;
         } 
         
         
         for(int k = 1; k < n; k++){
            if(array[k]%d == 0){
               bool = true;
               answer += cursum;
               matrix[k][1-cur]= cursum;
            }
            
            cursum += matrix[k][cur];
            
         }
         
         cur = 1-cur;
         d++;
      }
      
      out.println((answer+MOD)%MOD);
      
      out.close();
   }
   
   
}