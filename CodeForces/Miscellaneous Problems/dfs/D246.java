//Colorful Graph
import java.io.*;
import java.util.*;

public class D246{

   public static TreeMap<Integer,HashSet<Integer>> tmap;
   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] c;
   public static boolean[] seen;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());

      c = new int[n+1];
      
      for(int k = 1; k <= n; k++) c[k] = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>();
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      tmap = new TreeMap<Integer,HashSet<Integer>>();
      seen = new boolean[n+1];
      
      for(int k = 1; k <= n; k++){
         tmap.put(c[k],new HashSet<Integer>());
      }
      
      for(int k = 1; k <= n; k++){
         if(!seen[k]){
            dfs(k);
         }
      }
      
      int max = -1;
      int maxv = 0;
      
      
      for(int color : tmap.keySet()){
         //System.out.println(color + " " + tmap.get(color).size());
         if(tmap.get(color).size() > max){
            max = tmap.get(color).size();
            maxv = color;
         } else if(tmap.get(color).size() == max && color < maxv){                  //this line not needed
            //System.out.println("hi");
            maxv = color;
         }
      }
      
      out.println(maxv);
      
      
      out.close();
   }
   
   public static void dfs(int v){
      if(!seen[v]){
         seen[v] = true;
         for(int nei : adj.get(v)){
            if(!seen[nei] && c[v] != c[nei]){
               tmap.get(c[v]).add(c[nei]);
               tmap.get(c[nei]).add(c[v]);           
               dfs(nei);
            } else if(!seen[nei]){
               dfs(nei);
            }
         }
      }
   }
      
      
      
}