//Minimum Path
import java.io.*;
import java.util.*;

public class D1072{

   public static char[][] board;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      board = new char[n][n];
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < n; j++){
            board[k][j] = s.charAt(j);
         }
      }
      
      StringBuilder answer = new StringBuilder("");
      
      Queue<State> q = new LinkedList<State>();
      q.add(new State(0,0,0));
      
      HashSet<State> done = new HashSet<State>();
      
      int maxmoves = 0;
      
      while(!q.isEmpty()){
         State cur = q.poll();
         
         if(cur.x == n-1 && cur.y == n-1){
            for(int k = 0; k < 2*n; k++){
               out.print("a");
            }
            out.close();
            System.exit(0);
         }
         
         if(cur.alen>=m){
            done.add(cur);
            maxmoves = Math.max(maxmoves,cur.x + cur.y);
            continue;
         }
         
         int x = cur.x;
         int y = cur.y;
         
         if(board[cur.x][cur.y] == 'a'){
            if(in(x+1,y)) q.add(new State(cur.alen,x+1,y));
            if(in(x,y+1)) q.add(new State(cur.alen,x,y+1));
         }
         else {
            if(in(x+1,y)) q.add(new State(cur.alen+1,x+1,y));
            if(in(x,y+1)) q.add(new State(cur.alen+1,x,y+1));
         }
      }
      //System.out.println(maxmoves);
      
      HashSet<State> removes = new HashSet<State>();
      for(State wv : done){
         //System.out.println(wv);  
         if(wv.x + wv.y < maxmoves){
            removes.add(wv);
         }
      }
      
      for(State wv : removes){
         done.remove(wv);
      }
      
      for(int k = 0; k < maxmoves; k++) answer.append("a");
      
      
      Queue<HashSet<State>> qu = new LinkedList<HashSet<State>>();
      
      qu.add(done);
      
      while(!qu.isEmpty()){
         HashSet<State> curhs = qu.poll();
         //if(curhs.size()==0) System.exit(0);
         //System.out.println(curhs   .size());
         int min = 256;
         for(State cur : curhs){
            if(cur.x==n-1 && cur.y==n-1){
               out.println(answer.append(board[cur.x][cur.y]));
               out.close();
               System.exit(0);
            }
            min = Math.min(min,board[cur.x][cur.y]);
            // if(in(cur.x+1,cur.y)) min = Math.min(min,board[cur.x+1][cur.y]);
         //             if(in(cur.x,cur.y+1)) min = Math.min(min,board[cur.x][cur.y+1]);
         }
         
         HashSet<State> next = new HashSet<State>();
         
         answer.append((char)min);
         
         for(State cur : curhs){
            
            if(board[cur.x][cur.y] == min){
               if(in(cur.x+1,cur.y)){
                  next.add(new State(cur.alen,cur.x+1,cur.y));
               }
               if(in(cur.x,cur.y+1)){
                  next.add(new State(cur.alen,cur.x,cur.y+1));
               }
           
            }
               
         }
         
         qu.add(next);
      }
            
            
            
      
      out.close();
   }
   
   public static class State{
      int alen;
      int x;
      int y;
      
      public State(int a, int c, int d){
         alen = a;
         x = c;
         y = d;
      }
      
      public String toString(){
         return x + " " + y;
      }
   }
   
   public static boolean in(int x, int y){
      return x >= 0 && y >= 0 && x < n && y < n;
   }
   
}