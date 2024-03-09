//make sure to make new file!
import java.io.*;
import java.util.*;

public class E229{

   public static ArrayList<ArrayList<Integer>> adj;

   public static int[] parent;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         //adj.get(b).add(a);    //a < b
      }
      
      parent = new int[n+1];
      for(int k = 1; k <= n; k++) parent[k] = k;
      
      int comp = 1;
      int[] answer = new int[n+1];
      answer[n] = 0;
      for(int k = n-1; k >= 1; k--){
         answer[k] = comp;
         
         //add k
         comp++;
         for(int nei : adj.get(k)){
            //all neighbors are bigger than k
            if(join(k,nei)) comp--;
         }
            
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 1; k <= n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      out.close();
   }
   
   
   //dsu
   //returns if join was necessary
   public static boolean join(int u, int v){
      int up = getp(u);
      int vp = getp(v);
      
      if(up != vp) parent[up] = vp;
      return up != vp;
   }
   
   public static int getp(int v){
      if(parent[v] == v) return v;
      parent[v] = getp(parent[v]);
      return parent[v];
   }
   
      
}