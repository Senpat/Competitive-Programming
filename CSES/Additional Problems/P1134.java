//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1134{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n-2];
      for(int k = 0; k < n-2; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] last = new int[n+1];
      Arrays.fill(last,-1);
      for(int k = n-3; k >= 0; k--){
         if(last[array[k]] == -1) last[array[k]] = k;
      }
      
      PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
      for(int k = 1; k <= n; k++){
         if(last[k] == -1) pq.add(k);
      }
      
      ArrayList<Edge> answer = new ArrayList<Edge>();
      
      for(int k = 0; k < n-2; k++){
         int i = pq.poll();
         answer.add(new Edge(array[k],i));
         if(last[array[k]] == k){
            pq.add(array[k]);
         }
      }
      
      answer.add(new Edge(pq.poll(),n));
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < n-1; k++){
         sj.add("" + answer.get(k));  
      }
      
      out.println(sj.toString());
      
      
      
      
      
      out.close();
   }
   
   public static class Edge{
      int a;
      int b;
      public Edge(int c, int d){
         a = c;
         b = d;
      }
      
      public String toString(){
         return a + " " + b;
      }
   }
   
      
}