//make sure to make new file!
import java.io.*;
import java.util.*;

public class A789{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int[][] left = new int[n+1][n];
         int[][] right = new int[n+1][n];
         
         for(int k = 0; k < n; k++){
            for(int j = 0; j <= k; j++){
               right[array[k]][j]++;
            }
            for(int j = k; j < n; j++){
               left[array[k]][j]++;
            }
         }
         
         for(int k = 0; k < n; k++){
            for(int j = 1; j <= n; j++){
               left[j][k] += left[j-1][k];
               right[j][k] += right[j-1][k];
            }
         }
         
         long answer = 0L;
         
         for(int b = 1; b < n-1; b++){
            for(int c = b+1; c < n-1; c++){
               long numa = (long)left[array[c]-1][b-1];
               long numb = (long)right[array[b]-1][c+1];
               
               answer += numa*numb;
            }
         }
         
         out.println(answer);
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}