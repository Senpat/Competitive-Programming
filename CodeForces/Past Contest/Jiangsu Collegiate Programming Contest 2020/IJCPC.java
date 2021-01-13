//make sure to make new file!
import java.io.*;
import java.util.*;

public class IJCPC{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int xs = Integer.parseInt(st.nextToken())-1;
      int ys = Integer.parseInt(st.nextToken())-1;
      int xt = Integer.parseInt(st.nextToken())-1;
      int yt = Integer.parseInt(st.nextToken())-1;
      
      long[][] a = new long[n][m];
      long[][] b = new long[n][m];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < m; j++){
            a[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < m; j++){
            b[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      long[][] h = new long[n][m-1];                  //horizonal times
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < m-1; j++){
            h[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      long[][] v = new long[n-1][m];                  //vertical times
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         for(int j = 0; j < m; j++){
            v[k][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      long[][] times = new long[n][m];
      
      for(int k = 0; k < n; k++) Arrays.fill(times[k],Long.MAX_VALUE);
      
      PriorityQueue<State> pq = new PriorityQueue<State>();
      times[xs][ys] = 0;
      pq.add(new State(xs,ys,0));
      
      long answer = -1;
      while(!pq.isEmpty()){
         State s = pq.poll();
         
         if(s.x == xt && s.y == yt){
            answer = s.t;
            break;
         }
         
         if(times[s.x][s.y] != s.t) continue;
         
         
         long mod = a[s.x][s.y] + b[s.x][s.y];
         //go up or down, get new time
         long newht = s.t;
         if(s.t % mod >= a[s.x][s.y]){
            newht = mod * (s.t/mod + 1);
         }
         if(s.x > 0){
            long newt = newht + v[s.x-1][s.y];
            if(times[s.x-1][s.y] > newt){
               times[s.x-1][s.y] = newt;
               pq.add(new State(s.x-1,s.y,newt));
            }
         }
         if(s.x < n-1){
            long newt = newht + v[s.x][s.y];
            if(times[s.x+1][s.y] > newt){
               times[s.x+1][s.y] = newt;
               pq.add(new State(s.x+1,s.y,newt));
            }
         }
         
         long newvt = s.t;
         if(s.t % mod < a[s.x][s.y]){
            newvt = s.t / mod * mod + a[s.x][s.y];
         }
         if(s.y > 0){
            long newt = newvt + h[s.x][s.y-1];
            if(times[s.x][s.y-1] > newt){
               times[s.x][s.y-1] = newt;
               pq.add(new State(s.x,s.y-1,newt));
            }
         }
         if(s.y < m-1){
            long newt = newvt + h[s.x][s.y];
            if(times[s.x][s.y+1] > newt){
               times[s.x][s.y+1] = newt;
               pq.add(new State(s.x,s.y+1,newt));
            }
         }
         
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class State implements Comparable<State>{
      int x;
      int y;
      long t;
      
      public State(int a, int b, long c){
         x = a;
         y = b;
         t = c;
      }
      
      public int compareTo(State s){
         if(t < s.t) return -1;
         if(t > s.t) return 1;
         return 0;
      }
   }
   
      
}