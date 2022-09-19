//make sure to make new file!
import java.io.*;
import java.util.*;

public class CDEb{
   
   public static int n;
   
   public static int[][] adj;
   public static Stack<Integer> stack;
   public static HashSet<Integer> hset;
   public static boolean[] seen;
   public static boolean cycle;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
     
   
      n = Integer.parseInt(f.readLine());
   
      String[] array = new String[n];
      for(int k = 0; k < n; k++){
         array[k] = f.readLine();
      }
      
      
         
      adj = new int[n][n];
      for(int k = 0; k < n; k++) Arrays.fill(adj[k],0);
      
      for(int k = 0; k < n; k++){
         for(int j = k+1; j < n; j++){
            adj[k][j] = kmp(array[j]+array[k]);
            adj[j][k] = kmp(array[k]+array[j]);
         }
      }
         
      //do top sort
      /*
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            out.print(adj[k][j] + " ");
         }
         out.println();
      }*/
      
      stack = new Stack<Integer>();
      seen = new boolean[n];
      hset = new HashSet<Integer>();
      for(int k = 0; k < n; k++){
         
         if(!hset.contains(k)){
            topsort(k);
         }
      }
      
      //dp
      ArrayList<Integer> order = new ArrayList<Integer>();
      while(!stack.empty()){
         order.add(stack.pop());
      }
      
      int[] indexof = new int[n];
      for(int k = 0; k < n; k++){
         indexof[order.get(k)] = k;
      }
      
      cycle = false;
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            if(k == j) 
               continue;
            if(adj[k][j] > 0 && indexof[k] > indexof[j]){
               cycle = true;
            }
         }
      }
      
      if(cycle){
         out.println("Shakespeare, who?");
         out.close();
         return;
      }
      
      int[] dp = new int[n];
      for(int k = 0; k < order.size(); k++){
         int v = order.get(k);
         for(int nei = 0; nei < n; nei++){
            if(nei == v) 
               continue;
            if(adj[v][nei] > 0){
               dp[nei] = Math.max(dp[nei],dp[v] + adj[v][nei]);
            }
         }
      }
      
      int max = 0;
      for(int k = 0; k < n; k++){
         max = Math.max(max,dp[k]);
      }
      
      out.println(max);
      
      out.close();
   }
   
   public static void topsort(int v){
      
      hset.add(v);
      
      for(int nei = 0; nei < n; nei++){
         if(nei == v) 
            continue;
         if(adj[v][nei] == 0) 
            continue;
         if(hset.contains(nei))
            continue;
         
         topsort(nei);
      }
      
      stack.add(v);
   }   
   
   public static int kmp(String s){
      int n = (int)s.length();
      int[] pi = new int[n];
      for (int i = 1; i < n; i++) {
         int j = pi[i-1];
         while (j > 0 && s.charAt(i) != s.charAt(j))
            j = pi[j-1];
         if (s.charAt(i) == s.charAt(j))
            j++;
         pi[i] = j;
      }
      return pi[n-1];
   }
   
   
}