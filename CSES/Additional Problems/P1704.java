//make sure to make new file!
import java.io.*;
import java.util.*;
//WRONG - not necessarily valid to connect deepest node with root
public class P1704{

   public static ArrayList<ArrayList<Integer>> adj;
   
   public static int[] depth;
      
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int[] nei = new int[n+1];
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Integer>());
      }
      
      for(int k = 0; k < n-1; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         nei[a]++;
         nei[b]++;
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      ArrayList<Integer> leaves = new ArrayList<Integer>();
      for(int k = 1; k <= n; k++){
         if(nei[k] == 1){
            leaves.add(k);
         }
      }
      depth = new int[n+1];
      int root = leaves.get(0);
      depth[root] = 0;
      dfs(root,-1);
      
      int deepest = 0;
      for(int k = 1; k < leaves.size(); k++){
         if(depth[deepest] < depth[leaves.get(k)]){
            deepest = k;
         }
      }
      
      ArrayList<Pair> answer = new ArrayList<Pair>();
      answer.add(new Pair(leaves.get(0),leaves.get(deepest)));
      
      ArrayList<Integer> rest = new ArrayList<Integer>();
      for(int k = 1; k < leaves.size(); k++){
         if(k != deepest) rest.add(leaves.get(k));
      }
      
      for(int k = 0; k+1 < rest.size(); k+=2){
         answer.add(new Pair(rest.get(k),rest.get(k+1)));
      }
      
      if(rest.size() % 2 == 1){
         answer.add(new Pair(root,rest.get(rest.size()-1)));
      }
      
      
      StringJoiner sj = new StringJoiner("\n");
      sj.add("" + answer.size());
      for(Pair p : answer){
         sj.add("" + p.a + " " + p.b);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         depth[nei] = depth[v]+1;
         dfs(nei,v);
      }
   }
   
   public static class Pair{
      int a;
      int b;
      public Pair(int c, int d){
         a = c;
         b = d;
      }
   }
      
}