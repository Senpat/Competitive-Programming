//make sure to make new file!
import java.io.*;
import java.util.*;

public class D168{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      
      ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      boolean[] seen = new boolean[n+1];
      int[] answer = new int[n+1];
      Queue<Integer> q = new LinkedList<Integer>();
      q.add(1);
      seen[1] = true;
      
      while(!q.isEmpty()){
         int i = q.poll();
         
         for(int nei : adj.get(i)){
            if(seen[nei]) continue;
            answer[nei] = i;
            seen[nei] = true;
            q.add(nei);
         }
      }
      
      if(check(answer)){
         out.println("Yes");
         for(int k = 2; k <= n; k++){
            out.println(answer[k]);
         }
      } else {
         out.println("No");
      }
      
     
      
      
      
      
      
      
      
      out.close();
   }
   
   public static boolean check(int[] array){
      for(int k = 2; k < array.length; k++){
         if(array[k] == 0) return false;
      }
      return true;
   }
   
      
}