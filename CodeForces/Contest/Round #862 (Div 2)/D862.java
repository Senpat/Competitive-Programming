//make sure to make new file!
import java.io.*;
import java.util.*;

public class D862{

   public static ArrayList<ArrayList<Integer>> adj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
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
      int[] dist1 = new int[n+1];
      Arrays.fill(dist1,-1);
      dist1[1] = 1;
      Queue<Integer> q = new LinkedList<Integer>();
      q.add(1);
      int maxd = 1;
      while(!q.isEmpty()){
         int v = q.poll();
         
         if(dist1[v] > dist1[maxd]) maxd = v;
         
         for(int nei : adj.get(v)){
            if(dist1[nei] != -1) continue;
            dist1[nei] = dist1[v]+1;
            q.add(nei);   
         }
      }
      
      int[] dist2 = new int[n+1];
      Arrays.fill(dist2,-1);
      dist2[maxd] = 1;
      q = new LinkedList<Integer>();
      q.add(maxd);
      
      int max = 0;
      
      while(!q.isEmpty()){
         int v = q.poll();
         
         max = Math.max(max,dist2[v]);
         
         for(int nei : adj.get(v)){
            if(dist2[nei] != -1) continue;
            dist2[nei] = dist2[v]+1;
            q.add(nei);   
         }
      
         
      }
      
      //diameter is max
      int[] answer = new int[n+1];
      //out.println(max);
      for(int k = 1; k < max; k++){
         int r = 1+k-1;
         int l = max-k+1;
         
         if(l > r) answer[k] = 1;
         else{
            answer[k] = 1 + r-l+1;
         }
      }
      for(int k = max; k <= n; k++){
         answer[k] = n;
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 1; k <= n; k++) sj.add("" + answer[k]);
      out.println(sj.toString());
      
      
      
      out.close();
   }
   
      
}