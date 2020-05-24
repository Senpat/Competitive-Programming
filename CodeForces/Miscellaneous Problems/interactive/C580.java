//make sure to make new file!
import java.io.*;
import java.util.*;
//NOT DONE IMPLEMENTING
public class C580{

   public static BufferedReader f;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[][] matrix = new int[n+1][n+1];
      for(int k = 0; k <= n; k++) Arrays.fill(matrix[k],-1);
      
      matrix[1][1] = 1;
      matrix[n][n] = 0;
      
      //find all odds
      int i;
      for(int k = 1; k <= n; k+=2){
         for(int j = 1; j <= n-2; j+=2){
            if(k == n && j == n-2) 
               continue;
            //figure out matrix[k][j+2] from matrix[k][j]
            i = query(k,j,k,j+2);
            
            if(i == 1){
               matrix[k][j+2] = matrix[k][j];
            } else {
               matrix[k][j+2] = 1-matrix[k][j];
            }
            
            if(k < n){
               i = query(k,j,k+1,j+1);
               
               if(i == 1){
                  matrix[k+1][j+1] = matrix[k][j];
               } else {
                  matrix[k+1][j+1] = 1-matrix[k][j];
               }
            }
         }
         
         if(k <= n-2){
            i = query(k,1,k+2,1);
            
            if(i == 1){
               matrix[k+2][1] = matrix[k][1];
            } else {
               matrix[k+2][1] = 1-matrix[k][1];
            }
         
         }
      
      }
      
      //find 3x3 with 1 in top left and 0 in bot right
      int X = -1;
      int Y = -1;
      
      for(int k = 1; k <= n-2; k++){
         if(X != -1) 
            break;
         for(int j = 1; j <= n-2; j++){
            if(matrix[k][j] == -1) 
               continue;
            if(matrix[k][j] == 1 && matrix[k+2][j+2] == 0){
               X = k;
               Y = j;
               break;
            }
         }
      }
      
      //fill evens
      if(matrix[X+1][Y+1] != matrix[X+2][Y]){
         i = query(X+1,Y,X+2,Y+2);
         
         if(i == 1){
            matrix[X+1][Y] = matrix[X+2][X+2];
         } else {
            matrix[X+1][Y] = 1-matrix[X+2][X+2];
         }
         
         i = query(X+1,Y,X+2,Y+1);
         
         if(i == 1){
            matrix[X+2][Y+1] = matrix[X+1][Y];
         } else {
            matrix[X+2][Y+1] = 1-matrix[X+1][Y];
         }
         
         i = query(X,Y+1,X+2,Y+1);
         
         if(i == 1){
            matrix[X][Y+1] = matrix[X+2][Y+1];
         } else {
            matrix[X][Y+1] = 1-matrix[X+2][Y+1];
         }
         
         i = query(X,Y+1,X+1,Y+2);
         
         if(i == 1){
            matrix[X+1][Y+2] = matrix[X][Y+1];
         } else {
            matrix[X+1][Y+2] = 1-matrix[X][Y+1];
         }
         
      } else if(matrix[X+1][Y+1] != matrix[X][Y+2]){
      
      
      
      } else {
      
      
      }
       
         
      
      
      
      
      //print matrix
      StringJoiner sj = new StringJoiner("");
      sj.add("!\n");
      for(int k = 1; k <= n; k++){
         for(int j = 1; j <= n; j++){
            sj.add("" + matrix[k][j]);
         }
         sj.add("\n");
      }
      
      out.println(sj.toString());
      
      
      out.close();
   }
   
   public static int query(int a, int b, int c, int d) throws IOException{
      out.println("? " + a + " " + b + " " + c + " " + d);
      out.flush();
      
      return Integer.parseInt(f.readLine());
   }
      
}