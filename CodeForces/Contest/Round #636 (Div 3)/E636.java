//make sure to make new file!
import java.io.*;
import java.util.*;

public class E636{

   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q1 = 1; q1 <= t; q1++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int c = Integer.parseInt(st.nextToken());
         
         ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
         
         Long[] p = new Long[m];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < m; k++){
            p[k] = Long.parseLong(st.nextToken());
         }
         Arrays.sort(p);
         
         long[] psums = new long[m+1];
         psums[0] = 0;
         for(int k = 1; k <= m; k++){
            psums[k] = psums[k-1]+p[k-1];
         }
         
         
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
            
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            adj.get(x).add(y);
            adj.get(y).add(x);
         }
         
         int[] A = new int[n+1];
         int[] B = new int[n+1];
         int[] C = new int[n+1];
         
         Arrays.fill(A,-1);
         Arrays.fill(B,-1);
         Arrays.fill(C,-1);
         
         //fill A
         Queue<State> q = new LinkedList<State>();
         q.add(new State(a,0));
         A[a] = 0;
         while(!q.isEmpty()){
            State s = q.poll();
            
            for(int nei : adj.get(s.v)){
               if(A[nei] == -1){
                  q.add(new State(nei,s.d+1));
                  A[nei] = s.d+1;
               }
            }
         }
         
         //fill B
         q = new LinkedList<State>();
         q.add(new State(b,0));
         B[b] = 0;         
         while(!q.isEmpty()){
            State s = q.poll();
            
            for(int nei : adj.get(s.v)){
               if(B[nei] == -1){
                  q.add(new State(nei,s.d+1));
                  B[nei] = s.d+1;
               }
            }
         }
         
         q = new LinkedList<State>();
         q.add(new State(c,0));
         C[c] = 0;
         while(!q.isEmpty()){
            State s = q.poll();
            
            for(int nei : adj.get(s.v)){
               if(C[nei] == -1){
                  q.add(new State(nei,s.d+1));
                  C[nei] = s.d+1;
               }
            }
         }
         
         
         long answer = Long.MAX_VALUE;
         
         for(int k = 1; k <= n; k++){
            if(A[k] + B[k] + C[k] > m) continue;
            answer = Math.min(answer,psums[B[k]] + psums[A[k]+B[k]+C[k]]);
         }
         
         out.println(answer);
         

      }
      
      
      
      
      out.close();
   }
   
   public static class State{
      int v;
      int d;
      public State(int a, int b){
         v = a;
         d = b;
      }
   }
   
      
}