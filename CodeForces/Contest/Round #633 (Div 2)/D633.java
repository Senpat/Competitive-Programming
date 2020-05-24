//make sure to make new file!
import java.io.*;
import java.util.*;

public class D633{

   public static ArrayList<ArrayList<Integer>> adj;
   public static int[] childp;
   public static boolean odd;
   
   public static int len2count;
   
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
      
      int root = 1;
      int maxchildren = adj.get(1).size();
      
      for(int k = 2; k <= n; k++){
         if(adj.get(k).size() > maxchildren){
            root = k;
            maxchildren = adj.get(k).size();
         }
      }
      
      childp = new int[n+1];
      odd = false;
      dfsmin(root,-1);
      
      
      len2count = 0;
      dfsmax(root,-1);

      int answermin = odd ? 3 : 1;
      int answermax = n-1-len2count;
      
      out.println(answermin + " " + answermax);
      
      
      
      
      out.close();
   }

   
   public static void dfsmax(int v, int p){
      
      int i = 0;
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfsmax(nei,v);
         if(adj.get(nei).size() == 1){
            i++;
         }
      }
      len2count += Math.max(0,i-1);
   }
      
   
   public static void dfsmin(int v, int p){
      
      int pi = -1;
      for(int nei : adj.get(v)){
         if(nei == p) continue;
         dfsmin(nei,v);
         if(pi == -1){
            pi = childp[nei];
         } else if(pi!=childp[nei]){
            odd = true;
         }
      }
      
      if(pi == -1) childp[v] = 0;
      else childp[v] = 1-pi;
   }   
   
      
}