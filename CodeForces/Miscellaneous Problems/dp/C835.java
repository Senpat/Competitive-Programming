//Star Sky
//wrong
import java.io.*;
import java.util.*;

public class C835{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      
      boolean[][] have = new boolean[101][101];
      int[][] stars = new int[101][101];                                //t%c=0
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         int color = Integer.parseInt(st.nextToken());
         stars[x][y] = color;
         have[x][y] = true;
      }
      
      int[][][] states = new int[c+1][101][101];
      states[0] = makepre(stars);
      
      for(int k = 1; k < c; k++){
         //update stars
         for(int x = 0; x < 101; x++){
            for(int y = 0; y < 101; y++){
               if(have[x][y]){
                  stars[x][y] = (stars[x][y]+1)%(c+1);
               }
            }
         }
         
         states[k] = makepre(stars);
      }
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
         int t = Integer.parseInt(st.nextToken());
         int x1 = Integer.parseInt(st.nextToken());
         int y1 = Integer.parseInt(st.nextToken());
         int x2 = Integer.parseInt(st.nextToken());
         int y2 = Integer.parseInt(st.nextToken());
         
         out.println(states[t%(c+1)][x2][y2] - states[t%(c+1)][x1-1][y2] - states[t%(c+1)][x2][y1-1] + states[t%(c+1)][x1-1][y1-1]);
      }
      
      out.close();
   }
   
   public static int[][] makepre(int[][] matrix){
      int[][] pref = new int[matrix.length][matrix[0].length];
      
      for(int k = 1; k < matrix.length; k++){
         for(int j = 1; j < matrix[0].length; j++){
            pref[k][j] = matrix[k][j] + pref[k-1][j] + pref[k][j-1] - pref[k-1][j-1];
         }
      }
      
      return pref;
   }
   
   public static void p(int[][] a){
      for(int[] aa : a){
         for(int i : aa){
            System.out.print(i+" ");
         }
         System.out.println();
      }
   }
   
}