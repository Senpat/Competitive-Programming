//make sure to make new file!
import java.io.*;
import java.util.*;

public class P5{
   
   public static ArrayList<HashSet<Integer>> adj;
   public static HashSet<Integer> seen;
   //public static int size;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n*2];
      Integer[] sort = new Integer[n*2];
      
      for(int k = 0; k < 2*n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         sort[k] = array[k];
      }
      
      Arrays.sort(sort);
      
      HashMap<Integer,Integer> hmap = new HashMap<Integer,Integer>();            //int to node
      HashMap<Integer,Integer> other = new HashMap<Integer,Integer>();
      
      for(int k = 0; k < n; k++){
         other.put(sort[k],sort[2*n-k-1]);
         other.put(sort[2*n-k-1],sort[k]);
      }
      
      for(int k = 0; k < 2*n; k++){
         hmap.put(array[k],k/2 + 1);
      }
      
      adj = new ArrayList<HashSet<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++){
         adj.add(new HashSet<Integer>());
      }
      
      for(int i : hmap.keySet()){
         int a = hmap.get(i);
         int b = hmap.get(other.get(i));
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      int answer = 0;
      seen = new HashSet<Integer>();
      
      for(int k = 1; k <= n; k++){
         if(seen.contains(k)) continue;
         seen.add(k);
         //size = 0;
         answer += dfs(k) - 1;
         //answer += size-1;
      }
      
      out.println(answer);

      
      
      
      
      out.close();
   }
   
   public static int dfs(int v){
      
      Stack<Integer> stk = new Stack<Integer>();
      stk.add(v);
      int size = 0;
      while(!stk.isEmpty()){
         size++;
         
         int i = stk.pop();
         //if(seen.contains(i)) continue;
         
         for(int nei : adj.get(i)){
            if(seen.contains(nei)) continue;
            seen.add(nei);
            stk.push(nei);
         }
      }
      
      return size;
   }
         
   /*
   public static void dfs1(int v){
      size++;
      
      for(int nei : adj.get(v)){
         if(seen.contains(nei)) continue;
         seen.add(nei);
         dfs(nei);
      }
   }*/
      
}