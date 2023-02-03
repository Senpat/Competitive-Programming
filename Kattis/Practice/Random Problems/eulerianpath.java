//make sure to make new file!
import java.io.*;
import java.util.*;
//standard find eulerian path/circuit on directed graph
public class eulerianpath{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static Stack<Integer> stack;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      while(true){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         if(n == 0 && m == 0) 
            break;
         
         adj = new ArrayList<ArrayList<Integer>>(n);
         for(int k = 0; k < n; k++) adj.add(new ArrayList<Integer>());
         
         int[] indegree = new int[n];
         int[] outdegree = new int[n];
         
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
         
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            adj.get(a).add(b);
            
            outdegree[a]++;
            indegree[b]++;
         }
         
         boolean fail = false;
         int start = 0;
         int out1 = 0;
         int in1 = 0;
         for(int k = 0; k < n; k++){
            int diff = outdegree[k]-indegree[k];
            if(Math.abs(diff) >= 2){
               fail = true;
               break;
            }
            if(diff == 1){
               start = k;
               out1++;
            }
            if(diff == -1){
               in1++;
            }
         }
         
         if(out1 > 1 || in1 > 1){
            fail = true;
         }
         
         if(fail){
            out.println("Impossible");
            continue;
         }
         
         stack = new Stack<Integer>();
         edfs(start);
         
         if(stack.size() != m+1){
            out.println("Impossible");
            continue;
         }
         
         StringJoiner sj = new StringJoiner(" ");
         while(!stack.isEmpty()){
            sj.add("" + stack.pop());
         }
         out.println(sj.toString());
      
      }
      
      
      
      
      out.close();
   }
   
   public static void edfs(int v){
   
      while(adj.get(v).size() > 0){
         int next = adj.get(v).get(adj.get(v).size()-1);
      
         adj.get(v).remove(adj.get(v).size()-1);
      
         edfs(next);
      
      }
      
      stack.push(v);
      
      
   }
   
      
}