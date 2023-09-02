//make sure to make new file!
import java.io.*;
import java.util.*;
//comments / algolive ep 22
public class P1704b{

   public static ArrayList<ArrayList<Integer>> adj;
   
   public static ArrayList<Integer> leaves;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Integer>());
      }
      
      for(int k = 0; k < n-1; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      leaves = new ArrayList<Integer>();
      
      dfs(1,-1);
      
      int nl = leaves.size();
      
      StringJoiner sj = new StringJoiner("\n");
      sj.add("" + (nl+1)/2);
      for(int k = 0; k + (nl+1)/2 < nl; k++){
         sj.add("" + leaves.get(k) + " " + leaves.get(k + (nl+1)/2));
      }
      
      if(nl % 2 == 1){
         sj.add("" + leaves.get(0) + " "  + leaves.get(nl/2));
      }
      
      out.println(sj.toString());
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      if(adj.get(v).size() == 1){
         leaves.add(v);
      }
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfs(nei,v);
      }
   }
   
      
}