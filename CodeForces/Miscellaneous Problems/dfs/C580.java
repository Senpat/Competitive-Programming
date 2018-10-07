//Kefa and Park
//fails if b > a
import java.io.*;
import java.util.*;

public class C580{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] cat = new int[n+1];
      
      st = new StringTokenizer(f.readLine());
      
      for(int k = 1; k <= n; k++) cat[k] = Integer.parseInt(st.nextToken());
      
      ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         if(b>a){
            int temp = b;
            b = a;
            a = temp;
         }
         adj.get(a).add(b);
      }
      
      
      Queue<State> q = new LinkedList<State>();
      if(cat[1] == 1) q.add(new State(1,1));
      else q.add(new State(1,0));
      
      int answer = 0;
      while(!q.isEmpty()){
         State cur = q.poll();
         
         if(cur.c<=m){
            if(adj.get(cur.i).size()==0){
               answer++;
            } else {
               for(Integer nei : adj.get(cur.i)){
                  if(cat[nei]==1) q.add(new State(nei,cur.c+1));
                  else q.add(new State(nei,0));
               }
            }
         }
      }
      
      out.println(answer);
      
      
      out.close();
   }
   
   static class State{
      int i;
      int c;
      
      public State(int a, int b){
         i=a;
         c=b;
      }
   }
   

}