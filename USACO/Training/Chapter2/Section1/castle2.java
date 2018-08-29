/*
USER: pgz11901
TASK: castle
LANG: JAVA
*/

import java.util.*;
import java.io.*;

class castle2{

   public static int n,m;
   public static boolean[] viewed;
   public static int maxroom;

   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("castle.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
   
      StringTokenizer st = new StringTokenizer(f.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
   
      int[][] matrix = new int[n][m];
   
      boolean[][] adj = new boolean[n*m][n*m];
   
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < n; j++){
            matrix[k][j] = Integer.parseInt(st.nextToken());
            System.out.print(matrix[k][j] + " " + k + " " + j + " " + (k+7*j) + ": ");
            boolean[] b = process(matrix[k][j]);
            if(!b[0]){
               adj[k+7*j][k+7*(j+1)] = true;
            }
            if(!b[1]){
               adj[k+7*j][k+7*j+1] = true;
            }
            if(!b[2]){
               adj[k+7*j][k+7*(j-1)] = true;
            }
            if(!b[3]){
               adj[k+7*j][k+7*j-1] = true;
            }
         }
      }
   
      for(int k = 0; k < n*m; k++){
         for(int j = 0; j < n*m; j++){
            System.out.println(adj[k][j] + "\t");
         }
      }
      
      viewed = new boolean[n*m];
      maxroom = 0;
   
   
   
   
   }

   public static int count(int a, boolean[][] adj){
      int count = 0;
   
      Queue<Integer> q = new LinkedList<Integer>();
   
      q.add(a);
      viewed[a] = true;
   
      while(!q.isEmpty()){
         int i = q.remove();
         count++;
         for(int k = 0; k < n*m; k++){
            if(adj[i][k]){
               if(!viewed[k]){
                  q.add(k);
               }
            }
         }
      }
      
      return count;
   }

   public static boolean[] process(int i){   //0-south, 1-east, 2-north, 3-west
      boolean[] array = new boolean[4];
      Arrays.fill(array, false);
   
      if(i >= 8){
         array[0] = true;
         i-= 8;
      }
      if(i >= 4){
         array[1] = true;
         i -= 4;
      }
      if(i >= 2){
         array[2] = true;
         i -= 2;
      }
      if(i >= 1){
         array[3] = true;
         i -= 1;
      }
      
      System.out.println(array[0] + " " + array[1] + " " + array[2] + " " + array[3]);
   
      return array;
   }

}
