//make sure to make new file!
import java.io.*;
import java.util.*;

public class B170{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[][] prev = new int[n][11];
      int[][] next = new int[n][11];
      
      for(int k = 0; k < n; k++){
         Arrays.fill(prev[k],-1);
         Arrays.fill(next[k],-1);
      }
      
      for(int k = 1; k < n; k++){
         for(int j = 1; j <= 10; j++){
            prev[k][j] = prev[k-1][j];
         }
         prev[k][array[k-1]] = k-1;
      }
      for(int k = n-2; k >= 0; k--){
         for(int j = 1; j <= 10; j++){
            next[k][j] = next[k+1][j];
         }
         next[k][array[k+1]] = k+1;
      }
      
      int[] maxl = new int[n];
      Arrays.fill(maxl,-1);
      
      for(int k = 1; k < n; k++){
         for(int l = 1; l <= 10; l++){
            int r = array[k] + (array[k]-l);
            if(r >= 1 && r <= 10 && prev[k][l] != -1 && next[k][r] != -1){
               maxl[next[k][r]] = Math.max(maxl[next[k][r]],prev[k][l]);     
            }
         }
      }
      
      long sum = 0L;
      int curl = -1;
      for(int k = 0; k < n; k++){
         curl = Math.max(curl,maxl[k]);
         sum += (long)(k-curl);
      }
      //out.println(sum);
      
      long nl = (long)n;
      long answer = nl*(nl+1L)/2L - sum;
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}