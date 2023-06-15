//make sure to make new file!
import java.io.*;
import java.util.*;
//solves all subtasks
public class p3b{

   public static int n1,n2;

   public static ArrayList<ArrayList<Integer>> adj1;
   public static ArrayList<ArrayList<Integer>> adj2;
   public static ArrayList<HashSet<Integer>> adjcombine;
   public static int[] parent1;
   public static int[] parent2;
   public static int[] parentcombine;
   
   public static int[] depth;
   
   public static int[][] merge;
   
   public static boolean[] seen;
   
   public static boolean[] removed;
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      //BufferedReader f = new BufferedReader(new FileReader("p3-2.in"));
      //PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("p3.out")));
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         //System.out.println(q);
         //System.out.flush();
         n1 = Integer.parseInt(f.readLine());
         
         parent1 = new int[n1+1];
         parentcombine = new int[n1+1];
         HashSet<Integer> roothset = new HashSet<Integer>();
         adj1 = new ArrayList<>(n1+1);
         adj2 = new ArrayList<>(n1+1);
         adjcombine = new ArrayList<>(n1+1);
         adj1.add(new ArrayList<Integer>());
         adj2.add(new ArrayList<Integer>());
         adjcombine.add(new HashSet<Integer>());
         for(int k = 1; k <= n1; k++){
            roothset.add(k);
            adj1.add(new ArrayList<Integer>());
            adj2.add(new ArrayList<Integer>());
            adjcombine.add(new HashSet<Integer>());
         }
         
         for(int k = 0; k < n1-1; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
         
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            parent1[a] = b;
            parentcombine[a] = b;
            adj1.get(b).add(a);
            adjcombine.get(b).add(a);
            
            if(roothset.contains(a)) roothset.remove(a);
         }
         
         int root = -1;
         for(int i : roothset){
            root = i;
            break;
         }
         //out.println(root);
         //out.flush();
         
         n2 = Integer.parseInt(f.readLine());
         //same size as first array
         parent2 = new int[n1+1];
         boolean[] in2 = new boolean[n1+1];
         for(int k = 0; k < n2-1; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
         
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            parent2[a] = b;  
            
            adj2.get(b).add(a);
            
            in2[a] = true;
            in2[b] = true;
         }
         
         depth = new int[n1+1];
         depth[root] = 0;
         initdfs(root,-1);
         
         merge = new int[n1+1][n1+1];        //merge[d][v] -> at depth d, the node that v merged with
         
         seen = new boolean[n1+1];
         removed = new boolean[n1+1];
         seen[root] = true;
         for(int k = 1; k <= n1; k++){
            if(k == root || !in2[k]) continue;
            
            ArrayList<Integer> path1 = new ArrayList<Integer>();
            ArrayList<Integer> path2 = new ArrayList<Integer>();
            
            int i1 = k;
            int i2 = k;
            while(i1 != root){
               seen[i1] = true;
               seen[i2] = true;
               i1 = parent1[i1];
               i2 = parent2[i2];
               path1.add(i1);
               path2.add(i2);
            }
            
            for(int j = 0; j < path1.size(); j++){
               if((int)path1.get(j) != (int)path2.get(j)){
                  int from = path1.get(j);
                  int to = path2.get(j);
                  int d = depth[from];
                  
                  merge[d][from] = to;
                  
                  removed[from] = true;
                  //update tree
                  for(int nei : adj1.get(from)){
                     adjcombine.get(to).add(nei);
                     parentcombine[nei] = to;
                  }
                  //adj2.get(parent1[from]).add(from);
                  //parent2[from] = parent1[from];
               }
            }
               
         }
         
         //second subtask
         
         dfs2(root,-1);
         
         
         int answersize = 0;
         StringJoiner sj = new StringJoiner("\n");
         for(int k = 0; k <= n1; k++){
            for(int j = 1; j <= n1; j++){
               if(merge[k][j] != 0){
                  answersize++;
                  sj.add(j + " " + merge[k][j]);
               }
            }
         }
         
         //if(answersize != n1-n2){
         //   while(true);
         //}
         
         out.println(answersize);
         if(answersize > 0) out.println(sj.toString());
      }
      
      
      
      
      out.close();
   }
   
   
   public static ArrayList<Integer> matched;
   public static void dfs2(int v, int p){
      if(seen[v]){
         for(int nei : adj1.get(v)){
            if(nei == p) continue;
            dfs2(nei,v);
         }
         return;
      }
      //stop when you reach a node that hasn't been seen
      
      ArrayList<Integer> bamboopath = makebamboo(v);
      
      //try matching with nodes with parent
      //System.out.println("trying " + v);
      matched = new ArrayList<Integer>();
      for(int same : adjcombine.get(parentcombine[v])){
         if(same <= v || !seen[same] || removed[same]) continue;
         trypath(same,bamboopath);
         if(matched.size() > 0) break;
      }
      
//       if(matched.size() == 0){
//          System.out.println("Bamboo path:");
//          for(int i : bamboopath){
//             System.out.print(i + " ");
//          }
//          System.out.println();
//          System.out.flush();
//       }
      
      for(int k = 0; k < bamboopath.size(); k++){
         int from = bamboopath.get(k);
         int to = matched.get(k);
         int d = depth[from];
         
         if(from != to) merge[d][from] = to;
      }
   }
   
   //try to find a path going down starting from u that is greater than bamboopath
   public static boolean success;
   public static void trypath(int u, ArrayList<Integer> bamboopath){
      success = false;
      trydfs(u,parentcombine[u],bamboopath,0);
   }
   
   public static void trydfs(int u, int p, ArrayList<Integer> bamboopath, int i){
      if(bamboopath.get(i) > u){
         return;
      }
      
      matched.add(u);
      
      if(i == bamboopath.size()-1){
         success = true;
         return;
      }
      
      for(int nei : adj2.get(u)){
         if(nei == p) continue;
         
         trydfs(nei,u,bamboopath,i+1);
         
         if(success) return;
      }
      
      matched.remove(matched.size()-1);
      
   }
   
   public static HashSet<Integer> insub;
   public static int[] depthmax;
   public static ArrayList<Integer> makebamboo(int v){
      depthmax = new int[n1+1];
      insub = new HashSet<Integer>();
      dfsbamboo(v,-1);
      ArrayList<Integer> path = new ArrayList<Integer>();
      path.add(v);
      int i = depth[v]+1;
      while(i <= n1 && depthmax[i] != 0){
         path.add(depthmax[i]);
         i++;
      }
      
      //merge nodes in subtree with corresponding depthmax
      for(int node : insub){
         if(depthmax[depth[node]] != node){
            merge[depth[node]][node] = depthmax[depth[node]];
         }
      }
      
      return path;
   }
   
   public static void dfsbamboo(int v, int p){
      insub.add(v);
      depthmax[depth[v]] = Math.max(depthmax[depth[v]],v);
      for(int nei : adj1.get(v)){
         if(nei == p) continue;
         dfsbamboo(nei,v);
      }  
   }
   
   
   public static void initdfs(int v, int p){
      for(int nei : adj1.get(v)){
         if(nei == p) continue;
         depth[nei] = depth[v]+1;
         initdfs(nei,v);
      }
   }
   
      
}