//Asya and Kittens
import java.io.*;
import java.util.*;

public class F1131{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      ArrayList<HashSet<Integer>> adj = new ArrayList<HashSet<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new HashSet<Integer>());
      
      for(int k = 0; k < n-1; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         adj.get(a).add(b);
         adj.get(b).add(a);
      
      }
      
      
      ArrayList<Integer> answer = new ArrayList<Integer>(n);
      
      for(int k = 1; k <= n && answer.size() == 0; k++){
         if(adj.get(k).size() == 1){
            answer.add(k);
            break;
         }
      }
      
      for(int k = 0; k < n-1; k++){
         for(int i : adj.get(answer.get(k))){
            answer.add(i);
            adj.get(i).remove(answer.get(k));
            break;
         }
      }
      
      for(int i : answer){
         out.print(i + " ");
      }
      
      
      out.close();
   }
   
      
}