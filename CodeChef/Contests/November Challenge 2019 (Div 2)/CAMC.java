//make sure to make new file!
import java.io.*;
import java.util.*;

class CAMC{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] array = new int[n];
         
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         ArrayList<PriorityQueue<Integer>> alist = new ArrayList<PriorityQueue<Integer>>(m);
         for(int k = 0; k < m; k++){
            alist.add(new PriorityQueue<Integer>());
         }
         
         for(int k = 0; k < n; k++){
            alist.get(k%m).add(array[k]);
         }
         
         int max = 0;
         PriorityQueue<State> pq = new PriorityQueue<State>();
         
         for(int k = 0; k < m; k++){
            int i = alist.get(k).poll();
            pq.add(new State(i,k));
            max = Math.max(max,i);
         }
         
         int answer = Integer.MAX_VALUE;
         
         while(true){
            State s = pq.poll();
            answer = Math.min(answer,max-s.v);
            
            if(alist.get(s.i).isEmpty()) break;
            
            int i = alist.get(s.i).poll();
            
            max = Math.max(max,i);
            
            pq.add(new State(i,s.i));
         }
         
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
   public static class State implements Comparable<State>{
      int v;      //value
      int i;      //index of alist it belongs to
      
      public State(int a, int b){
         v = a;
         i = b;
      }
      
      public int compareTo(State s){
         return v-s.v;
      }  
   }
   
      
}