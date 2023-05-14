//make sure to make new file!
import java.io.*;
import java.util.*;

public class D862b{

   public static ArrayList<ArrayList<Integer>> adj;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      //get diameter
      int[] dist1 = getdist(1);
      int end1 = getmax(dist1);
      int[] dist2 = getdist(end1);
      int end2 = getmax(dist2);
      int[] dist3 = getdist(end2);
      
      int[] dist = new int[n+1];
      for(int k = 1; k <= n; k++){
         dist[k] = Math.max(dist2[k],dist3[k]);
      }
      
      int[] answer = new int[n+1];
      for(int k = 1; k <= n; k++){
         answer[dist[k]]++;
      }
      
      answer[1] = 1;
      for(int k = 2; k <= n; k++){
         answer[k] = Math.min(n,answer[k]+answer[k-1]);
      }
      
      
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 1; k <= n; k++) sj.add("" + answer[k]);
      out.println(sj.toString());
      
      
      
      out.close();
   }
   
   public static int getmax(int[] a){
      int max = 1;
      for(int k = 2; k <= n; k++){
         if(a[k] > a[max]) max = k;
      }
      return max;
   }
   
   public static int[] getdist(int x){
      int[] dist = new int[n+1];
      Arrays.fill(dist,-1);
      dist[x] = 1;
      Queue<Integer> q = new LinkedList<Integer>();
      q.add(x);
      int maxd = 1;
      while(!q.isEmpty()){
         int v = q.poll();
         
         if(dist[v] > dist[maxd]) maxd = v;
         
         for(int nei : adj.get(v)){
            if(dist[nei] != -1) continue;
            dist[nei] = dist[v]+1;
            q.add(nei);   
         }
      }
      
      return dist;  
   }
   
      
}