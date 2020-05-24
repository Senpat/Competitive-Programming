//make sure to make new file!
import java.io.*;
import java.util.*;

public class A623{
   
   public static void main(String[] args)throws IOException{
      //BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader f = new BufferedReader(new FileReader("test.out"));
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
      
      
      HashMap<Long,PriorityQueue<Long>> map = new HashMap<Long,PriorityQueue<Long>>();
      PriorityQueue<Num> pq = new PriorityQueue<Num>();
      
      for(int k = 0; k < n; k++){
         pq.add(new Num(array[k],time[k]));
         
         if(!map.containsKey(array[k])){
            map.put(array[k],new PriorityQueue<Long>());
         }
         
         map.get(array[k]).add(time[k]);
      }
      
      long answer = 0L;
      while(!pq.isEmpty()){
         Num num = pq.poll();
         
         if(map.get(num.i).size() == 1){
            continue;
         }
         map.get(num.i).poll();
         answer += num.t;
         if(map.containsKey(num.i+1) && map.get(num.i+1).size() > 0){
            PriorityQueue<Long> temp = new PriorityQueue<Long>();
            pq.add(new Num(num.i+1,num.t));
            while(!map.get(num.i+1).isEmpty()){
               long i = map.get(num.i+1).poll();
               temp.add(i);
               pq.add(new Num(num.i+1,i));
            }
            temp.add(num.t);
            map.put(num.i+1,temp);
            
         } else {
            PriorityQueue<Long> temp = new PriorityQueue<Long>();
            temp.add(num.t);
            map.put(num.i+1,temp);
            pq.add(new Num(num.i+1,num.t));
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
         if(t > n.t) return 1;
         if(t < n.t) return -1;
         return 0;
      }
   }
      
}