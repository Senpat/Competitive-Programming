//make sure to make new file!
import java.io.*;
import java.util.*;
//tutorial
public class E770{
   
   public static ArrayList<ArrayList<Edge>> adj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      ArrayList<ArrayList<Integer>> arrays = new ArrayList<ArrayList<Integer>>();
      
      HashMap<Integer,Integer> freqs = new HashMap<Integer,Integer>();
      
      for(int k = 0; k < n; k++){
         int n1 = Integer.parseInt(f.readLine());
         StringTokenizer st = new StringTokenizer(f.readLine());
         arrays.add(new ArrayList<Integer>());
         for(int j = 0; j < n1; j++){
            int i = Integer.parseInt(st.nextToken());
            arrays.get(k).add(i);
            if(freqs.containsKey(i)){
               freqs.put(i,freqs.get(i)+1);
            } else {
               freqs.put(i,1);
            }
         }
      }
      
      boolean fail = false;
      ArrayList<Integer> values = new ArrayList<Integer>();
   
      for(int i : freqs.keySet()){
         if(freqs.get(i)%2 != 0){
            fail = true;
            break;
         }
         values.add(i);
      }
      
      
      
      if(fail){
         out.println("NO");
         out.close();
         return;
      }
      
      
      ArrayList<Node> nodes = new ArrayList<Node>();
      nodes.add(new Node(-1,-1));
      
      for(int k = 0; k < n; k++){
         //add arrays
         nodes.add(new Node(k,0));
      }
      
      HashMap<Integer,Integer> numindex = new HashMap<Integer,Integer>();
      for(int k = 0; k < values.size(); k++){
         nodes.add(new Node(values.get(k),1));
         
         numindex.put(values.get(k),nodes.size()-1);
      }
      
      
      adj = new ArrayList<ArrayList<Edge>>(nodes.size());
      for(int k = 0; k < nodes.size(); k++) adj.add(new ArrayList<Edge>());
      
      int edges = 0;
      for(int k = 0; k < n; k++){
         
         for(int j = 0; j < arrays.get(k).size(); j++){
            //connect node (k+1) with (numindex.get(arrays.get(k).get(j))
            
            int a = k+1;
            int b = numindex.get(arrays.get(k).get(j));
            
            adj.get(a).add(new Edge(b,k,j,edges,'L'));
            adj.get(b).add(new Edge(a,k,j,edges,'R'));
            
            edges++;
         }
      }
      
      
      
      HashSet<Integer> seenedges = new HashSet<Integer>();
      HashSet<Integer> seennodes = new HashSet<Integer>();
      
      ArrayList<ArrayList<Character>> answer = new ArrayList<ArrayList<Character>>(n);
      for(int k = 0; k < n; k++){
         answer.add(new ArrayList<Character>(arrays.get(k).size()));
         for(int j = 0; j < arrays.get(k).size(); j++){
            answer.get(k).add('?');
         }
      }
      
      Stack<StackNode> stack = new Stack<StackNode>();
      
      for(int k = 1; k < nodes.size(); k++){
         if(seennodes.contains(k)) continue;
      
         stack.add(new StackNode(k,new Edge(-1,-1,-1,-1,'?')));
      
         while(!stack.isEmpty()){
         //get next edge
            int v = stack.peek().v;
            seennodes.add(v);
            
            Edge edge = new Edge(-1,-1,-1,-1,'?');
            while(adj.get(v).size() > 0){
               Edge curedge = adj.get(v).get(adj.get(v).size()-1);
               adj.get(v).remove(adj.get(v).size()-1);
               if(seenedges.contains(curedge.i)) 
                  continue;
               seenedges.add(curedge.i);
               edge = curedge;
               break;
            }
         
         
            if(edge.to == -1){
            //add to answer
               Edge se = stack.peek().e;
               stack.pop();
               if(se.to == -1) 
                  break;
               answer.get(se.ni).set(se.ai,se.ch);
            } else {
               StackNode next = new StackNode(edge.to,edge);
               stack.push(next);
            }
         }
      
      }     
      
      
      StringJoiner sj = new StringJoiner("");
      sj.add("YES\n");
      for(int k = 0; k < n; k++){
         for(int j = 0; j < answer.get(k).size(); j++){
            sj.add("" + answer.get(k).get(j));
         }
         sj.add("\n");
      }
      
      out.println(sj.toString());
      
      out.close();
   }
   
   public static class StackNode{
      int v;
      Edge e;
      
      public StackNode(int a, Edge b){
         v = a;
         e = b;
      }
      
      public String toString(){
         return v + " -> edge: " + e.toString();
      }
   }
   
   public static class Edge{
      int to;              //index of node that you're going to
      int ni;              //which array
      int ai;              //which index in that array
      int i;               //index identifier
      char ch;
      public Edge(int d, int e, int f, int g, char h){
         to = d;
         ni = e;
         ai = f;
         i = g;
         ch = h;
      }
      
      public String toString(){
         return to + " " + ni + " " + ai + " " + i + " " + ch;
      }
   }
   
   public static class Node{
      int v;
      int t;                     //0 is array, 1 is number
      public Node(int a, int b){
         v = a;
         t = b;
      }
   }
      
      
}