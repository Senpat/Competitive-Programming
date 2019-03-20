//make sure to make new file!
import java.io.*;
import java.util.*;

public class E961{
   
   public static int n;
   public static int[] bits;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n+1];
      bits = new int[n+1];
      
      PriorityQueue<State> pq = new PriorityQueue<State>();
      
      for(int k = 1; k <= n; k++){
         array[k] = Math.min(n,Integer.parseInt(st.nextToken()));
         pq.add(new State(array[k],k));
         update(k,1);
      }
      
      long count = 0;
      for(int k = 1; k < n; k++){
         while(!pq.isEmpty() && pq.peek().e < k){
            
            State s = pq.poll();
            //out.println("hi " + s.s);
            update(s.s,-1);
         }
         /*
         out.println(k);
         for(int j = 1; j <= n; j++){
            out.print(psum(j) + " ");
         }*/
                  
         count += Math.max(0,psum(array[k]) - psum(k));
         //out.println(count);
      }
      
      out.println(count);
      
      

      
      
      
      
      out.close();
   }
   
      
   public static void update(int i, int x){
      //System.out.println("update " + i);
      for(; i <= n; i += i&-i){
         
         bits[i]+=x;
      }
   
   }
   
   public static int psum(int i){
      int sum = 0;
      for(; i > 0; i -= i&-i){
         sum += bits[i];
      }
      return sum;
   
   }
   
   
   
   
   public static class State implements Comparable<State>{
      int e;
      int s;
      public State(int a, int b){
         e = a;
         s = b;
      }
      
      public int compareTo(State s){
         return e-s.e;                 
      }
   }
   
      
}