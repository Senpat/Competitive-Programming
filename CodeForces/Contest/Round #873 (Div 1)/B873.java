//make sure to make new file!
import java.io.*;
import java.util.*;
//first subtask
public class B873{
   
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
         
         //value if no dividers
         long answer = 0L;
         for(int k = 1; k <= n-1; k++){
            answer += (long)k * (long)(n-k);
         }
         out.println(answer);
         int[][] rangemin = new int[n][n];            //rangemin[x][y] is min from x to y
         for(int k = 0; k < n; k++){
            rangemin[k][k] = array[k];
            for(int j = k+1; j < n; j++){
               rangemin[k][j] = Math.min(rangemin[k][j-1],array[j]);
            }
         }
         
         for(int k = 0; k < n-1; k++){
            //divider between k and k+1
            int lmax = 0;
            int r = n-1;
            
            for(int l = k; l >= 0; l--){
               lmax = Math.max(lmax,array[l]);
               while(r >= k+1 && rangemin[k+1][r] < lmax){
                  r--;
               }
               
               if(r < k+1) continue;
               
               answer -= r-(k+1)+1;
            } 
            
         }
         
         out.println(answer);
      

      }
      
      
      
      
      out.close();
   }
   
      
}