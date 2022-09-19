//make sure to make new file!
import java.io.*;
import java.util.*;

public class C131{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         ArrayList<ArrayList<Integer>> tasks = new ArrayList<ArrayList<Integer>>(n+1);
         for(int k = 0; k <= n; k++) tasks.add(new ArrayList<Integer>());
         
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < m; k++){
            int i = Integer.parseInt(st.nextToken());
            tasks.get(i).add(k);
         }
         
         TreeSet<Worker> tset = new TreeSet<Worker>();
         for(int k = 1; k <= n; k++){
            tset.add(new Worker(tasks.get(k).size(),false,k));
         }
         
         while(true){
            if(n == 1) break;
            
            Worker max = tset.last();
            Worker min = tset.first();
            
            //see if you can give a task from max to min
            if(max.added) break;
            
            if(min.t + 2 < max.t){
               Worker newmax = new Worker(max.t-1,false,max.i);
               Worker newmin = new Worker(min.t+2,true,min.i);
               
               tset.remove(max);
               tset.remove(min);
               tset.add(newmax);
               tset.add(newmin);
            } else break;
         }
         
         int answer = tset.last().t;
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
   public static class Worker implements Comparable<Worker>{
      int t;            //t when finished;
      boolean added;
      int i;
      public Worker(int a, boolean b, int c){
         t = a;
         added = b;
         i = c;
      }
      
      public int compareTo(Worker w){
         if(t != w.t) return t-w.t;
         return i-w.i;
      }
   }
   
      
}