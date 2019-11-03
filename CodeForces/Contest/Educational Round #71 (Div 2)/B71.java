
//make sure to make new file!
import java.io.*;
import java.util.*;

public class B71{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[][] a = new int[n][m];
      int[][] b = new int[n][m];
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < m; j++){
            a[k][j] = Integer.parseInt(st.nextToken());
            b[k][j] = 0;
         }
      }
      
      
      ArrayList<Answer> answer = new ArrayList<Answer>();

      for(int k = 0; k < n-1; k++){
         for(int j = 0; j < m-1; j++){
            if(a[k][j] == 1 && a[k+1][j] == 1 && a[k][j+1] == 1 && a[k+1][j+1] == 1){
               answer.add(new Answer(k+1,j+1));
               b[k][j] = 1;
               b[k+1][j] = 1;
               b[k][j+1] = 1;
               b[k+1][j+1] = 1;
            }
         }
      }
      
      if(check(a,b)){
         out.println(answer.size());
         for(Answer ans : answer){
            out.println(ans.x + " " + ans.y);
         }
      } else {
         out.println(-1);
      }
      
      out.close();
   }
   
   public static boolean check(int[][] a, int[][] b){
      for(int k = 0; k < a.length; k++){
         for(int j = 0; j < a[0].length; j++){
            if(a[k][j] != b[k][j]) return false;
         }
      }
      return true;
   }
   
   public static class Answer{
      int x;
      int y;
      public Answer(int a, int b){
         x = a;
         y = b;
      }
   }
   
      
}