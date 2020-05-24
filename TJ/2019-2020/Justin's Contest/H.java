//make sure to make new file!
import java.io.*;
import java.util.*;

public class H{

   public static char[][] matrix;
   public static int n;
   public static int m;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      matrix = new char[n][m];
      
      for(int k = 0; k < n; k++){
         matrix[k] = f.readLine().toCharArray();
      }
      
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken())-1;
         int m = Integer.parseInt(st.nextToken())-1;
         
         ff(n,m);
      }
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            out.print(matrix[k][j]);
         }
         out.println();
      }

      
      
      
      
      
      out.close();
   }
   
   public static boolean in(int x, int y){
      return x >= 0 && x < n && y >= 0 && y < m;
   }
   
   public static void ff(int x, int y){
      matrix[x][y] = '#';
      
      if(in(x+1,y) && matrix[x+1][y] == '.') ff(x+1,y);
      if(in(x-1,y) && matrix[x-1][y] == '.') ff(x-1,y);
      if(in(x,y+1) && matrix[x][y+1] == '.') ff(x,y+1);
      if(in(x,y-1) && matrix[x][y-1] == '.') ff(x,y-1);
   }
      
}