import java.io.*;
import java.util.*;

class mootube{
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("mootube.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      LinkedList<Edge>[] edges = new LinkedList[n];
      for(int k = 0; k < n; k++){
         edges[k] = new LinkedList<Edge>();
      }
      for(int k = 1; k < n; k++){
         StringTokenizer stt = new StringTokenizer(f.readLine());
         int x = Integer.parseInt(stt.nextToken())-1;
         int y = Integer.parseInt(stt.nextToken())-1;
         int z = Integer.parseInt(stt.nextToken());
         edges[x].add(new Edge(y,z));
         edges[y].add(new Edge(x,z));
      }
      for(int k = 0; k < q; k++){
         int count = 0;
         StringTokenizer stt = new StringTokenizer(f.readLine());
         int th = Integer.parseInt(stt.nextToken());
         int value = Integer.parseInt(stt.nextToken())-1;
         
         boolean[] seen = new boolean[n];
         LinkedList<Integer> queue = new LinkedList<Integer>();
         queue.add(value);
         seen[value] = true;
         while(!queue.isEmpty()){
            int cur = queue.removeFirst();
            for(Edge e : edges[cur]){
               if(!seen[e.d] && e.w>=th){
                  count++;
                  seen[e.d] = true;
                  queue.add(e.d);
               }
            }
         }
         out.println(count);
     }
     out.close();
      
      
      
      
   }
   
   
   


   static class Edge{
      int d,w;
      public Edge(int n1,int n2){
         d = n1;
         w = n2;
      }
   }
}