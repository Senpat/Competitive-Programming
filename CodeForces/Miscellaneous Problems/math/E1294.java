//Obtain and Permutation
import java.io.*;
import java.util.*;

public class E1294{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      
      int[][] matrix = new int[n][m];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < m; j++){
            matrix[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      int[][] shifts = new int[n][m];
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            //see if it's in the right column
            if(matrix[k][j]%m==(j+1)%m && matrix[k][j] <= n*m){
               
               //find how much it shifts
               int shift = k-(matrix[k][j]-1)/m;
               if(shift < 0) shift += n;
               
               shifts[shift][j]++;
             }
          }
       }
       
       
       
      int answer = 0;
      for(int j = 0; j < m; j++){
         int min = n+1;
         for(int k = 0; k < n; k++){
            min = Math.min(n-shifts[k][j]+k,min);
         }
         answer+=min;
      }
      
      out.println(answer);

      
      
      
      
      
      out.close();
   }
   
      
}