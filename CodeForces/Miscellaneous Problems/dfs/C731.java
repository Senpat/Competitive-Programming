//Socks
//memory limit exceeded
import java.io.*;
import java.util.*;

public class C731{

   public static ArrayList<ArrayList<Integer>> adj;
   public static boolean[] seen;
   public static int[] comps;
   public static int curcomp;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int[] colors = new int[n+1];
      
      for(int k = 1; k <= n; k++) colors[k] = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>();
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      comps = new int[n+1];
      
      curcomp = 1;
      
      for(int k = 1; k <= n; k++){
         if(comps[k]==0){
            dfs(k);
            curcomp++;
         }
      }
      
      int[][] matrix = new int[curcomp][c+1];               //freq of every color at very curcomp, 0 is max
      //int[] maxc = new int[curcomp];                        //for every comp calculate max
      int[] compsizes = new int[curcomp];                   //size of every comp
      
      
      
      for(int k = 1; k <= n; k++){
         compsizes[comps[k]]++;
         matrix[comps[k]][colors[k]]++;
         matrix[comps[k]][0] = Math.max(matrix[comps[k]][0],matrix[comps[k]][colors[k]]);
      }
      
      int answer = 0;
      
      for(int k = 1; k < compsizes.length; k++){
         answer+=compsizes[k]-matrix[k][0];
      }
      
      out.print(answer);
              
      
      out.close();
   }
   
   public static void dfs(int v){
      comps[v] = curcomp;
      
      for(int nei : adj.get(v)){
         if(comps[nei] == 0){
            dfs(nei);
         }
      }
   }
   
}