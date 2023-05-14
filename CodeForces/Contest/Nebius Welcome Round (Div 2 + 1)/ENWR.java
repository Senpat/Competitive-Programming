//make sure to make new file!
import java.io.*;
import java.util.*;

public class ENWR{
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static ArrayList<ArrayList<Integer>> cycles;
   
   public static ArrayList<Integer> path;
   public static boolean[] seen;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int np2 = (1 << n);
      
      //0-INDEXED NODES
      adj = new ArrayList<ArrayList<Integer>>(n);
      for(int k = 0; k < n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken())-1;
         int b = Integer.parseInt(st.nextToken())-1;
      
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      //get all cycles
      cycles = new ArrayList<ArrayList<Integer>>(np2);
      for(int k = 0; k < np2; k++){
         cycles.add(new ArrayList<Integer>());
      }
      
      for(int k = 0; k < n; k++){
         path = new ArrayList<Integer>();
         seen = new boolean[n];
         path.add(k);
         seen[k] = true;
         dfs(k,k);
      }
      
      boolean foundanswer = false;
      for(int mask = 0; mask < np2; mask++){
         if(cycles.get(mask).size() == 0) continue;
         //see if every node is in it or 1 away
         boolean fail = false;
         int[] answer = new int[n];
         Arrays.fill(answer,-1);
         for(int k = 0; k < n; k++){
            if((mask & (1 << k)) != 0) continue;
            for(int nei : adj.get(k)){
               if((mask & (1 << nei)) != 0){
                  answer[k] = nei;
               }
            }
            if(answer[k] == -1){
               fail = true;
               break;
            }
         }
         
         if(fail) continue;
         //found answer
         for(int k = 0; k < cycles.get(mask).size()-1; k++){
            answer[cycles.get(mask).get(k)] = cycles.get(mask).get(k+1);
         }
         answer[cycles.get(mask).get(cycles.get(mask).size()-1)] = cycles.get(mask).get(0);
         out.println("Yes");
         for(int k = 0; k < n; k++){
            out.print((answer[k]+1) + " ");
         }
         out.println();
         foundanswer = true;
         break;
      }
      
      
      if(!foundanswer){
         out.println("No");
      }
      
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v, int start){
      
      for(int nei : adj.get(v)){
         if(nei == start){
            //made a cycle
            int mask = 0;
            for(int p : path){
               mask ^= (1 << p);
            }
            if(cycles.get(mask).size() == 0){
               for(int p : path){
                  cycles.get(mask).add(p);
               }
            }
         }
         
         if(seen[nei]) continue;
         
         seen[nei] = true;
         path.add(nei);
         dfs(nei,start);
         path.remove(path.size()-1);
         seen[nei] = false;
      }
   }
   
      
}