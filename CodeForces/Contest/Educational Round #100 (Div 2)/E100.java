//make sure to make new file!
import java.io.*;
import java.util.*;
//tutorial
public class E100{
   
   public static int n;
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static ArrayList<ArrayList<Integer>> adjspecial;
   public static ArrayList<ArrayList<Integer>> adjspecialop;
   
   public static int component;
   public static int[] components;                                //stores what component each vertex is in
   public static ArrayList<ArrayList<Integer>> compvertices;      //stores what vertices are in each component
   public static HashSet<Integer> componentseen;
   
   public static ArrayList<ArrayList<Integer>> adjcomp;
   
   public static Stack<Integer> stackcomp;
   public static HashSet<Integer> topcompseen;
   
   public static Stack<Integer> stacksp;
   public static HashSet<Integer> topspseen;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      adjspecial = new ArrayList<ArrayList<Integer>>(n+1);
      adjspecialop = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Integer>());
         adjspecial.add(new ArrayList<Integer>());
         adjspecialop.add(new ArrayList<Integer>());
      }
      
      st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= n; k++){
         int i = Integer.parseInt(st.nextToken());
         if(i != 0){
            adj.get(i).add(k);
         }
      }
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adjspecial.get(a).add(b);
         adjspecialop.get(b).add(a);
      }
      
      //get components
      component = 0;
      components = new int[n+1];
      componentseen = new HashSet<Integer>();
      
      compvertices = new ArrayList<ArrayList<Integer>>();
      compvertices.add(new ArrayList<Integer>());                        //empty, make it 1 indexed
      
      for(int k = 1; k <= n; k++){
         if(componentseen.contains(k)) continue;
         component++;
         compvertices.add(new ArrayList<Integer>());
         componentseen.add(k);
         dfscomp(k);
      }
      
      adjcomp = new ArrayList<ArrayList<Integer>>(component+1);
      for(int k = 0; k <= component; k++) adjcomp.add(new ArrayList<Integer>());
      
      for(int k = 1; k <= n; k++){
         for(int nei : adj.get(k)){
            adjcomp.get(components[k]).add(components[nei]);
         }
      }
      
      //do topsort on components
      stackcomp = new Stack<Integer>();
      topcompseen = new HashSet<Integer>();
      for(int k = 1; k <= component; k++){
         if(topcompseen.contains(k)) continue;
         
         topcomp(k);
      }
      
      ArrayList<Integer> comptopsort = new ArrayList<Integer>();
      while(!stackcomp.isEmpty()){
         comptopsort.add(stackcomp.pop());
      }
      
      //do topsort on special edges
      stacksp = new Stack<Integer>();
      topspseen = new HashSet<Integer>();
      for(int k = 1; k <= n; k++){
         if(topspseen.contains(k)) continue;
         
         topsp(k);
      }
      
      int[] topspinds = new int[n+1];
      int i = 0;
      while(!stacksp.isEmpty()){
         topspinds[stacksp.pop()] = i;
         i++;
      }
      
      for(int k = 1; k <= component; k++){
         Collections.sort(compvertices.get(k),new Comparator<Integer>(){
            public int compare(Integer i1, Integer i2){
               return topspinds[i1]-topspinds[i2];
            }
         });
      }
      
      //get the full order
      ArrayList<Integer> order = new ArrayList<Integer>();
      int[] indexof = new int[n+1];
      for(int k = 0; k < comptopsort.size(); k++){
         for(int j = 0; j < compvertices.get(comptopsort.get(k)).size(); j++){
            int x = compvertices.get(comptopsort.get(k)).get(j);
            order.add(x);
            indexof[x] = order.size()-1;
         }
      }
      
      //check if this order is valid
      if(check(indexof)){
         StringJoiner sj = new StringJoiner(" ");
         for(int o : order){
            sj.add("" + o);
         }
         out.println(sj.toString());
      } else {
         out.println(0);
      }
      
      
            
      
      
      
      out.close();
   }
   
   public static boolean check(int[] indexof){
      for(int k = 1; k <= n; k++){
         for(int nei : adj.get(k)){
            if(indexof[k] > indexof[nei]) return false;
         }
         for(int nei : adjspecial.get(k)){
            if(indexof[k]+1 != indexof[nei]) return false;
         }
      }
      return true;
   }
      
      
   
   public static void topsp(int v){
      topspseen.add(v);
      
      
      for(int nei : adjspecial.get(v)){
         if(topspseen.contains(nei)) continue;
         
         topsp(nei);
      }
      
      stacksp.push(v);
   }
   
   public static void topcomp(int v){
      topcompseen.add(v);
      
      for(int nei : adjcomp.get(v)){
         if(topcompseen.contains(nei)) continue;
         
         topcomp(nei);
      }
      
      stackcomp.push(v);
   }
   
   public static void dfscomp(int v){
      components[v] = component;
      compvertices.get(compvertices.size()-1).add(v);
      for(int nei : adjspecial.get(v)){
         if(componentseen.contains(nei)) continue;
         componentseen.add(nei);
         dfscomp(nei);
      }
      
      for(int nei : adjspecialop.get(v)){
         if(componentseen.contains(nei)) continue;
         componentseen.add(nei);
         dfscomp(nei);
      }
   }
      
}