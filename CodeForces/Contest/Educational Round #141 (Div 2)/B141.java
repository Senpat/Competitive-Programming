//make sure to make new file!
import java.io.*;
import java.util.*;

public class B141{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         int up = n*n;
         int down = 1;
         
         int[][] matrix = new int[n][n];
         for(int k = 0; k < n; k++){
            for(int j = 0; j < n; j++){
               int c = j;
               if(k%2 == 1){
                  c = n-j-1;
               }
               if((k+c)%2 == 0){
                  matrix[k][c] = down;
                  down++;
               } else {
                  matrix[k][c] = up;
                  up--;
               }
            }
         }
         
         StringJoiner sj = new StringJoiner("\n");
         for(int k = 0; k < n; k++){
            StringJoiner row = new StringJoiner(" ");
            for(int j = 0; j < n; j++){
               row.add("" + matrix[k][j]);
            }
            sj.add(row.toString());
         }
         
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}