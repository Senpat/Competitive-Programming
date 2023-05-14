//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      char[][] board = new char[n][n];
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < n; j++){
            board[k][j] = s.charAt(j);
         }
      }
      
      if(n == 1){
         out.println(0);
         out.close();
         return;
      }
      
      ArrayList<PriorityQueue<State>> row = new ArrayList<>(n);
      ArrayList<PriorityQueue<State>> col = new ArrayList<>(n);
      
      for(int k = 0; k < n; k++){
         row.add(new PriorityQueue<State>()); 
         col.add(new PriorityQueue<State>());
      }
      
      row.get(0).add(new State(0,0));
      col.get(0).add(new State(0,0));
      
      int answer = -1;
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            if(k == 0 && j == 0) continue;
            if(board[k][j] == '#') continue;
            
            int min = Math.min(get(row.get(k),j-m),get(col.get(j),k-m));
            if(min == Integer.MAX_VALUE) continue;
            
            min++;
            row.get(k).add(new State(min,j));
            col.get(j).add(new State(min,k));
            
            if(k == n-1 && j == n-1) answer = min;
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int get(PriorityQueue<State> pq, int last){
      while(!pq.isEmpty() && pq.peek().i < last){
         pq.poll();
      }
      
      if(pq.isEmpty()) return Integer.MAX_VALUE;
      return pq.peek().val;
   }
   
   public static class State implements Comparable<State>{
      int val;
      int i;
      public State(int a, int b){
         val = a;
         i = b;
      }
      public int compareTo(State s){
         return val-s.val;
      }
   }
      
}