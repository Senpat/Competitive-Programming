//make sure to make new file!
import java.io.*;
import java.util.*;

public class E777{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] indegree = new int[n+1];
      int[] to = new int[n+1];
      int big = n;
      for(int k = 1; k <= n; k++){
         int i = Integer.parseInt(st.nextToken());
         //edge from k to i
         to[k] = i;
         
         if(indegree[i] == 0) big--;
         indegree[i]++;
      }
      
      st = new StringTokenizer(f.readLine());
      int[] vals = new int[n+1];
      int[] indexof = new int[n+1];
      int max = 0;
      for(int k = 1; k <= n; k++){
         vals[k] = Integer.parseInt(st.nextToken());
         max = Math.max(max,vals[k]);
         if(vals[k] <= n){
            indexof[vals[k]] = k;
         }
      }
      
      int D = 31;
      
      int[][] jmp = new int[n+1][D];
      
      for(int k = 1; k <= n; k++){
         jmp[k][0] = to[k];
      }
      
      for(int d = 1; d < D; d++){
         for(int k = 1; k <= n; k++){
            jmp[k][d] = jmp[jmp[k][d-1]][d-1];
         }
      }
      
      //but big will never be equal to 0 because guaranteed to be 2 equal elements
      int dist = 0;
      if(big != 0) dist = (max-n)/big;
      
      //where each node ends up after dist jumps
      ArrayList<ArrayList<Integer>> endshere = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) endshere.add(new ArrayList<Integer>());
      int[] after = new int[n+1];
      for(int k = 1; k <= n; k++){
         int curnode = k;
         int curdist = 0;
         for(int d = D-1; d >= 0; d--){
            if(curdist + (1 << d) <= dist){
               curnode = jmp[curnode][d];
               curdist += (1 << d);
            }
         }
         after[k] = curnode;
         endshere.get(curnode).add(k);
      }
      
      int[] answer = new int[n+1];
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
      
      for(int k = 1; k <= n; k++){
         //get the earliest element that ends up here, or pick from pq if there are no elements
         int i = indexof[k];
         if(endshere.get(i).size() == 0){
            answer[pq.poll()] = k;
         } else {
            answer[endshere.get(i).get(0)] = k;
            for(int j = 1; j < endshere.get(i).size(); j++){
               pq.add(endshere.get(i).get(j));
            }
         }
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 1; k <= n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
         
      
      
      
      
      
      
      out.close();
   }
   
      
}