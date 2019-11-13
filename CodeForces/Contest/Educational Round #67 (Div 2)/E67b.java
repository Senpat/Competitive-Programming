//make sure to make new file!
import java.io.*;
import java.util.*;

public class E67b{

   public static long[] sum;                                //stores number of nodes in subtree
   public static long rootans;
   public static long[] answer;
   public static long max;
   public static ArrayList<ArrayList<Integer>> adj;

   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      
      sum = new long[n+1];
      sums(1,-1);
      
      //for(int k = 1; k <= n; k++) System.out.print(sum[k] + " ");              //test sums
      //out.println();
      
      rootans = 0L;
      for(int k = 1; k <= n; k++){
         rootans += sum[k];
      }
      
      answer = new long[n+1];
      answer[1] = rootans;
      max = rootans;
      dfs(1,-1);
      
      //for(int k = 1; k <= n; k++) System.out.print(answer[k] + " ");
      
      out.println(max);
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      if(p!=-1){
         answer[v] = answer[p] + sum[1] - 2*sum[v];
      }
      max = Math.max(max,answer[v]);
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         dfs(nei,v);
      }
   }
      
   
   public static long sums(int v,int p){
      if(adj.get(v).size()==1 && p != -1){                     //REMEMBER THE && P != -1
         sum[v] = 1;
         return sum[v];
      }
      
      long cur = 1L;
      for(int nei : adj.get(v)){
         if(nei == p) 
            continue;
         cur+=sums(nei,v);
      }
      return sum[v] = cur;
   }
   
      
}