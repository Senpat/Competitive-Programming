/*
USER: pgz11901
TASK: castle
LANG: JAVA
*/

import java.util.*;
import java.io.*;

class castle{
   
   public static int[][] board, group;
   public static int m,n;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("castle.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      m = Integer.parseInt(st.nextToken());
      n = Integer.parseInt(st.nextToken());
      
      board = new int[n][m];
      group = new int[n][m];
      
      
      
      for(int k = 0; k < n; k++){
         Arrays.fill(group[k],-1);
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j<m; j++){
            board[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      /*for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            System.out.print(board[k][j] + " ");
         }
         System.out.println();
      }*/
      
      int c = 0;
      ArrayList<Integer> g = new ArrayList<Integer>();
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(group[k][j] == -1){
               g.add(dfs(k,j,c));
               c++;
            }
         }
      }
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            System.out.print(group[k][j] + " ");
         }
         System.out.println();
      }
      
      System.out.println(g);
      
      System.out.println(g.size());
      out.println(g.size());
      
      int max = 0;
      for(Integer i : g){
         max = Math.max(max,i);
      }
      
      System.out.println(max);
      out.println(max);
      
      if(g.size()==n*m && n>=2){
         out.println(2);
         out.println(n + " 1 N");
         out.close();
         System.exit(0);
      }
      
      
      int maxcomb = 0;
      int maxx = 0;
      int maxy = 0;
      char maxchar = 'E';
      
      for(int k = n-1; k >= 0; k--){
         for(int j = 0; j < m; j++){
            if(k!=0){                        //check north
               if(group[k][j] != group[k-1][j]){
                  int i = g.get(group[k][j]) + g.get(group[k-1][j]);
                  if(i > maxcomb){
                  
                     maxcomb = i;
                     maxx = j;
                     maxy = k;
                     maxchar = 'N';
                     //System.out.println(maxcomb + " " + maxx + " " + maxy + " " + maxchar);
                  }
                  if(i==maxcomb){
                     if(j==maxx){
                        maxcomb = i;
                        maxx = j;
                        maxy = k;
                        maxchar = 'N';
                        //System.out.println(maxcomb + " " + maxx + " " + maxy + " " + maxchar);
                     
                     } 
                     else if(j<maxx){
                        maxcomb = i;
                        maxx = j;
                        maxy = k;
                        maxchar = 'N';
                        //System.out.println(maxcomb + " " + maxx + " " + maxy + " " + maxchar);
                     }
                  }
               }
            }
            if(j!=m-1){                      //check east
               if(group[k][j] != group[k][j+1]){
                  int i = g.get(group[k][j]) + g.get(group[k][j+1]);
                  if(i > maxcomb){
                     maxcomb = i;
                     maxx = j;
                     maxy = k;
                     maxchar = 'E';
                     //System.out.println(maxcomb + " " + maxx + " " + maxy + " " + maxchar);
                  }
                  if(i==maxcomb){
                     if(j==maxx){
                        if(k>maxy){
                           maxcomb = i;
                           maxx = j;
                           maxy = k;
                           maxchar = 'E';
                           //System.out.println(maxcomb + " " + maxx + " " + maxy + " " + maxchar);
                        
                        
                        }
                     
                     } 
                     else if(j<maxx){
                        maxcomb = i;
                        maxx = j;
                        maxy = k;
                        maxchar = 'E';
                        //System.out.println(maxcomb + " " + maxx + " " + maxy + " " + maxchar);
                     
                     }
                  }
               }
            }
         }
      }
      
      System.out.println(maxcomb);
      out.println(maxcomb);
      
      maxx++;
      maxy++;
      System.out.println(maxy + " " + maxx + " " + maxchar);
      out.println(maxy + " " + maxx + " " + maxchar);
   
      
      
      out.close();
   }
   
   public static int dfs(int a, int b,int c){
      if(group[a][b] == -1){
         int count = 1;
         group[a][b] = c;
         if(west(a,b)) count += dfs(a,b-1,c);
         if(north(a,b)) count += dfs(a-1,b,c);
         if(east(a,b)) count += dfs(a,b+1,c);
         if(south(a,b)) count += dfs(a+1,b,c);
         
         
         return count;
      
      
      
      }
      return 0;
   }
   
   
   public static boolean west(int k, int j){
      return (board[k][j]&1) == 0;
   }
   
   public static boolean north(int k, int j){
      return (board[k][j]&2) == 0;
   }
   
   public static boolean east(int k, int j){
      return (board[k][j]&4) == 0;
   }
   
   public static boolean south(int k, int j){
      return (board[k][j]&8) == 0;
   }
   
     
}
