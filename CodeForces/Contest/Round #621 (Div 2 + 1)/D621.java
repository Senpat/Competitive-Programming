//make sure to make new file!
import java.io.*;
import java.util.*;

public class D621{
   
   public static ArrayList<ArrayList<Integer>> adj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      
      Integer[] specials = new Integer[s];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < s; k++){
         specials[k] = Integer.parseInt(st.nextToken());
      }
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++)adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      int[] dist1 = new int[n+1];
      int[] distn = new int[n+1];
      
      dist1[1] = 0;
      distn[n] = 0;
      
      boolean[] seen = new boolean[n+1];
      
      Queue<State> q = new LinkedList<State>();
      
      q.add(new State(1,0));
      seen[1] = true;
      
      while(!q.isEmpty()){
         State cur = q.poll();
         
         dist1[cur.v] = cur.i;
         
         for(int nei : adj.get(cur.v)){
            if(seen[nei]) continue;
            
            q.add(new State(nei,cur.i+1));
            seen[nei] = true;
         }
      }
      
      
      Arrays.fill(seen,false);
      q = new LinkedList<State>();
      seen[n] = true;
      q.add(new State(n,0));
      
      while(!q.isEmpty()){
         State cur = q.poll();
         
         distn[cur.v] = cur.i;
         
         for(int nei : adj.get(cur.v)){
            if(seen[nei]) continue;
            
            q.add(new State(nei,cur.i+1));
            seen[nei] = true;
         }
      }
      
      Arrays.sort(specials, new Comparator<Integer>(){
         public int compare(Integer i1, Integer i2){
            return (dist1[i1]-distn[i1])-(dist1[i2]-distn[i2]);
         }
      });
      
      
      int answer = dist1[n];
      int newanswer = 0;
      for(int k = 0; k < s-1; k++){
         int cur = Math.min(dist1[specials[k]]+1+distn[specials[k+1]],dist1[specials[k+1]]+1+distn[specials[k]]);
         newanswer = Math.max(newanswer,cur);
      }
      
      answer = Math.min(answer,newanswer);
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class State{
      int v;
      int i;
      public State(int a, int b){
         v = a;
         i = b;
      }
      public String toString(){
         return v + " " + i;
      }
   }
      
}