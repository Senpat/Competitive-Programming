//make sure to make new file!
import java.io.*;
import java.util.*;

public class B68{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         char[][] board = new char[n][m];
         
         for(int k = 0; k < n; k++){
            String s = f.readLine();
            for(int j = 0; j < m; j++){
               board[k][j] = s.charAt(j);
            }
         }
         
         HashSet<Integer> rows = new HashSet<Integer>();
         HashSet<Integer> cols = new HashSet<Integer>();
         
         int maxc = -1;
         int maxr = -1;
         for(int k = 0; k < n; k++){
            int count = 0;
            for(int j = 0; j < m; j++){
               if(board[k][j] == '*'){
                  count++;
               }
            }
            
            if(count == maxr){
               rows.add(k);
            } else if(count > maxr){
               maxr = count;
               rows = new HashSet<Integer>();
               rows.add(k);
            }
         } 
         
         for(int j = 0; j < m; j++){
            int count = 0;
            for(int k = 0; k < n; k++){
               if(board[k][j] == '*'){
                  count++;
               }
            }
            
            if(count == maxc){
               cols.add(j);
            } else if(count > maxc){
               maxc = count;
               cols = new HashSet<Integer>();
               cols.add(j);
            }
         }
         
         int answer = n+m - (maxr + maxc);
         if(answer > 0){
            boolean found = false;
            for(int i : rows){
               for(int j : cols){
                  if(board[i][j] == '.'){
                     answer--;
                     found = true;
                     break;
                  }
               }
               if(found) break;
            }
         }
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
      
}