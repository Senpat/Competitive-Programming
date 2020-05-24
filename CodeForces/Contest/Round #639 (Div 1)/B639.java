//make sure to make new file!
import java.io.*;
import java.util.*;

public class B639{
   
   public static boolean[][] seen;   
   public static int n;
   public static int m;
   public static char[][] matrix;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      matrix = new char[n][m];
      
      int[] numrow = new int[n];
      int[] numcol = new int[m];
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            matrix[k][j] = s.charAt(j);
            if(matrix[k][j] == '#'){
               numrow[k]++;
               numcol[j]++;
            }
         }
         
      }
      
      //check if impossible
      if(checkgap() && checkrolcol(numrow,numcol)){
         seen = new boolean[n][m];
         int answer = 0;
         for(int k = 0; k < n; k++){
            for(int j = 0; j < m; j++){
               if(matrix[k][j] == '#' && !seen[k][j]){
                  seen[k][j] = true;
                  answer++;
                  ff(k,j);
               }
            }
         }
         
         out.println(answer);
      
      } else {
         out.println("-1");
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean in(int x, int y){
      return x >= 0 && x < n && y >= 0 && y < m;
   }
   
   public static void ff(int x, int y){
      
      if(in(x+1,y) && !seen[x+1][y] && matrix[x+1][y] == '#'){
         seen[x+1][y] = true;
         ff(x+1,y);
      }
      if(in(x-1,y) && !seen[x-1][y] && matrix[x-1][y] == '#'){
         seen[x-1][y] = true;
         ff(x-1,y);
      }
      if(in(x,y+1) && !seen[x][y+1] && matrix[x][y+1] == '#'){
         seen[x][y+1] = true;
         ff(x,y+1);
      }
      if(in(x,y-1) && !seen[x][y-1] && matrix[x][y-1] == '#'){
         seen[x][y-1] = true;
         ff(x,y-1);
      }
   }
   
   
   
   public static boolean checkgap(){
      
      //check row
      for(int k = 0; k < n; k++){
         boolean sawdark = false;
         boolean sawwhite = false;     //after dark
         for(int j = 0; j < m; j++){
            if(matrix[k][j] == '#'){
               if(sawwhite) 
                  return false;
               sawdark = true;
            } else {
               if(sawdark){
                  sawwhite = true;
               }
            }
         }
      }
      
      //check col
      for(int j = 0; j < m; j++){
         boolean sawdark = false;
         boolean sawwhite = false;     //after dark
         for(int k = 0; k < n; k++){
            if(matrix[k][j] == '#'){
               if(sawwhite) 
                  return false;
               sawdark = true;
            } else {
               if(sawdark){
                  sawwhite = true;
               }
            }
         }
      }
      
      return true;
   }
   
   public static boolean checkrolcol(int[] numrow, int[] numcol){
      //every row and col needs at least 1 # OR cell with 0 # in row and col 
      
      //check row
      for(int k = 0; k < n; k++){
         boolean pass = false;
         for(int j = 0; j < m; j++){
            if(matrix[k][j] == '#' || (numrow[k] == 0 && numcol[j] == 0)){
               pass = true;
            }
         }
         if(!pass) 
            return false;
      }
      
      //check column
      for(int j = 0; j < m; j++){
         boolean pass = false;
         for(int k = 0; k < n; k++){
            if(matrix[k][j] == '#' || (numrow[k] == 0 && numcol[j] == 0)){
               pass = true;
            }
         }
         if(!pass) 
            return false;
      }
      
      return true;
   }
}