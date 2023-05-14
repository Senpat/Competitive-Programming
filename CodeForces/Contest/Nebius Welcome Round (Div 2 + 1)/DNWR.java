//make sure to make new file!
import java.io.*;
import java.util.*;

public class DNWR{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
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
         
         int minanswer = 0;
         int maxanswer = 0;
         
         for(int k = 0; k < n; k++){
            //calculate min and max answer for this row
            //min - get adjacent 1s to count once
            int num1 = 0;
            int maxdelete = 0;
            int start = -1;
            for(int j = 0; j < m; j++){
               if(board[k][j] == 1){
                  if(start == -1) start = j;
                  num1++;
               } else {
                  if(start != -1){
                     //streak from start to j-1
                     maxdelete += (j-1 - start+1)/2;
                     start = -1;
                  }
               }
            }
            if(start != -1){
               maxdelete += (m-1 - start+1)/2;
            }
            
            maxdelete = Math.min(m/4,maxdelete);
            minanswer += num1-maxdelete;
            
            //get streaks without consecutive 1s
            start = -1;
            int maxuse = 0;         //max # of doubles you can use that don't use two 1s at once
            for(int j = 1; j < m; j++){
               if(board[k][j] == 1 && board[k][j-1] == 1){
                  if(start != -1){
                     //streak from start to j-1
                     maxuse += (j-1 - start + 1)/2;
                     start = -1;
                  }
               } else {
                  if(start == -1){
                     start = j-1;
                  }
               }
            }
            if(start != -1){
               maxuse += (m-1 - start + 1)/2;
            }
            
            maxanswer += num1 - Math.max(0,(m/4 - maxuse));
         
         
         
         }
         
         out.println(minanswer + " " + maxanswer);
         
      
      
      
      
      
      out.close();
   }
   
      
}