//Learning Languages
import java.io.*;
import java.util.*;

public class A277{

   public static ArrayList<ArrayList<State>> adj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      adj = new ArrayList<ArrayList<State>>(m+1);
      
      State[] array = new State[n];
            
      for(int k = 0; k < m+1; k++) adj.add(new ArrayList<State>());
      
      boolean arestupid = true;
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         
         int n1 = Integer.parseInt(st.nextToken());
         
         if(n1 > 0) arestupid = false;
         
         State cur = new State(new ArrayList<Integer>(),-1);
         for(int j = 0; j < n1; j++){
            int langnum = Integer.parseInt(st.nextToken());
            cur.lang.add(langnum);
            adj.get(langnum).add(cur);
         }
         
         array[k] = cur;
         
      }
      
      if(arestupid){
         out.println(n);
         out.close();
         System.exit(0);
      }
      
      int comp = 0;
      for(int k = 0; k < n; k++){
         if(array[k].comp==-1){
            bfs(array[k],comp);
            comp++;
         }
      }
      
      out.println(comp-1);
      
      out.close();
   }
   
   public static void bfs(State s, int comp){
      if(s.comp != -1) return;
      s.comp = comp;
      for(int l : s.lang){
         for(State nei : adj.get(l)){
            if(nei.comp == -1){
               bfs(nei,comp);
            }
         }
      }
   }
   
   static class State{
      ArrayList<Integer> lang;
      
      int comp;
      public State(ArrayList<Integer> a,int c){
         lang = a;
         comp = c;
      }
   }
   
}