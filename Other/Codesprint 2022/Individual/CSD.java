//make sure to make new file!
import java.io.*;
import java.util.*;

public class CSD{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      Job[] jobs = new Job[n];
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());      
         
         jobs[k] = new Job(a,b,k);
      }
      
      int[] unoptimal = new int[n];
      int[] optimal = new int[n];
      
      //calculate unoptimal
      PriorityQueue<Integer> end = new PriorityQueue<Integer>();
      
      int done = 0;
      for(int k = 0; k < n; k++){
         while(!end.isEmpty() && end.peek() <= jobs[k].t){
            done++;
            end.poll();
         }
         
         if(end.size() < m){
            end.add(jobs[k].t + jobs[k].l);
         }
         
         unoptimal[k] = done+end.size();
      }
      
      //calculate optimal
      TreeSet<Job> tset = new TreeSet<Job>();
      done = 0;
      for(int k = 0; k < n; k++){
         while(!tset.isEmpty() && tset.first().end <= jobs[k].t){
            done++;
            tset.pollFirst();
         }
         
         if(tset.size() < m){
            tset.add(jobs[k]);
         } else {
            if(tset.last().end > jobs[k].end){
               tset.pollLast();
               tset.add(jobs[k]);
            }
         }
         
         optimal[k] = done+tset.size();
      }
      
      
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < n; k++){
         sj.add("" + (optimal[k]-unoptimal[k]));
      }
      out.println(sj.toString());
      
      out.close();
   }
   
   public static class Job implements Comparable<Job>{
      int t;
      int l;
      int id;
      
      int end;
      public Job(int a, int b, int c){
         t = a;
         l = b;
         id = c;
         
         end = t+l;
      }
      
      public int compareTo(Job j){
         if(end != j.end) return end-j.end;
         return id-j.id;
      }
   }
   
      
}