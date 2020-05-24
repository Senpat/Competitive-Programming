//make sure to make new file!
import java.io.*;
import java.util.*;

public class E1220{

   public static long[] w;
   public static ArrayList<ArrayList<Integer>> adj;
   public static int start;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      w = new long[n+1];
      long sum = 0L;
      for(int k = 1; k <= n; k++){
         w[k] = Long.parseLong(st.nextToken());
         sum += w[k];
      }
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      start = Integer.parseInt(f.readLine());
      
      ArrayList<Long> branches = new ArrayList<Long>();
      
      for(int k = 1; k <= n; k++){
         if(k == start || adj.get(k).size() > 1) continue;
         
         long cur = w[k];
         int i = adj.get(k).get(0);
         int parent = k;
         while(i != start && adj.get(i).size() == 2){
            cur += w[i];
            int nei = adj.get(i).get(0);
            if(nei == parent){
               nei = adj.get(i).get(1);
            }
            parent = i;
            i = nei;
         }
         
         branches.add(cur);
      }
      
      Collections.sort(branches);
      
      for(int k = 0; k < branches.size()-1; k++){
         sum-=branches.get(k);
      }
      
      out.println(sum);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}