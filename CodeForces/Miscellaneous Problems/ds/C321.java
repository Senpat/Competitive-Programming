//Ciel and Commander
import java.io.*;
import java.util.*;

public class C321{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static char[] answer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      

      cadj = new ArrayList<HashSet<Integer>>(n+1);
      for(int k = 0; k <= n; k++) cadj.add(new HashSet<Integer>());
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         a--;
         b--;
      
         //adj.get(a).add(b);
         //adj.get(b).add(a);
         cadj.get(a).add(b);
         cadj.get(b).add(a);
      }
      
      
      
      
      par = new int[n];
      sub = new int[n];
      
      build(0,-1);
      
      //find root
      
      int root = -1;
      for(int k = 0; k < n; k++){
         if(par[k] == k){
            root = k;
         } else {
            adj.get(k).add(par[k]);
            adj.get(par[k]).add(k);
         }
      }
      
      answer = new char[n];
      answer[root] = 'A';
      ansdfs(root,-1);
      
      for(int k = 0; k < n; k++){
         out.print(answer[k] + " ");
      }
      
      
      out.close();
   }
   
   public static void ansdfs(int v, int p){
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         if(answer[v] == 'Z'){
            System.out.println("Impossible");
            System.out.close();
            System.exit(0);
         }
         answer[nei] = (char)(answer[v] + 1);
         ansdfs(nei,v);
      }
   }
   
   //centroid decomposition stuff
   public static ArrayList<HashSet<Integer>> cadj;
   public static int[] par;
   public static int[] sub;
   
   public static void build(int v, int p){
      int s = dfs1(v,p);
      int centroid = dfs2(v,p,s);
      //System.out.println(" " + centroid + " " + p);
      if(p == -1){
         p = centroid;
      }
      
      par[centroid] = p;
      
      ArrayList<Pair> pairs = new ArrayList<Pair>();
      for(int nei : cadj.get(centroid)){
         pairs.add(new Pair(nei,centroid));
      }
      
      for(Pair pr : pairs){
         cadj.get(pr.a).remove(pr.b);
         cadj.get(pr.b).remove(pr.a);
         build(pr.a,pr.b);
      }
      /*
      for(int nei : cadj.get(centroid)){
         cadj.get(nei).remove(centroid);
         cadj.get(centroid).remove(nei);
         build(nei,centroid);
      }*/
         
         
   
   }
   
   //find centroid
   public static int dfs2(int v, int p, int s){
      for(int nei : cadj.get(v)){
         if(nei != p && sub[nei] > s/2){
            return dfs2(nei,v,s);
         }
      }
      return v;
   }
   
   //find size of subtree for every node
   public static int dfs1(int v, int p){
      sub[v] = 1;
      
      for(int nei : cadj.get(v)){
         if(nei != p){
            sub[v] += dfs1(nei,v);
         }
      }
      
      return sub[v];
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