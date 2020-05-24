//make sure to make new file!
import java.io.*;
import java.util.*;

public class C628{

   public static ArrayList<ArrayList<Edge>> adj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Edge>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      int[] answer = new int[n-1];
      Arrays.fill(answer,-1);
      
      for(int k = 0; k < n-1; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(new Edge(b,k));
         adj.get(b).add(new Edge(a,k));
         
      }
      
      PriorityQueue<Node> pq = new PriorityQueue<Node>();
      
      for(int k = 1; k <= n; k++){
         pq.add(new Node(k,adj.get(k).size()));
      }
      
      int i = 0;
      
      
      while(!pq.isEmpty()){
         Node no = pq.poll();
         
         for(Edge e : adj.get(no.v)){
            if(answer[e.i] == -1){
               answer[e.i] = i;
               i++;
            }
         }
      }
      
      for(int k = 0; k < n-1; k++){
         out.println(answer[k]);
      }
      
      
      
      
      
      
      out.close();
   }
   
   public static class Node implements Comparable<Node>{
      int v;         //vertex
      int num;       //number of neighbors
      public Node(int a, int b){
         v = a;
         num = b;
      }
      public int compareTo(Node no){
         return num-no.num;
      }
   }
   
   public static class Edge{
      int v;         //vertex it goes to
      int i;         //index
      public Edge(int a, int b){
         v = a;
         i = b;
      }
   }
      
}