//make sure to make new file!
import java.io.*;
import java.util.*;

public class DMC18{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n);
      for(int k = 0; k < n+1; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      boolean[] seen = new boolean[n+1];
      Queue<Integer> q = new LinkedList<Integer>();
      q.add(1);
      seen[1] = true;
      
      ArrayList<HashSet<Integer>> hslist = new ArrayList<HashSet<Integer>>();
      for(int k = 0; k < n+1; k++) hslist.add(new HashSet<Integer>());
      int step = 0;
      int next = 1;
      boolean move = false;
      
      while(!q.isEmpty()){
         int cur = q.poll();
         
         if(next == cur){
            move = true;
            step++;
         }
         hslist.get(step).add(cur);
         
         for(Integer nei : adj.get(cur)){
            if(!seen[nei]){
               q.add(nei);
               seen[nei] = true;
               if(move){
                  move = false;
                  next = nei;
               }
            }
         }
      }
               
      int[] finale = new int[n];
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++) finale[k] = Integer.parseInt(st.nextToken());
      
      int curstep = 1;
      
      for(int k = 0; k < n; k++){
         if(hslist.get(curstep).contains(finale[k])){
            hslist.get(curstep).remove(finale[k]);
            if(hslist.get(curstep).isEmpty()){
               curstep++;
            }
         } else {
            out.println("No");
            out.close();
            System.exit(0);
         }
      }
      
      out.println("Yes");
      
      
      
      out.close();
   }
   
}