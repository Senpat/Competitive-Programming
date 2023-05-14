//make sure to make new file!
import java.io.*;
import java.util.*;
//https://cp-algorithms.com/graph/strongly-connected-components.html
public class C865{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static ArrayList<ArrayList<Integer>> radj;
   public static boolean[] seen;
   
   public static ArrayList<Integer> order;
   public static ArrayList<ArrayList<Integer>> components;
   public static int[] component;
   public static int curcomponent;
   
   public static ArrayList<HashSet<Integer>> compadj;
   public static int[] compdepth;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         adj = new ArrayList<ArrayList<Integer>>(n+1);
         radj = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++){
            adj.add(new ArrayList<Integer>());
            radj.add(new ArrayList<Integer>());
         }
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
      
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if(a == 1) continue;
            
            adj.get(a).add(b);
            radj.get(b).add(a);
         }
         
         seen = new boolean[n+1];
         Arrays.fill(seen,false);
         seen[1] = true;
         dfscheck(1);
         
         boolean fail = false;
         for(int k = 1; k <= n; k++){
            if(!seen[k]){
               fail = true;
               break;
            }
            seen[k] = false;
         }
         
         if(fail){
            out.println("INFINITE");
            continue;
         }
         
         order = new ArrayList<Integer>();
         for(int k = 1; k <= n; k++){
            if(!seen[k]){
               dfs1(k);
            }
         }
         
         components = new ArrayList<ArrayList<Integer>>();
         component = new int[n+1];
         curcomponent = 0;
         Arrays.fill(seen,false);
         
         for(int k = order.size()-1; k >= 0; k--){
            if(!seen[order.get(k)]){
               components.add(new ArrayList<Integer>());
               dfs2(order.get(k));
               curcomponent++;
            }
         }
         
         compadj = new ArrayList<HashSet<Integer>>(curcomponent);
         for(int k = 0; k < curcomponent; k++) compadj.add(new HashSet<Integer>());
         
         compdepth = new int[curcomponent];
         
         for(int k = 1; k <= n; k++){
            for(int nei : radj.get(k)){
               if(component[k] != component[nei]){
                  compadj.get(component[k]).add(component[nei]);
               }
            }
         }
         
         Arrays.fill(seen,false);
         compbfs(component[1]);
         
         ArrayList<ArrayList<Integer>> sections = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) sections.add(new ArrayList<Integer>());
         for(int k = 2; k <= n; k++){
            if(compdepth[component[k]] == 0){
               //wasn't reached by 1, should go in first section, shouldn't reach
               sections.get(0).add(k);
            } else {
               sections.get(compdepth[component[k]]-1).add(k);
            }
         }
         
         ArrayList<Integer> path = genpath(0,n);
         
         ArrayList<Integer> answer = new ArrayList<Integer>();
         for(int i : path){
            if(i == 0){
               for(int v : sections.get(0)){
                  answer.add(v);
               }
               answer.add(1);
               for(int v : sections.get(0)){
                  answer.add(v);
               }
               
            } else {
               for(int v : sections.get(i)){     
                  answer.add(v);
               }
            }
         }
         
         out.println("FINITE");
         out.println(answer.size());
         StringJoiner sj = new StringJoiner(" ");
         for(int i : answer){
            sj.add("" + i);
         }
         out.println(sj.toString());
         
         
      }
      
      
      
      
      out.close();
   }
   
   public static ArrayList<Integer> genpath(int start, int end){
      
      if(start == end){
         ArrayList<Integer> ret = new ArrayList<Integer>();
         ret.add(start);
         return ret;   
      }
      
      ArrayList<Integer> ret = genpath(start+1,end);
      for(int k = start; k <= end; k++){
         ret.add(k);
      }
      return ret;
   }
   
   
   
   public static void compbfs(int c){
      
      Queue<Integer> q = new LinkedList<Integer>();
      
      compdepth[c] = 1;
      q.add(c);
      
      while(!q.isEmpty()){
         int v = q.poll();
         
         for(int nei : compadj.get(v)){
            if(compdepth[nei] != 0){
               continue;
            }
            
            compdepth[nei] = compdepth[v]+1;
            q.add(nei);
         }
      }
      
        
   }
   
   //scc
   public static void dfs1(int v){
      seen[v] = true;
      
      for(int nei : adj.get(v)){
         if(!seen[nei]) dfs1(nei);
      }
      
      order.add(v);
   }
      
   public static void dfs2(int v){
      seen[v] = true;
      component[v] = curcomponent;
      components.get(curcomponent).add(v);
      
      for(int nei : radj.get(v)){
         if(!seen[nei]){
            dfs2(nei);
         }
      }
   }   
   
   //check that all nodes can reach 1
   public static void dfscheck(int v){
      for(int nei : radj.get(v)){
         if(!seen[nei]){
            seen[nei] = true;
            dfscheck(nei);
         }
      }
   }
}