//make sure to make new file!
import java.io.*;
import java.util.*;

public class DPR{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[][] board = new int[n][m];
         int[] rowones = new int[n];
         int allones = 0;
         for(int k = 0; k < n; k++){
            st = new StringTokenizer(f.readLine());
            for(int j = 0; j < m; j++){
               board[k][j] = Integer.parseInt(st.nextToken());
               if(board[k][j] == 1){
                  allones++;
                  rowones[k]++;
               }
            }
         }
         
         if(allones % n != 0){
            out.println(-1);
            continue;
         }
         
         int thresh = allones/n;
         
         ArrayList<State> answer = new ArrayList<State>();
         
         for(int c = 0; c < m; c++){
            ArrayList<Integer> under = new ArrayList<Integer>();
            ArrayList<Integer> over = new ArrayList<Integer>();
            
            for(int r = 0; r < n; r++){
               if(rowones[r] > thresh && board[r][c] == 1){
                  over.add(r);
               } 
               if(rowones[r] < thresh && board[r][c] == 0){
                  under.add(r);
               }
            }
            
            for(int k = 0; k < Math.min(over.size(),under.size()); k++){
               answer.add(new State(over.get(k)+1,under.get(k)+1,c+1));
               rowones[over.get(k)]--;
               rowones[under.get(k)]++;
            }
         }         
      
         out.println(answer.size());
         StringJoiner sj = new StringJoiner("\n");
         for(int k = 0; k < answer.size(); k++){
            sj.add(answer.get(k).toString());
         }
         out.println(sj.toString());
         
      }
      
      
      
      
      out.close();
   }
   
   public static class State{
      int x;
      int y;
      int z;
      public State(int a, int b, int c){
         x = a;
         y = b;
         z = c;
      }
      
      public String toString(){
         return "" + x + " " + y + " " + z;
      }
   }
}