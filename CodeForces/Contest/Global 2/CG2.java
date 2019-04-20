//make sure to make new file!
import java.io.*;
import java.util.*;

public class CG2{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[][] board1 = new int[n][m];
      
      int[] rows = new int[n];
      int[] columns = new int[m];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < m; j++){
            board1[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < m; j++){
            int i = Integer.parseInt(st.nextToken());
            
            if(board1[k][j] != i){
               rows[k]++;
               columns[j]++;
            }
         
         }
      }

      if(check(rows,columns)){
         out.println("Yes");
      } else {
         out.println("No");
      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(int[] r, int[] c){
      for(int k = 0; k < r.length; k++){
         if(r[k]%2 == 1) return false;
      }
      for(int k = 0; k < c.length; k++){
         if(c[k]%2 == 1) return false;
      }
      return true;
   }
      
   
      
}