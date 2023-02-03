//make sure to make new file!
import java.io.*;
import java.util.*;
//in contest attempt, use random to calculate minimum vertex cover
public class E140{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      st = new StringTokenizer(f.readLine());
      int[] costs = new int[m+1];
      for(int k = 1; k <= m; k++){
         costs[k] = Integer.parseInt(st.nextToken());
      }
      
      int[][] adj = new int[m+1][m+1];
      int total = 0;
      for(int k = 0; k < n-1; k++){
         if(adj[array[k]][array[k+1]] == 0){
            adj[array[k]][array[k+1]] = 1;
            adj[array[k+1]][array[k]] = 1;
            total += 2;
         }
      }
      
      for(int k = 0; k <= m; k++){
         if(adj[k][array[n-1]] == 1){
            total -= 2;
            adj[k][array[n-1]] = 0;
            adj[array[n-1]][k] = 0;
         }
         
         if(adj[k][array[0]] == 1){
            total -= 2;
            adj[k][array[0]] = 0;
            adj[array[0]][k] = 0;
         }
      }
      
      ArrayList<Integer> order = new ArrayList<Integer>();
      for(int k = 1; k <= m; k++){
         if(k == array[n-1] || k == array[0]) 
            continue;
         order.add(k);
      }
      
      int sim = 200000;
      
      int answer = Integer.MAX_VALUE;
      
      for(int s = 0; s < sim; s++){
         
         Collections.shuffle(order);
         
         int[][] curadj = new int[m+1][m+1];
         for(int k = 1; k <= m; k++){
            for(int j = 1; j <= m; j++){
               curadj[k][j] = adj[k][j];
            }
         }
         
         int removed = 0;
         
         int cur = costs[array[n-1]];
         if(array[n-1] != array[0]) cur += costs[array[0]];
         
         for(int i : order){
            cur += costs[i];
            
            for(int k = 1; k <= m; k++){
               if(curadj[k][i] == 1){
                  curadj[k][i] = 0;
                  curadj[i][k] = 0;
                  removed+=2;
               }
            }
            
            if(removed >= total) 
               break;
         }
         
         answer = Math.min(answer,cur);
         
         
      }
      
      
      out.println(answer);
      
      out.close();
   }
   
      
}