//make sure to make new file!
import java.io.*;
import java.util.*;

public class B344{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      int[][] board = new int[n][m];
      
      int[] rows = new int[n];               //last color this row was colored
      int[] cols = new int[m];               //last color this col was colored
      int[] rowst = new int[n];               //time this row was colored
      int[] colst = new int[m];               //time this col was colored
      
      for(int k = 1; k <= q; k++){
         st = new StringTokenizer(f.readLine());
      
         int i = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken())-1;
         int c = Integer.parseInt(st.nextToken());
         
         if(i == 1){
            rows[x] = c;
            rowst[x] = k;
         } else {
            cols[x] = c;
            colst[x] = k;
         }
      }
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(Math.max(rowst[k],colst[j]) == 0) board[k][j] = 0;
            else if(rowst[k] > colst[j]) board[k][j] = rows[k];
            else board[k][j] = cols[j];
         }
      }
      
      StringBuilder sb = new StringBuilder("");
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            sb.append(board[k][j] + " ");
         }
         sb.append("\n");
      }
      
      out.println(sb);
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}