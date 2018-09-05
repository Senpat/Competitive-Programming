//Find Square
import java.io.*;
import java.util.*;

public class A{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      char[][] matrix = new char[n][m];
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            matrix[k][j] = s.charAt(j);
         }
      }
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(matrix[k][j] == 'B'){
               int i = 1;
               while(k+i < n && j+i<m && matrix[k+i][j+i]=='B'){
                  i++;
               }
               
               out.println((k+i/2+1) + " " + (j+i/2+1));
               out.close();
               System.exit(0);
            }
         }
      }
      
      
      
      out.close();
   }
   
}