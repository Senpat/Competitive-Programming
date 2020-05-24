//make sure to make new file!
import java.io.*;
import java.util.*;

public class A623n{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      //BufferedReader f = new BufferedReader(new FileReader("test.out"));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st1 = new StringTokenizer(f.readLine());
      StringTokenizer st2 = new StringTokenizer(f.readLine());
      
      long[] array = new long[n];
      long[] time = new long[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st1.nextToken());
         time[k] = Long.parseLong(st2.nextToken());
         
      }
      
      PriorityQueue<Num2> pq2 = new PriorityQueue<Num2>();
      
      for(int k = 0; k < n; k++){
         pq2.add(new Num2(array[k],time[k]));
      }
      
      PriorityQueue<Num> pq = new PriorityQueue<Num>(10,Collections.reverseOrder());
      
      long answer = 0L;
      
      Num2 first = pq2.poll();
      long cur = first.i;
      pq.add(new Num(first.i,first.t));
      while(!pq2.isEmpty() && pq2.peek().i == cur){
         Num2 temp = pq2.poll();
         pq.add(new Num(temp.i,temp.t));
      }
      pq.poll();
      
      while(!pq2.isEmpty() || !pq.isEmpty()){
        
         if(pq.isEmpty()){
            //reset cur
            first = pq2.poll();
            cur = first.i;
            pq.add(new Num(first.i,first.t));
            while(!pq2.isEmpty() && pq2.peek().i == cur){
               Num2 temp = pq2.poll();
               pq.add(new Num(temp.i,temp.t));
            }
            pq.poll();
         } else {
            cur++;
            while(!pq2.isEmpty() && pq2.peek().i == cur){
               Num2 temp = pq2.poll();
               pq.add(new Num(temp.i,temp.t));
            }
            
            Num remove = pq.poll();
            answer += (cur-remove.i)*remove.t;
            
            
         } 
         
         
         
      }
         
         
      
      out.println(answer);
      
      
      
      out.close();
   }
   
   public static class Num implements Comparable<Num>{
      long i;
      long t;
      public Num(long a, long b){
         i = a;
         t = b;
      }
      public int compareTo(Num n){
         //return t-n.t;
         if(t > n.t) 
            return 1;
         if(t < n.t) 
            return -1;
         return 0;
      }
   }
   
   public static class Num2 implements Comparable<Num2>{
      long i;
      long t;
      public Num2(long a, long b){
         i = a;
         t = b;
      }
      public int compareTo(Num2 n){
         if(i > n.i) 
            return 1;
         if(i < n.i) 
            return -1;
         return 0;
      }
   }
      
      
}