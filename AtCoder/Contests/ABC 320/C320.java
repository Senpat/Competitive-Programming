//make sure to make new file!
import java.io.*;
import java.util.*;

public class C320{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      int THRESH = 4*n;
      
      int[][] array = new int[3][n];
      for(int k = 0; k < 3; k++){
         String s = f.readLine();
         for(int j = 0; j < n; j++){
            array[k][j] = Character.getNumericValue(s.charAt(j));
         }
      }
      
      int[][] perms = new int[][]{{0,1,2},{0,2,1},{1,0,2},{1,2,0},{2,0,1},{2,1,0}};
      
      int minanswer = Integer.MAX_VALUE;
      for(int x = 0; x <= 9; x++){
         for(int[] p : perms){
            int pi = 0;
            
            int i = 0;
            
            while(pi <= 2 && i <= THRESH){
               if(array[p[pi]][i%n] == x){
                  pi++;
               }
               i++;
            }
            
            if(i <= THRESH){
               minanswer = Math.min(minanswer,i-1);
            }
         }
      }
      
      if(minanswer == Integer.MAX_VALUE){
         out.println(-1);
      } else {
         out.println(minanswer);
      }
      
      
      
      out.close();
   }
   
      
}