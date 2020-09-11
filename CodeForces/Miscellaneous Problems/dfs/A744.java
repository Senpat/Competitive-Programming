//make sure to make new file!
import java.io.*;
import java.util.*;

public class A744{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static int size;
   public static HashSet<Integer> seen;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      
      int[] gov = new int[x];
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < x; k++){
         gov[k] = Integer.parseInt(st.nextToken());
      }
      
      adj = new ArrayList<ArrayList<Integer>>();
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Integer>());
      }
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      seen = new HashSet<Integer>();
      
      //get biggest comp
      int unmatched = n;
      
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10,Collections.reverseOrder());
      for(int k = 0; k < x; k++){
         //get size of component
         size = 0;
         seen.add(gov[k]);
         dfs(gov[k]);
         unmatched-=size;
         pq.add(size);
      }
      
      //get biggest
      int biggestsize = pq.poll();
      
      int answer = c2(biggestsize+unmatched);
      
      while(!pq.isEmpty()){
         answer += c2(pq.poll());
      }
      
      answer -= m;
      out.println(answer);
      
      
      
      
      
      
      out.close();
   }
   
   public static int c2(int x){
      return x*(x-1)/2;
   }
   
   public static void dfs(int v){
      size++;
      for(int nei : adj.get(v)){
         if(seen.contains(nei)) continue;
         seen.add(nei);
         dfs(nei);
      }
   }
}