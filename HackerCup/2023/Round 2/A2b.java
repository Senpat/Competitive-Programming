import java.io.*;
import java.util.*;

public class A2b{

   public static int n,m;
   
   public static char[][] board;
   public static boolean[][] seen;
   
   public static int cursize;
   
   public static int[] dx = {0,0,1,-1};
   public static int[] dy = {1,-1,0,0};
   
   public static HashSet<Integer> adj;
   
   public static HashMap<Integer,Integer> map;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("a2_full_in.txt"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("a2_full_out.txt")));
      
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         n = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());
         
         board = new char[n][m];
         
         for(int k = 0; k < n; k++){
            String s = f.readLine();
            for(int j = 0; j < m; j++){
               board[k][j] = s.charAt(j);
            }
         }
         
         seen = new boolean[n][m];
         map = new HashMap<Integer,Integer>();
         
         for(int k = 0; k < n; k++){
            for(int j = 0; j < m; j++){
               if(!seen[k][j] && board[k][j] == 'W'){
                  cursize = 0;
                  adj = new HashSet<Integer>();
                  dfs(k,j);
                  //System.out.println(k + " " + j + " " + cursize);
                  if(adj.size() == 1){
                     int i = -1;
                     for(int ai : adj){
                        i = ai;
                     }
                     //System.out.println(i);
                     if(map.containsKey(i)){
                        map.put(i,map.get(i)+cursize);
                     } else {
                        map.put(i,cursize);
                     }
                  }
               }
            }
         }
         
         int max = 0;
         for(int i : map.keySet()){
            max = Math.max(max,map.get(i));
         }
         
         out.println("Case #" + q + ": " + max);
      }
        
        
      out.close();
   }
   
   
   public static void dfs(int xi, int yi){
   
      Stack<Pair> stack = new Stack<Pair>();
      stack.push(new Pair(xi,yi));
      seen[xi][yi] = true;
      
      while(!stack.isEmpty()){
         Pair p = stack.pop();
         int x = p.x;
         int y = p.y;
         cursize++;
         
         for(int d = 0; d < 4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];
            
            if(!in(nx,ny)) 
               continue;
            
            if(board[nx][ny] == '.'){
               adj.add(conv(nx,ny));
               continue;
            }
            
            if(board[nx][ny] == 'B' || seen[nx][ny]) 
               continue;
            seen[nx][ny] = true;
            stack.push(new Pair(nx,ny));
         }
      }
   }
   
   
   public static int conv(int x, int y){
      return x*m + y;
   }
   
   public static boolean in(int x, int y){
      return x < n && x >= 0 && y < m && y >= 0;
   }
   
   public static class Pair{
      int x;
      int y;
      public Pair(int a, int b){
         x = a;
         y = b;
      }
   }
}