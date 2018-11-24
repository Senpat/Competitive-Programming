//Biridian Forest
import java.io.*;
import java.util.*;

public class B329{

   public static char[][] board;
   public static int mind,answer,n,m;
   public static boolean[][] seend;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      
      board = new char[n][m];
      
      int ex=0,ey=0,sx=0,sy=0;
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
            if(board[k][j] == 'E'){
               ex = k;
               ey = j;
            }
            if(board[k][j] == 'S'){
               sx = k;
               sy = j;
            }
         }
      }
      
      boolean[][] seen = new boolean[n][m];
      
      Queue<State> q = new LinkedList<State>();
      
      q.add(new State(sx,sy,0));
      
      mind = -1;
      
      while(!q.isEmpty()){
         State cur = q.poll();
         if(!seen[cur.x][cur.y] && mind == -1){
            seen[cur.x][cur.y] = true;
            if(board[cur.x][cur.y] == 'E'){
               mind = cur.level;
               continue;
            }
            
            if(cur.x > 0 && board[cur.x-1][cur.y] != 'T'){
               q.add(new State(cur.x-1,cur.y,cur.level+1));
            }
            if(cur.x < n-1 && board[cur.x+1][cur.y] != 'T'){
               q.add(new State(cur.x+1,cur.y,cur.level+1));
            }
            if(cur.y > 0 && board[cur.x][cur.y-1] != 'T'){
               q.add(new State(cur.x,cur.y-1,cur.level+1));
            }
            if(cur.y < m-1 && board[cur.x][cur.y+1] != 'T'){
               q.add(new State(cur.x,cur.y+1,cur.level+1));
            }
         }
      }
      
      //System.out.println(mind);
      answer = 0;
        
      seend = new boolean[n][m];
      
      //dfs(ex,ey,0);
      
      //iterative bfs instead of recursize dfs
      q = new LinkedList<State>();
      
      q.add(new State(ex,ey,0));
      
      while(!q.isEmpty()){
         State cur = q.poll();
         int x = cur.x;
         int y = cur.y;
         int level = cur.level;
         if(seend[x][y]) continue;
         if(level > mind) 
            continue;
         //System.out.println(Character.getNumericValue(board[x][y]));
         if(level <= mind && board[x][y]!= 'E'){
            seend[x][y] = true;
            //System.out.println(Character.getNumericValue(board[x][y]));
            answer+=Character.getNumericValue(board[x][y]);
         }
         if(x > 0 && !seend[x-1][y] && board[x-1][y] != 'E' && board[x-1][y] != 'S' && board[x-1][y] != 'T'){
            q.add(new State(x-1,y,level+1));
         }
         if(x < n-1 && !seend[x+1][y] && board[x+1][y] != 'E' && board[x+1][y] != 'S' && board[x+1][y] != 'T'){
            q.add(new State(x+1,y,level+1));
         }
         if(y > 0 && !seend[x][y-1] && board[x][y-1] != 'E' && board[x][y-1] != 'S' && board[x][y-1] != 'T'){
            q.add(new State(x,y-1,level+1));
         }
         if(y < m-1 && !seend[x][y+1] && board[x][y+1] != 'E' && board[x][y+1] != 'S' && board[x][y+1] != 'T'){
            q.add(new State(x,y+1,level+1));
         }      
      }
      
      
      out.println(answer);
      
      out.close();
   }
   
   public static void dfs(int x, int y, int level){
      if(level > mind) 
         return;
      System.out.println(Character.getNumericValue(board[x][y]));
      if(level <= mind && board[x][y]!= 'E'){
         seend[x][y] = true;
         //System.out.println(Character.getNumericValue(board[x][y]));
         answer+=Character.getNumericValue(board[x][y]);
      }
      if(x > 0 && !seend[x-1][y] && board[x-1][y] != 'E' && board[x-1][y] != 'S' && board[x-1][y] != 'T'){
         dfs(x-1,y,level+1);
      }
      if(x < n-1 && !seend[x+1][y] && board[x+1][y] != 'E' && board[x+1][y] != 'S' && board[x+1][y] != 'T'){
         dfs(x+1,y,level+1);
      }
      if(y > 0 && !seend[x][y-1] && board[x][y-1] != 'E' && board[x][y-1] != 'S' && board[x][y-1] != 'T'){
         dfs(x,y-1,level+1);
      }
      if(y < m-1 && !seend[x][y+1] && board[x][y+1] != 'E' && board[x][y+1] != 'S' && board[x][y+1] != 'T'){
         dfs(x,y+1,level+1);
      }
   }
      
      
      
   
   public static class State{
      int x;
      int y;
      int level;
      
      public State(int a, int b, int c){
         x = a;
         y = b;
         level = c;
      }
   }
   
}