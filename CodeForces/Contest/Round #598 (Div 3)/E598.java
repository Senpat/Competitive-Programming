//make sure to make new file!
import java.io.*;
import java.util.*;

public class E598{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      State[] sort = new State[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         sort[k] = new State(array[k],k);
      }
      
      Arrays.sort(sort);
      
      int[][] dp = new int[n][6];
      Pair[][] back = new Pair[n][6];
      for(int k = 0; k < n; k++) Arrays.fill(dp[k],Integer.MAX_VALUE);
      
      dp[2][3] = sort[2].v - sort[0].v;
      dp[3][4] = sort[3].v - sort[0].v;
      dp[4][5] = sort[4].v - sort[0].v;
      back[2][3] = new Pair(0,0);
      back[3][4] = new Pair(0,0);
      back[4][5] = new Pair(0,0);
      
      
      for(int k = 0; k < n; k++){
         for(int j = 3; j < 6; j++){
            if(dp[k][j] == Integer.MAX_VALUE) continue;
            
            for(int h = 3; h < 6; h++){
               if(k+h >= n) break;
               if(dp[k][j] + sort[k+h].v-sort[k+1].v < dp[k+h][h]){
                  dp[k+h][h] = dp[k][j] + sort[k+h].v-sort[k+1].v;
                  back[k+h][h] = new Pair(k,j);
               }
            }
            
         }
      }
               
      
      Pair start = new Pair(n-1,3);
      int min = dp[n-1][3];
      if(dp[n-1][4] < min){
         start = new Pair(n-1,4);
         min = dp[n-1][4];
      }
      if(dp[n-1][5] < min){
         start = new Pair(n-1,5);
         min = dp[n-1][5];
      }
      
      int[] answer = new int[n];
      int team = 1;
      while(start.b != 0){
         Pair pair = back[start.a][start.b];
         
         for(int k = 0; k < start.b; k++){
            answer[sort[start.a-k].i] = team;
         }
         
         team++;
         start = pair;
      }
      
      out.println(min + " " + (team-1));
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj);
      
      
      
      
      
      out.close();
   }
   
   public static class Pair{
      int a;
      int b;
      public Pair(int c, int d){
         a = c;
         b = d;
      }
   }
   
   public static class State implements Comparable<State>{
      int v;         //value
      int i;         //index
      public State(int a, int b){
         v = a;
         i = b;
      }
      public int compareTo(State s){
         return v-s.v;
      }
   }
}