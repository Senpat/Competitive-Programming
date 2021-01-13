//make sure to make new file!
import java.io.*;
import java.util.*;

public class A679{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] strings = new int[6];
      for(int k = 0; k < 6; k++) strings[k] = Integer.parseInt(st.nextToken());
      
      int n = Integer.parseInt(f.readLine());
      
      st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      ArrayList<PriorityQueue<Integer>> pqs = new ArrayList<PriorityQueue<Integer>>(n);
      for(int k = 0; k < n; k++) pqs.add(new PriorityQueue<Integer>());
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < 6; j++){
            pqs.get(k).add(array[k]-strings[j]);
         }
      }
      
      int max = 0;
      PriorityQueue<State> pq = new PriorityQueue<State>();
      
      for(int k = 0; k < n; k++){
         int i = pqs.get(k).poll();
         max = Math.max(max,i);
         pq.add(new State(i,k));
      }
      
      int answer = Integer.MAX_VALUE;
      
      int prevmin = Integer.MAX_VALUE;
      while(!pq.isEmpty()){
         State s = pq.poll();
         if(s.i > prevmin) break;
         answer = Math.min(answer,max-s.i);
         
         if(!pqs.get(s.index).isEmpty()){
            int add = pqs.get(s.index).poll();
            max = Math.max(max,add);
            pq.add(new State(add,s.index));
         } else {
            prevmin = s.i;
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class State implements Comparable<State>{
      int i;
      int index;
      public State(int a, int b){
         i = a;
         index = b;
      }
      public int compareTo(State s){
         return i-s.i;
      }
   }
      
}