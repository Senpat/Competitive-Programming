//make sure to make new file!
import java.io.*;
import java.util.*;

public class C777{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         int[][] board = new int[n][m];
         
         for(int k = 0; k < n; k++){
            String s = f.readLine();
            for(int j = 0; j < m; j++){
               board[k][j] = Character.getNumericValue(s.charAt(j));
            }
         }
         
         if(board[0][0] == 1){
            out.println("-1");
            continue;
         }
         
         ArrayList<Rect> answer = new ArrayList<Rect>();
         
         for(int k = 0; k < n; k++){
            for(int j = m-1; j >= 1; j--){
               if(board[k][j] == 1){
                  answer.add(new Rect(k,j-1,k,j));
               }
            }
         }
         
         //get first column
         for(int k = n-1; k >= 1; k--){
            if(board[k][0] == 1){
               answer.add(new Rect(k-1,0,k,0));
            }
         }
         
         
         
         StringJoiner sj = new StringJoiner("\n");
         sj.add("" + answer.size());
         for(Rect r : answer){
            sj.add(r.toString());
         }
         out.println(sj.toString());
         
      

      }
      
      
      
      
      out.close();
   }
   
   public static class Rect{
      int x1;
      int y1;
      int x2;
      int y2;
      
      public Rect(int a, int b, int c, int d){
         x1 = a+1;
         y1 = b+1;
         x2 = c+1;
         y2 = d+1;
      }
      
      public String toString(){
         return x1 + " " + y1 + " " + x2 + " " + y2;
      }
   }
      
}