//make sure to make new file!
import java.io.*;
import java.util.*;
//maximize the # of elements that come after each element (same thing as putting each element as far forward as possible)
public class P1757{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Integer>());
      }
      
      int[] indegree = new int[n+1];
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(b).add(a);
         indegree[a]++;
      }
      
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10,Collections.reverseOrder());
      
      for(int k = 1; k <= n; k++){
         if(indegree[k] == 0){
            pq.add(k);
         }
      }
      
      ArrayList<Integer> answer = new ArrayList<Integer>();
      
      while(!pq.isEmpty()){
         int x = pq.poll();
         answer.add(x);
         
         for(int nei : adj.get(x)){
            indegree[nei]--;
            if(indegree[nei] == 0){
               pq.add(nei);
            }
         }
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = n-1; k >= 0; k--){
         sj.add("" + answer.get(k));
      }
      out.println(sj.toString());
      
      
      
      
      
      
      out.close();
   }
   
      
}