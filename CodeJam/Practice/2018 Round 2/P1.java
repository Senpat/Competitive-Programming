//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         int[] freq = new int[n+1];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            freq[array[k]]++;
         }
         
         if(array[0] == 0 || array[n-1] == 0){
            out.println("Case #" + q + ": IMPOSSIBLE");
            continue;
         }
         
         
         int[] go = new int[n];
         int i = 0;
         for(int k = 0; k < n; k++){
            for(int j = 0; j < array[k]; j++){
               go[i] = k;
               i++;
            }
         }
         
         int maxdif = 0;
         
         for(int k = 0; k < n; k++){
            maxdif = Math.max(maxdif,Math.abs(k-go[k]));
         }
         
         char[][] matrix = new char[maxdif+2][n];
         
         for(int k = 0; k <= maxdif; k++){
            for(int j = 0; j < n; j++){
               matrix[k][j] = '.';
            }
         }
         
         for(int k = 0; k < n; k++){
            if(go[k] < k){
               for(int j = 0; j < k-go[k]; j++){
                  matrix[j][k-j] = '/';
               }
            }
            if(go[k] > k){
               for(int j = 0; j < go[k]-k; j++){
                  matrix[j][k+j] = '\\';
               }
            }
         }
         
         out.println("Case #" + q + ": " + (maxdif+1));
         for(int k = 0; k <= maxdif; k++){
            for(int j = 0; j < n; j++){
               out.print(matrix[k][j]);
            }
            out.println();
         }
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}