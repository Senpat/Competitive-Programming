//make sure to make new file!
import java.io.*;
import java.util.*;

public class D774{

   public static ArrayList<ArrayList<Integer>> adj;
   
   public static int[] color;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      if(n==2){
         out.println("2 2");
         out.println("1 1");
         out.close();
         return;
      }
      
      color = new int[n+1];
      
      color[1] = 0;
      dfs(1,-1);
      
      //all 0 colors get counted
      int a0 = 0;
      int sum0 = 0;
      
      //all 1 colors get counted
      int a1 = 0;
      int sum1 = 0;
      for(int k = 1; k <= n; k++){
         if(color[k] == 0){
            a0++;
            sum0 += adj.get(k).size();
            
            sum1++;
         } else {
            sum0++;
           
            a1++;
            sum1 += adj.get(k).size();
         }
      }
      
      
      if(a0 > a1 || (a0 == a1 && sum0 < sum1)){
         //a0 is the answer
         out.println(a0 + " " + sum0);
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 1; k <= n; k++){
            if(color[k] == 0){
               sj.add("" + adj.get(k).size());
            } else {
               sj.add("1");
            }
         }
         out.println(sj.toString());
      } else {
         //a1 is the answer
         out.println(a1 + " " + sum1);
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 1; k <= n; k++){
            if(color[k] == 1){
               sj.add("" + adj.get(k).size());
            } else {
               sj.add("1");
            }
         }
         out.println(sj.toString());
      }
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int p){
      
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         color[nei] = 1-color[v];
         dfs(nei,v);
      }
   }
      
}