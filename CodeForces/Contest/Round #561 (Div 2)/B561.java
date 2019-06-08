//make sure to make new file!
import java.io.*;
import java.util.*;

public class B561{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      if(n < 25){
         out.println(-1);
         out.close();
         System.exit(0);
      }
      
      int y = find5(n);
      if(y == -1){
         out.println(-1);
         out.close();
         System.exit(0);
      }
      
      char[][] matrix = new char[y][n/y];
      char[] vowels = {'a','e','i','o','u'};
      int cvowel = 0;
      int rvowel = 0;
      for(int k = 0; k < matrix.length; k++){
         for(int j = 0; j < matrix[0].length; j++){
            if(j == 0){
               matrix[k][j] = vowels[cvowel];
               cvowel = (cvowel + 1)%5;
               rvowel = cvowel;
            } else {
               matrix[k][j] = vowels[rvowel];
               rvowel = (rvowel + 1)%5;
            }
            out.print(matrix[k][j]);
         }
      }
      
      
      
      
      
      out.close();
   }
   
   public static int find5(int i){
      int x = 5;
      while(x < i/2 && i % x != 0){
         x++;
      }
      if(i%x != 0 || i/x < 5) return -1;
      return x;
   }
   
      
}