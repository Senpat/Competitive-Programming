//make sure to make new file!
import java.io.*;
import java.util.*;
//SOLVED WRONG PROBLEM D: thought it was paths not subtrees
public class F627{
   
   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] value;
   
   public static int[][] pdown;                          //1st and 2nd best paths going down (including itself)
   public static int[][] child;                          //child of corresponding path
   
   public static int[] pup;                              //best path going up (not including itself)
   
   
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
      
      //setup dfspdown
      pdown = new int[n+1][2];
      child = new int[n+1][2];
      
      for(int k = 0; k <= n; k++){
         for(int j = 0; j < 2; j++){
            pdown[k][j] = Integer.MIN_VALUE;
            child[k][j] = -1;
         }
      }
      
      dfspdown(1,-1);
      
      //setup dfspup
      pup = new int[n+1];
      Arrays.fill(pup,Integer.MIN_VALUE);
      
      dfspup(1,-1);
      
      int[] answer = new int[n+1];
      
      for(int k = 1; k <= n; k++){
         int max = pdown[k][0];
         max = Math.max(max,max+pdown[k][1]-value[k]);
         max = Math.max(max,pdown[k][0]+pup[k]);
         answer[k] = max;
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 1; k <= n; k++){
         sj.add("" + answer[k]);
      }
      
      out.println(sj.toString());
      
      
      
      out.close();
   }
   
   //fill pup
   public static void dfspup(int v, int p){
      if(p == -1){
         pup[v] = 0;
      } else {
         //max between max pdown value of parent that doesnt go through v, and pup value of parent + parent   
         int max = child[p][0] != v ? pdown[p][0] : pdown[p][1];
         max = Math.max(max,pup[p] + value[p]);
         pup[v] = max;
      }
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfspup(nei,v);
      }
   }
      
      
      
   
   //fill pdown
   public static void dfspdown(int v, int p){
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         
         dfspdown(nei,v);
         
         //path through nei is better than current best path down path for v
         if(pdown[nei][0] + value[v] > pdown[v][0]){
            //shift child and pdown value down to 1
            child[v][1] = child[v][0];
            pdown[v][1] = pdown[v][0];
            
            //update pdown[v][0] and child[v][0]
            child[v][0] = nei;
            pdown[v][0] = pdown[nei][0]+value[v];
         } else if(pdown[nei][0] + value[v] > pdown[v][1]){
            child[v][1] = nei;
            pdown[v][1] = pdown[nei][0] + value[v];
         }
      }
      
      //check for just itself
      
      if(value[v] >= pdown[v][0]){
         child[v][1] = child[v][0];
         pdown[v][1] = pdown[v][0];
         
         child[v][0] = -1;
         pdown[v][0] = value[v];
      } else if(value[v] >= pdown[v][1]){
         child[v][1] = -1;
         pdown[v][1] = value[v];
      }
   }
   
   
}