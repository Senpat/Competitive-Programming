//Lakes in Berland
import java.io.*;
import java.util.*;

public class D723{
   
   public static int n,m,i;
   public static char[][] board,oriboard;
   public static int cursize,comp;
   public static int[][] comps;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      i = Integer.parseInt(st.nextToken());
      
      board = new char[n][m];
      oriboard = new char[n][m];
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < m; j++){
            board[k][j] = s.charAt(j);
            oriboard[k][j] = s.charAt(j);
         }
      }
      
      //fill lakes on edges with *
      for(int k = 0; k < m; k++){                        //top and bottom
         dfs1(0,k);
         dfs1(n-1,k);
      }
      
      for(int k = 1; k < n-1; k++){
         dfs1(k,0);
         dfs1(k,m-1);
      }
      
      //count components
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
      HashMap<Integer,Queue<Integer>> hm = new HashMap<Integer,Queue<Integer>>();
      for(int k = 0; k < n*m; k++){
         hm.put(k,new LinkedList<Integer>());
      }
      
      cursize = 0;
      comp = 1;
      comps = new int[n][m];
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(board[k][j] == '.'){
               dfs(k,j);
               pq.add(cursize);
               hm.get(cursize).add(comp); 
               cursize = 0;       
               comp++;
            }
         }
      }
      
      int answer = 0;
      HashSet<Integer> change = new HashSet<Integer>();
      
      for(int k = 0; k < comp-1-i; k++){
         int curpoll = pq.poll();
         change.add(hm.get(curpoll).poll());
         answer += curpoll;
      }
      
      out.println(answer);
        
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            if(change.contains(comps[k][j])){
               out.print('*');
            } else {
               out.print(oriboard[k][j]);
            }
         }
         out.println();
      }
        
      out.close();
   }
   
   public static void dfs(int x, int y){
      if(in(x,y) && board[x][y] == '.'){
         cursize++;
         board[x][y] = '*';
         comps[x][y] = comp;
         dfs(x-1,y);
         dfs(x+1,y);
         dfs(x,y-1);
         dfs(x,y+1);
      }
   }
   
   public static void dfs1(int x, int y){                
      if(in(x,y) && board[x][y] == '.'){
         board[x][y] = '*';
         dfs1(x-1,y);
         dfs1(x+1,y);
         dfs1(x,y-1);
         dfs1(x,y+1);
      }
   
   }
   
   public static boolean in(int x, int y){
      return x >= 0 && y >= 0 && x < n && y < m;
   }
   
}