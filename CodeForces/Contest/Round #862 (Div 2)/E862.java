//make sure to make new file!
import java.io.*;
import java.util.*;

public class E862{

   public static int n;
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static int[] vals;
   public static HashMap<Integer,Integer> freq;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Edge>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Edge>());
      
      StringTokenizer st;
      for(int k = 0; k < n-1; k++){
      
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(new Edge(b,k));
         adj.get(b).add(new Edge(a,k));
      }
      
      vals = new int[n+1];
      freq = new HashMap<Integer,Integer>();
      
      st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= n; k++){
         vals[k] = Integer.parseInt(st.nextToken());
         if(freq.containsKey(vals[k])) freq.put(vals[k],freq.get(vals[k])+1);
         else freq.put(vals[k],1);
      }
      
      int max3 = 0;
      ArrayList<Integer> twos = new ArrayList<Integer>();
      
      for(int i : freq.keySet()){
         if(freq.get(i) == 2) twos.add(i);
         if(freq.get(i) >= 3) max3 = Math.max(max3,i);
      }
      
      Collections.sort(twos,Collections.reverseOrder());
      
      int[] answer = new int[n-1];
      Arrays.fill(answer,max3);
      
      if(twos.size() > 0 && twos.get(0) > max3){
         
         HashMap<Integer,Integer> twosindexof = new HashMap<Integer,Integer>();
         for(int k = 0; k < twos.size(); k++){
            twosindexof.put(twos.get(k),k);
         }
         
         int[][] twoindeces = new int[twos.size()][2];
         for(int k = 0; k < twos.size(); k++){
            twoindeces[k][0] = -1;
            twoindeces[k][1] = -1;
         }
      
         for(int k = 1; k <= n; k++){
            if(twosindexof.containsKey(vals[k])){
               int index = twosindexof.get(vals[k]);
               if(twoindeces[index][0] == -1) twoindeces[index][0] = k;
               else twoindeces[index][1] = k;
            }
         }
         
         int start1 = twoindeces[0][0];
         int start2 = twoindeces[0][1];
         
         //root at start1
         lca = new int[n+1][MAXD];
         depth = new int[n+1];
         depth[start1] = 0;
         parentedge = new int[n+1];
         Arrays.fill(parentedge,-1);
         initdfs(start1,-1);
         initLCA();
         
         ArrayList<Integer> vertices = new ArrayList<Integer>();
         ArrayList<Integer> edges = new ArrayList<Integer>();
         HashSet<Integer> edgeshset = new HashSet<Integer>();
         
         vertices.add(start2);
         int i = start2;
         while(i != start1){
            edges.add(parentedge[i]);
            edgeshset.add(parentedge[i]);
            i = lca[i][0];
            vertices.add(i);
         }
         
         for(int k = 0; k < n-1; k++){
            if(!edgeshset.contains(k)) answer[k] = twos.get(0);
         }
         
         int pn = vertices.size();
         int[] pathindexof = new int[n+1];
         Arrays.fill(pathindexof,-1);
         
         for(int k = 0; k < pn; k++){
            pathindexof[vertices.get(k)] = k;
         }
         
         ArrayList<ArrayList<Integer>> pathlca = new ArrayList<ArrayList<Integer>>(pn);
         for(int k = 0; k < pn; k++) pathlca.add(new ArrayList<Integer>());
         
         TreeSet<Integer> tset = new TreeSet<Integer>();
         for(int k = 1; k < twos.size(); k++){
            if(twos.get(k) <= max3) break;
            tset.add(twos.get(k));
            int lca1 = lca(start2,twoindeces[k][0]);
            int lca2 = lca(start2,twoindeces[k][1]);
            pathlca.get(pathindexof[lca1]).add(twos.get(k));
            pathlca.get(pathindexof[lca2]).add(twos.get(k));
         }
         
         tset.add(max3);
         for(int k = 0; k < pn-1; k++){
            for(int x : pathlca.get(k)){
               if(tset.contains(x)) tset.remove(x);
               else tset.add(x);
            }
            
            answer[edges.get(k)] = tset.last();
         }
      
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < n-1; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      out.close();
   }
   
   public static class Edge{
      int to;
      int i;
      public Edge(int a, int b){
         to = a;
         i = b;
      }
   }
   
   
   //lca and dist stuff
   public static int MAXD = 18;
   public static int[][] lca;
   public static int[] depth;
   public static int[] parentedge;
   
   public static void initLCA() {
      for(int d = 1; d < MAXD; d++) {
         for(int i = 0; i < n+1; i++) {
            lca[i][d] = lca[lca[i][d-1]][d-1];
         }
      }
   }
   
   public static int lca(int a, int b){
      
      if(depth[a] < depth[b]){
         //swap a and b
         int temp = a;
         a = b;
         b = temp;
      }
      
      for(int i = MAXD-1; i >= 0; i--){
         if (((depth[a]-depth[b]) & (1<<i)) != 0){
         //if(depth[a] - (1<<i) > depth[b]){
            a = lca[a][i];
         }
      }
      if(a==b) 
         return a;
      
      for(int i = MAXD-1; i >= 0; i--){
         if(lca[a][i] != lca[b][i]){
            a = lca[a][i];
            b = lca[b][i];
         }
      }
      return lca[a][0];
   }
            
   
   
   public static int dist(int a, int b){
      //System.out.println("lca: " + lca(a,b));
      return depth[a] + depth[b] - 2*depth[lca(a,b)];
   }
   
   public static void initdfs(int v, int p){
      
      for(Edge e : adj.get(v)){
         if(e.to == p) 
            continue;
         depth[e.to] = depth[v]+1;
         lca[e.to][0] = v;
         parentedge[e.to] = e.i;
         initdfs(e.to,v);
      }
   }
   
      
}