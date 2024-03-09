//make sure to make new file!
import java.io.*;
import java.util.*;
//fail
public class swift{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      //matrix[x][y] is parity of number of flights from x to y
      int[][] matrix = new int[n+1][n+1];
      
      for(int k = 1; k < n; k++){
         String s = f.readLine();
         int i = 0;
         for(int j = k+1; j <= n; j++){
            matrix[k][j] = Character.getNumericValue(s.charAt(i));
            i++;
         }
      }
      
      //odds[x][z] is number of ways to go x --1-> y --1-> z
      int[][] odds = new int[n+1][n+1];
      
      for(int x = 1; x <= n; x++){
         for(int y = x+1; y <= n; y++){
            for(int z = y+1; z <= n; z++){
               if(matrix[x][y] == 1 && matrix[y][z] == 1){
                  odds[x][z]++;
               }
            }
         }
      }
      
      int answer = 0;
      for(int x = 1; x <= n; x++){
         for(int z = x+1; z <= n; z++){
            answer += (odds[x][z]&1) ^ (matrix[x][z]&1);
            if(((odds[x][z]&1) ^ (matrix[x][z]&1)) == 1){
               out.println(x + " " + z);
            }
         }
      }
      
      out.println(answer);
      
      
      
      
      
      out.close();
   }
   
      
}