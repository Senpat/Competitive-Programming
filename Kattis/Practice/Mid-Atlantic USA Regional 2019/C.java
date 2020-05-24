//make sure to make new file!
import java.io.*;
import java.util.*;

public class C{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      
      char[][] matrix = new char[n][n];
      for(int k = 0; k < n; k++) matrix[k] = f.readLine().toCharArray();
      
      if(check(matrix)){
         out.println(1);
      } else {
         out.println(0);
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean check(char[][] matrix){
      int n = matrix.length;
      for(int k = 0; k < n; k++){   
         int numb = 0;
         int numw = 0;
         for(int j = 0; j < n; j++){
            if(matrix[k][j] == 'B') numb++;
            else numw++;
            
            if(j >= 2 && matrix[k][j] == matrix[k][j-1] && matrix[k][j] == matrix[k][j-2]) return false;
            
            
         }
         if(numb != numw) return false;
      }
      
      for(int k = 0; k < n; k++){   
         int numb = 0;
         int numw = 0;
         for(int j = 0; j < n; j++){
            if(matrix[j][k] == 'B') numb++;
            else numw++;
            
            if(j >= 2 && matrix[j][k] == matrix[j-1][k] && matrix[j][k] == matrix[j-2][k]) return false;
            
            
         }
         if(numb != numw) return false;
      }
      
      return true;
      
   
   }
   
      
}