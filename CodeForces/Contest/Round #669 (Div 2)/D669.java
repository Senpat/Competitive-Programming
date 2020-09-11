//make sure to make new file!
import java.io.*;
import java.util.*;
//wrong
public class D669{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      ArrayList<HashSet<Integer>> adj = new ArrayList<HashSet<Integer>>(n);
      for(int k = 0; k < n; k++) adj.add(new HashSet<Integer>());
      
      for(int k = 0; k < n-1; k++){
         adj.get(k).add(k+1);
      }
      
      //get max edges
      int start = 0;
      int minbet = Integer.MAX_VALUE;
      int k = 1;
      while(k < n){
         if(array[k] < minbet){
            if(array[k] > array[start]){
               adj.get(start).add(k);
               minbet = array[k];
            } else {
               adj.get(start).add(k);
               k = start+1;
               start = k;
               minbet = Integer.MAX_VALUE;
            }
         }
         k++;
      }
      
      //get min edges
      start = 0;
      int maxbet = -1;
      int k = 1;
      while(k < n){
         if(array[k] > maxbet){
            if(array[k] < array[start]){
               adj.get(start).add(k);
               maxbet = array[k];
            } else {
               adj.get(start).add(k);
               k = start+1;
               start = k;
               maxbet = -1;
            }
         }
         k++;
      }
      
      //bfs
      
      int[] path = new int[n];
      Arrays.fill(path,Integer.MAX_VALUE);
      path[0] = 0;
      
      for(int k = 0; k < n; k++){
         for(int nei : adj.get(k)){
            path[nei] = Math.min(path[nei],path[k]+1);
         }
      }
      
      int answer = path[n-1];
      out.println(answer);
         
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}