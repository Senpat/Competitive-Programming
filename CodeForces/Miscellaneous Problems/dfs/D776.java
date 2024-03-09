//make sure to make new file!
import java.io.*;
import java.util.*;
//2-sat practice
public class D776{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static ArrayList<ArrayList<Integer>> radj;
   
   public static ArrayList<Integer> order;
   public static boolean[] seen;
   
   public static int compnum;
   public static int[] comp;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int[] doors = new int[n+1];
      for(int k = 1; k <= n; k++){
         doors[k] = Integer.parseInt(st.nextToken());
      }
      
      int[][] switches = new int[n+1][2];
      for(int k = 1; k <= m; k++){
         st = new StringTokenizer(f.readLine());
         
         int num = Integer.parseInt(st.nextToken());
         for(int j = 0; j < num; j++){
            int i = Integer.parseInt(st.nextToken());
            if(switches[i][0] == 0) switches[i][0] = k;
            else switches[i][1] = k;
         }
      }
      
      adj = new ArrayList<ArrayList<Integer>>(2*m+1);
      radj = new ArrayList<ArrayList<Integer>>(2*m+1);
      for(int k = 0; k <= 2*m; k++){
         adj.add(new ArrayList<Integer>());
         radj.add(new ArrayList<Integer>());
      }
      
      for(int k = 1; k <= n; k++){
         int a = switches[k][0];
         int b = switches[k][1];
         int nota = a+m;
         int notb = b+m;
         //System.out.println(a + " " + b + " " + nota + " " + notb);
         
         if(doors[k] == 0){
            //switch door -> (a or b) and (nota or notb)
            adj.get(nota).add(b);
            adj.get(notb).add(a);
            
            adj.get(a).add(notb);
            adj.get(b).add(nota);
            
            radj.get(b).add(nota);
            radj.get(a).add(notb);
            radj.get(notb).add(a);
            radj.get(nota).add(b);
         } else {
            //don't switch door -> (nota or b) and (a or notb)
            adj.get(a).add(b);
            adj.get(notb).add(nota);
            
            adj.get(nota).add(notb);
            adj.get(b).add(a);
            
            radj.get(b).add(a);
            radj.get(nota).add(notb);
            radj.get(notb).add(nota);
            radj.get(a).add(b);
         }
      }
      
      //find scc
      order = new ArrayList<Integer>();
      seen = new boolean[2*m+1];
            
      for(int k = 1; k <= 2*m; k++){
         if(seen[k]) continue;
         dfs(k);
         //out.println(k);
      }
      
      compnum = 1;
      comp = new int[2*m+1];
      for(int o = order.size()-1; o >= 0; o--){
         if(comp[order.get(o)] != 0) continue;
         //System.out.println(order.get(o));
         dfs2(order.get(o));
         compnum++;
      }
      
      boolean fail = false;
      for(int k = 1; k <= m; k++){
         //out.println(comp[k] + " " + comp[k+m]);
         if(comp[k] == comp[k+m]){
            fail = true;
         }
      }
      
      if(fail){
         out.println("NO");
      } else {
         out.println("YES");
      }
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      seen[v] = true;
      
      for(int nei : adj.get(v)){
         if(seen[nei]) continue;
         dfs(nei);
      }
      
      order.add(v);
   }
   
   public static void dfs2(int v){
      comp[v] = compnum;
      //System.out.println(v + " " + radj.get(v).size());
      for(int nei : radj.get(v)){
         //System.out.println("nei " + nei + " " + comp[nei]);
         if(comp[nei] != 0) continue;
         //System.out.println("nei " + nei);
         dfs2(nei);
      }
   }
      
}