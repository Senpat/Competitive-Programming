//make sure to make new file!
import java.io.*;
import java.util.*;

public class F627b{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] value;
   
   public static int[] dpdown;
   public static int[] dpup;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      value = new int[n+1];
      
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Integer>());
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= n; k++){
         value[k] = Integer.parseInt(st.nextToken());
         if(value[k] == 0) value[k]--;
      }
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      
      }
      
      dpdown = new int[n+1];
      dpup = new int[n+1];
      
      dfsdown(1,-1);
      
      dfsup(1,-1);
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k =  1; k <= n; k++){
         sj.add("" + (dpdown[k]+dpup[k]));
      }
      
      out.println(sj.toString());
      
      
      out.close();
   }
   
   public static void dfsdown(int v, int p){
      dpdown[v] = value[v];
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfsdown(nei,v);
         
         if(dpdown[nei] > 0){
            dpdown[v] += dpdown[nei];
         }
      }
   }
   
   public static void dfsup(int v, int p){
      if(p == -1){
         dpup[v] = 0;
      } else {
         int i = dpup[p] + dpdown[p];
         if(dpdown[v] > 0) i-=dpdown[v];
         dpup[v] += Math.max(i,0);
      }
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         
         dfsup(nei,v);
      }
   }
}