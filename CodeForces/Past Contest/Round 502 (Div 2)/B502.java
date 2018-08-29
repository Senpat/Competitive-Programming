//Badge
import java.io.*;
import java.util.*;

public class B502{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      boolean[][] matrix = new boolean[n][n];
      
      for(int k = 0; k < n; k++){
         int cur = k;
         while(!matrix[k][cur]){
            matrix[k][cur] = true;
            cur = array[cur]-1;
         }
         out.print(cur+1 + " ");
      }
      
      
      
      
      out.close();
   }
   
}