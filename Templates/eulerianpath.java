/*
TASK: fence
LANG: JAVA
*/

import java.io.*;
import java.util.*;
//standard eulerian path
//supports multiple edges
//from fence USACO training problem
class eulerianpath{

   public static int N = 505;
   
   public static ArrayList<TreeMap<Integer,Integer>> adj;
   public static Stack<Integer> stack;
   public static ArrayList<Integer> answer;
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("fence.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fence.out")));
      
      int m = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<TreeMap<Integer,Integer>>(N);
      
      for(int k = 0; k < N; k++) adj.add(new TreeMap<Integer,Integer>());
      
      int[] degree = new int[N];
      
      int min = N;
      for(int k = 0; k < m; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         addEdge(adj.get(a),b);
         addEdge(adj.get(b),a);
         
         degree[a]++;
         degree[b]++;
         
         min = Math.min(min,a);
         min = Math.min(min,b);
      }
      
      int start = -1;
      for(int k = 0; k < N; k++){
         if(degree[k]%2 == 1){
            start = k;
            break;
         }
      }
      
      stack = new Stack<Integer>();
      answer = new ArrayList<Integer>();
      
      if(start == -1){
         edfs(min);
      } else {
         edfs(start);
      }
      while(!stack.isEmpty()){
         edfs(stack.pop());
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = answer.size()-1; k >= 0; k--){
         sj.add("" + answer.get(k));
      }
      
      out.println(sj.toString());
      
        
        
      out.close();
   }
   
   public static void edfs(int v){
      if(adj.get(v).size() == 0){
         answer.add(v);
         return;
      }
      
      stack.push(v);
        
      int next = adj.get(v).firstKey();
      removeEdge(adj.get(v),next);
      removeEdge(adj.get(next),v);
      
      edfs(next);
   }
      
   public static void addEdge(TreeMap<Integer,Integer> tmap, int x){
      if(tmap.containsKey(x)){
         tmap.put(x,tmap.get(x)+1);
      } else {
         tmap.put(x,1);
      }
   }
   
   public static void removeEdge(TreeMap<Integer,Integer> tmap, int x){
      if(tmap.get(x) == 1){
         tmap.remove(x);
      } else{
         tmap.put(x,tmap.get(x)-1);
      }
   }
      
   
   
   
      
}