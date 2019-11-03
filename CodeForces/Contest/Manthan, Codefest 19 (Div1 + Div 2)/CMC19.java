//make sure to make new file!
import java.io.*;
import java.util.*;

public class CMC19{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      int n4 = n/4;
      int[][] answer = new int[n][n];
      
      for(int k = 0; k < n4; k++){
         for(int j = 0; j < n4; j++){
            int start = (k*n4+j)*16;
            int count = 0;
            for(int x = k*4; x < k*4 + 4; x++){
               for(int y = j*4; y < j*4 + 4; y++){
                  answer[x][y] = start + count;
                  count++;
               }
             }
         }
      }
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            out.print(answer[k][j] + " ");
         }
         out.println();
      } 
      
      
      
      
      out.close();
   }
   
      
}