//make sure to make new file!
import java.io.*;
import java.util.*;

public class CPR1{

   public static int n;
   public static int[][] adj;
   
   public static Stack<Integer> stack;
   public static boolean[] seen;
   
   public static void topsort(int v){
      seen[v] = true;
      
      for(int nei = 0; nei < n; nei++){
         if(adj[v][nei] == 1 && !seen[nei]){
            topsort(nei);
         }
      }
      
      stack.push(v);
   }
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         n = Integer.parseInt(f.readLine());
      
         adj = new int[n][n];
         
         for(int k = 0; k < n; k++){
            String s = f.readLine();
            for(int j = 0; j < n; j++){
               adj[k][j] = Character.getNumericValue(s.charAt(j));
            }
         }
         
         stack = new Stack<Integer>();
         seen = new boolean[n];
         
         //top sort
         for(int k = 0; k < n; k++){
            if(!seen[k]){
               topsort(k);
            }
         }
         
         ArrayList<Integer> order = new ArrayList<Integer>();
         while(!stack.empty()){
            order.add(stack.pop());
         }
         
         //Collections.reverse(order);
         
         ArrayList<HashSet<Integer>> sets = new ArrayList<HashSet<Integer>>();
         for(int k = 0; k < n; k++) sets.add(new HashSet<Integer>());
         
         for(int k = 0; k < n; k++){
            int cur = order.get(k);
            for(int j = 0; j < n; j++){
               if(adj[j][cur] == 1){
                  for(int i : sets.get(j)){
                     sets.get(cur).add(i);
                  }
               }
            }
            
            sets.get(cur).add(k+1);
         }
         
         StringJoiner sj = new StringJoiner("\n");
         for(int k = 0; k < n; k++){
            StringJoiner sj2 = new StringJoiner(" ");
            sj2.add("" + sets.get(k).size());
            for(int i : sets.get(k)){
               sj2.add("" + i);
            }
            sj.add(sj2.toString());
         }
         
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}